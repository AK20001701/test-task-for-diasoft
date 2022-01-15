package ru.diasoft.testTask.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.diasoft.testTask.controller.UserController;
import ru.diasoft.testTask.data.dto.UserDto;
import ru.diasoft.testTask.data.entity.User;
import ru.diasoft.testTask.data.mapper.UserMapper;
import ru.diasoft.testTask.data.repository.UserRepository;
import ru.diasoft.testTask.exception.AlreadyTakenException;
import ru.diasoft.testTask.exception.UserNotFoundException;
import ru.diasoft.testTask.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDto> findAll() {
        logger.info("Find all users");
        List<UserDto> list = userMapper.toUserDtoList(userRepository.findAll());
        if (list.isEmpty()) {
            throw new UserNotFoundException();
        }
        return list;
    }

    @Override
    public UserDto findByEmail(String email) {
        return userMapper.toUserDto(userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("email: " + email)));
    }

    @Override
    public UserDto findByPhoneNumber(String phoneNumber) {
        return userMapper.toUserDto(userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new UserNotFoundException("phone number: " + phoneNumber)));

    }

    @Override
    public List<UserDto> findByFirstName(String firstName) {
        return userMapper.toUserDtoList(userRepository.findByFirstName(firstName)
                                .orElseThrow(() -> new UserNotFoundException("first name: " + firstName)));
    }

    @Override
    public List<UserDto> findByLastName(String lastName) {
        return userMapper.toUserDtoList(userRepository.findByLastName(lastName)
                        .orElseThrow(() -> new UserNotFoundException("last name: " + lastName)));
    }

    @Override
    @Transactional
    public void createNewUser(UserDto userDto) {
        checkDuplicate(userDto);
        User user = userMapper.toUser(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        logger.info("Encode user password");
        user.setRegistrationDate(LocalDateTime.now());
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUser(String oldEmail, UserDto userDto) {
        checkDuplicate(userDto);
        User oldUser = userRepository.findByEmail(oldEmail)
                .orElseThrow(() -> new UserNotFoundException("email: " + oldEmail));
        User user = userMapper.toUser(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setId(oldUser.getId());

        logger.info("Update user");
        userRepository.save(user);
    }

    @Override
    public void deleteUser(String email) {
        logger.info("Delete user with email: - " + email);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("email: " + email));
        userRepository.delete(user);
    }

    private void checkDuplicate(UserDto userDto) {
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new AlreadyTakenException("email (" + userDto.getEmail() + ")");
        }
        if (userRepository.findByPhoneNumber(userDto.getPhoneNumber()).isPresent()) {
            throw new AlreadyTakenException("phone number (" + userDto.getPhoneNumber() + ")");
        }
    }
}

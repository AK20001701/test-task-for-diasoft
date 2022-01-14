package ru.diasoft.testTask.service.impl;

import org.springframework.stereotype.Service;
import ru.diasoft.testTask.data.dto.UserDto;
import ru.diasoft.testTask.data.entity.User;
import ru.diasoft.testTask.data.mapper.UserMapper;
import ru.diasoft.testTask.data.repository.UserRepository;
import ru.diasoft.testTask.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDto> findAll() {
        return userMapper.toUserDtoList(userRepository.findAll());
    }

    @Override
    public UserDto findByEmail(String email) {
        return userMapper.toUserDto(userRepository.findByEmail(email));
    }

    @Override
    public UserDto findByPhoneNumber(String phoneNumber) {
        return userMapper.toUserDto(userRepository.findByPhoneNumber(phoneNumber));
    }

    @Override
    public List<UserDto> findByFirstName(String firstName) {
        return userMapper.toUserDtoList(userRepository.findByFirstName(firstName));
    }

    @Override
    public List<UserDto> findByLastName(String lastName) {
        return userMapper.toUserDtoList(userRepository.findByLastName(lastName));
    }

    @Override
    public void createNewUser(UserDto userDto) {
        User user = userMapper.toUser(userDto);

        user.setPassword(userDto.getPassword());

        userRepository.save(user);
    }

    @Override
    public void updateUser(String oldEmail, UserDto userDto) {
        User user = userRepository.findByEmail(oldEmail);
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setPassword(userDto.getPassword());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());

        userRepository.save(user);
    }

    @Override
    public void deleteUser(String email) {
        userRepository.delete(userRepository.findByEmail(email));
    }
}

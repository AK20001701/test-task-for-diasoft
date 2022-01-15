package ru.diasoft.testTask.service;

import org.apache.catalina.UserDatabase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;
import ru.diasoft.testTask.data.dto.UserDto;
import ru.diasoft.testTask.data.entity.User;
import ru.diasoft.testTask.data.mapper.UserMapper;
import ru.diasoft.testTask.data.repository.UserRepository;
import ru.diasoft.testTask.exception.UserNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserMapper mapper;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserRepository userRepository;

    private final String OLD_EMAIL = "old@email.user";
    private final String EMAIL = "new@email.user";
    private final String PHONE_NUMBER = "+79998887722";
    private final String FIRST_NAME = "Anton";
    private final String LAST_NAME = "Ivanov";
    private final String PASSWORD = "qwerty1243";

    @Test
    void findAll() {
        List<UserDto> usersDto = new ArrayList<>();
        List<User> users = new ArrayList<>();
        usersDto.add(new UserDto());
        doReturn(usersDto)
                .when(mapper)
                .toUserDtoList(users);
        usersDto.add(new UserDto());
        doReturn(users)
                .when(userRepository)
                .findAll();
        userService.findAll();
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void findByEmail() {
        User user = new User();
        doReturn(Optional.of(user))
                .when(userRepository)
                .findByEmail(EMAIL);
        userService.findByEmail(EMAIL);
        verify(userRepository, times(1)).findByEmail(EMAIL);
    }

    @Test
    void findByEmail_whenUserNotFound() {
        Assertions.assertThrows(UserNotFoundException.class,
                () -> userService.findByEmail(EMAIL));
        verify(userRepository, times(1)).findByEmail(EMAIL);
    }

    @Test
    void findByPhoneNumber() {
        User user = new User();
        doReturn(Optional.of(user))
                .when(userRepository)
                .findByPhoneNumber(PHONE_NUMBER);
        userService.findByPhoneNumber(PHONE_NUMBER);
        verify(userRepository, times(1)).findByPhoneNumber(PHONE_NUMBER);
    }

    @Test
    void findByPhoneNumber_whenUserNotFound() {
        Assertions.assertThrows(UserNotFoundException.class,
                () -> userService.findByPhoneNumber(PHONE_NUMBER));
        verify(userRepository, times(1)).findByPhoneNumber(PHONE_NUMBER);
    }

    @Test
    void findByFirstName() {
        List<User> users = new ArrayList<>();
        doReturn(Optional.of(users))
                .when(userRepository)
                .findByFirstName(FIRST_NAME);
        userService.findByFirstName(FIRST_NAME);
        verify(userRepository, times(1)).findByFirstName(FIRST_NAME);
    }

    @Test
    void findByFirstName_whenUserNotFound() {
        Assertions.assertThrows(UserNotFoundException.class,
                () -> userService.findByFirstName(FIRST_NAME));
        verify(userRepository, times(1)).findByFirstName(FIRST_NAME);
    }

    @Test
    void findByLastName() {
        List<User> users = new ArrayList<>();
        doReturn(Optional.of(users))
                .when(userRepository)
                .findByLastName(LAST_NAME);
        userService.findByLastName(LAST_NAME);
        verify(userRepository, times(1)).findByLastName(LAST_NAME);
    }

    @Test
    void findByLastName_whenUserNotFound() {
        Assertions.assertThrows(UserNotFoundException.class,
                () -> userService.findByLastName(LAST_NAME));
        verify(userRepository, times(1)).findByLastName(LAST_NAME);
    }

    @Test
    void createNewUser() {
        UserDto userDto = new UserDto();
        userDto.setEmail(EMAIL);
        userDto.setPhoneNumber(PHONE_NUMBER);
        userDto.setFirstName(FIRST_NAME);
        userDto.setLastName(LAST_NAME);
        userDto.setPassword(PASSWORD);

        User user = new User();
        doReturn(user)
                .when(mapper)
                .toUser(userDto);

        userService.createNewUser(userDto);


        verify(passwordEncoder, times(1)).encode(userDto.getPassword());
        verify(userRepository, times(1)).save(user);
        verify(mapper, times(1)).toUser(userDto);
    }

    @Test
    void updateUser() {
        UserDto userDto = new UserDto();
        userDto.setEmail(EMAIL);
        userDto.setPhoneNumber(PHONE_NUMBER);
        userDto.setFirstName(FIRST_NAME);
        userDto.setLastName(LAST_NAME);
        userDto.setPassword(PASSWORD);

        User user = new User();
        doReturn(Optional.of(user))
                .when(userRepository)
                .findByEmail(OLD_EMAIL);

        User newUser = new User();
        doReturn(newUser)
                .when(mapper)
                .toUser(userDto);

        userService.updateUser(OLD_EMAIL, userDto);

        verify(passwordEncoder, times(1)).encode(userDto.getPassword());
        verify(mapper, times(1)).toUser(userDto);
        verify(userRepository, times(1)).save(newUser);
    }

    @Test
    void deleteUser() {
        User user = new User();
        doReturn(Optional.of(user))
                .when(userRepository)
                .findByEmail(EMAIL);

        userService.deleteUser(EMAIL);

        verify(userRepository, times(1)).findByEmail(EMAIL);
        verify(userRepository, times(1)).delete(user);
    }

    @Test
    void deleteUser_whenUserNotFound() {
        doReturn(Optional.empty())
                .when(userRepository)
                .findByEmail(EMAIL);

        Assertions.assertThrows(UserNotFoundException.class,
                () -> userService.deleteUser(EMAIL));
        verify(userRepository, times(1)).findByEmail(EMAIL);
    }
}

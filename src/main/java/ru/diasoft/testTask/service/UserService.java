package ru.diasoft.testTask.service;


import ru.diasoft.testTask.data.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> findAll();

    UserDto findByEmail(String email);

    UserDto findByPhoneNumber(String phoneNumber);

    List<UserDto> findByFirstName(String FirstName);

    List<UserDto> findByLastName(String LastName);

    void createNewUser(UserDto userDto);

    void updateUser(String oldEmail, UserDto userDto);

    void deleteUser(String email);

}

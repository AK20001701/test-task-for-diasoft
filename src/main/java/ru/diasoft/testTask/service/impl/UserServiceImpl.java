package ru.diasoft.testTask.service.impl;

import org.springframework.stereotype.Service;
import ru.diasoft.testTask.data.dto.UserDto;
import ru.diasoft.testTask.data.repository.UserRepository;
import ru.diasoft.testTask.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<UserDto> findAll() {
        return null;
    }

    @Override
    public UserDto findByEmail(String email) {
        return null;
    }

    @Override
    public UserDto findByPhoneNumber(String phoneNumber) {
        return null;
    }

    @Override
    public List<UserDto> findByFirstName(String FirstName) {
        return null;
    }

    @Override
    public List<UserDto> findByLastName(String LastName) {
        return null;
    }

    @Override
    public void createNewUser(String email) {

    }

    @Override
    public void updateUser(String email) {

    }

    @Override
    public void deleteUser(String email) {

    }
}

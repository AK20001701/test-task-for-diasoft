package ru.diasoft.testTask.data.mapper.impl;

import org.springframework.stereotype.Component;
import ru.diasoft.testTask.data.dto.UserDto;
import ru.diasoft.testTask.data.entity.User;
import ru.diasoft.testTask.data.mapper.UserMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public User toUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        user.setRegistrationDate(userDto.getRegistrationDate());
        return user;
    }

    @Override
    public UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPassword(user.getPassword());
        userDto.setRegistrationDate(user.getRegistrationDate());
        return userDto;
    }

    @Override
    public List<User> toUserList(List<UserDto> userDtoList) {
        return userDtoList.stream().map(this::toUser).collect(Collectors.toList());
    }

    @Override
    public List<UserDto> toUserDtoList(List<User> userList) {
        return userList.stream().map(this::toUserDto).collect(Collectors.toList());
    }

}

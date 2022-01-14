package ru.diasoft.testTask.data.mapper;

import ru.diasoft.testTask.data.dto.UserDto;
import ru.diasoft.testTask.data.entity.User;

import java.util.List;

public interface UserMapper {
    User toUser(UserDto userDto);

    UserDto toUserDto(User user);

    List<User> toUserList(List<UserDto> userDtoList);

    List<UserDto> toUserDtoList(List<User> userList);
}

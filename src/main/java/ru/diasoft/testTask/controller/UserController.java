package ru.diasoft.testTask.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.diasoft.testTask.data.dto.UserDto;
import ru.diasoft.testTask.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add-user")
    public void addUser(@RequestBody UserDto userDto) {
        userService.createNewUser(userDto);
    }

    @GetMapping("/all-users")
    public List<UserDto> allUsers() {
        System.out.println("qweqwe");
        return userService.findAll();
    }

}

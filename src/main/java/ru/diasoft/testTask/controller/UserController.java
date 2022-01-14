package ru.diasoft.testTask.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.diasoft.testTask.data.dto.UserDto;
import ru.diasoft.testTask.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add-user")
    public void addUser(@RequestBody UserDto userDto) {
        logger.info("Add user");
        userService.createNewUser(userDto);
    }

    @GetMapping("/find-by-email")
    public UserDto findByEmail(@RequestParam(name = "email") String email) {
        logger.info("Find by email");
        return userService.findByEmail(email);
    }

    @GetMapping("/find-by-phone-number")
    public UserDto findByPhoneNumber(@RequestParam(name = "phoneNumber") String phoneNumber) {
        logger.info("Find by phone number");
        return userService.findByPhoneNumber(phoneNumber);
    }

    @GetMapping("/find-by-first-name")
    public List<UserDto> findByFirstName(@RequestParam(name = "firstName") String firstName) {
        logger.info("Find by first name");
        return userService.findByFirstName(firstName);
    }

    @GetMapping("/find-by-last-name")
    public List<UserDto> findByLastName(@RequestParam(name = "lastName") String lastName) {
        logger.info("Find by last name");
        return userService.findByLastName(lastName);
    }

    @GetMapping("/all-users")
    public List<UserDto> allUsers() {
        logger.info("All users");
        return userService.findAll();
    }

    @PostMapping("/update-user")
    public void updateUser(@RequestBody UserDto userDto, @RequestParam(name = "oldEmail") String oldEmail) {
        logger.info("Update user");
        userService.updateUser(oldEmail, userDto);
    }

    @GetMapping("/delete-user")
    public void deleteUser(@RequestParam(name = "email") String email) {
        logger.info("Delete user");
        userService.deleteUser(email);
    }


}

package ru.diasoft.testTask.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceImplTest {

    private final UserService userService;

    public UserServiceImplTest(UserService userService) {
        this.userService = userService;
    }

    @Test
    void contextLoads() {
    }

}

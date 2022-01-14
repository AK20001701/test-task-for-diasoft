package ru.diasoft.testTask.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("Users not found");
    }

    public UserNotFoundException(String msg) {
        super("User with " + msg + " not found");
    }
}

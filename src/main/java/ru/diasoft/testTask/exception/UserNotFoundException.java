package ru.diasoft.testTask.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String msg) {
        super("User with " + msg + " not found");
    }
}

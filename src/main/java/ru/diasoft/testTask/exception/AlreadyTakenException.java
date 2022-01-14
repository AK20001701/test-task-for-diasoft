package ru.diasoft.testTask.exception;

public class AlreadyTakenException extends RuntimeException {
    public AlreadyTakenException(String msg) {
        super("This " + msg + " already taken");
    }
}

package ru.kata.spring.boot_security.demo.exceptions;

public class CheckUserIDForYouException extends RuntimeException{
    public CheckUserIDForYouException(String message) {
        super(message);
    }
}

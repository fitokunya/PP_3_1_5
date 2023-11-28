package ru.kata.spring.boot_security.demo.Exceptions;

public class CheckUserIDForYouException extends RuntimeException{
    public CheckUserIDForYouException(String message) {
        super(message);
    }
}

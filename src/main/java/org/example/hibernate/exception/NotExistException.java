package org.example.hibernate.exception;

public class NotExistException extends Exception {
    public NotExistException(String message) {
        super(message);
    }
}
package org.example.hibernate.exception;

public class DuplicateException extends Exception {
    public DuplicateException(String message) {
        super(message);
    }
}
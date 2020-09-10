package com.example.general.util.exception;

public class NotFoundException extends Exception {

    public NotFoundException(String message) {
        super(message);
    }

    public static void check(boolean ex, String message) throws NotFoundException {
        if (ex) {
            throw new NotFoundException(message);
        }
    }
}

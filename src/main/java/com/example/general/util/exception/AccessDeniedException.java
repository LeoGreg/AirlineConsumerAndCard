package com.example.general.util.exception;

public class AccessDeniedException extends Exception {

    public AccessDeniedException(String message) {
        super(message);
    }

    public static void check(boolean ex, String message) throws AccessDeniedException {
        if (ex) {
            throw new AccessDeniedException(message);
        }
    }
}

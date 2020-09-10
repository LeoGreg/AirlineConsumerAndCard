package com.example.general.util.exception;
public class DuplicateException extends Exception {

    public DuplicateException(String message) {
        super(message);
    }

    public static void check(boolean ex, String message) throws DuplicateException {
        if (ex) {
            throw new DuplicateException(message);
        }
    }
}
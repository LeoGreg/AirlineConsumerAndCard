package com.example.general.util.exception;


public class UnverifiedException extends Exception {

    public UnverifiedException(String message) {
        super(message);
    }

    public static void check(boolean ex, String message) throws UnverifiedException {
        if (ex) {
            throw new UnverifiedException(message);
        }
    }
}
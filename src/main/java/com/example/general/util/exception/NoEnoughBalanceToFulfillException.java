package com.example.general.util.exception;

public class NoEnoughBalanceToFulfillException extends Exception {

    public NoEnoughBalanceToFulfillException(String message) {
        super(message);
    }

    public static void check(boolean ex, String message) throws NoEnoughBalanceToFulfillException {
        if (ex) {
            throw new NoEnoughBalanceToFulfillException(message);
        }
    }
}

package com.sber.springmvc.exception;

public class CardException extends Exception {

    private final String account;

    public CardException(String message, String account) {
        super(message);
        this.account = account;
    }

    public String getAccount() {
        return this.account;
    }
}

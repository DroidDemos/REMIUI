package com.xiaomi.account.exception;

public class SNSAccessTokenExpiredException extends Exception {
    public SNSAccessTokenExpiredException(String message) {
        super(message);
    }
}

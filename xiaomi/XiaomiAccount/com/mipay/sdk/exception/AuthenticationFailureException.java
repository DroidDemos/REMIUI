package com.mipay.sdk.exception;

public class AuthenticationFailureException extends Exception {
    public AuthenticationFailureException(String detailMessage) {
        super(detailMessage);
    }
}

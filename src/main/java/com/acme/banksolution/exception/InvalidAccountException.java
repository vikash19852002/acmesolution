package com.acme.banksolution.exception;

public class InvalidAccountException extends AcmeException{

    public InvalidAccountException(int code, String msg) {
        super(code, msg);
    }
}

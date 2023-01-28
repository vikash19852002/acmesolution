package com.acme.banksolution.exception;

public class InsufficientFundException extends AcmeException{

    public InsufficientFundException(int code, String msg) { super(code, msg); }
}

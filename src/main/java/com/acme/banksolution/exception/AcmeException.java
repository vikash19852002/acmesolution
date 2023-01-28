package com.acme.banksolution.exception;

/**
 * This is parent class for all acmeSolution project. Provide additional facility to send customize output to user
 *
 *  @author VKumar
 */
public class AcmeException extends RuntimeException{

    private int code;
    public AcmeException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}

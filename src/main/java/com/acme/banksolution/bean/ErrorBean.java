package com.acme.banksolution.bean;

import java.io.Serializable;

public class ErrorBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private int code;
    private String message;

    public ErrorBean(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ErrorBean{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}

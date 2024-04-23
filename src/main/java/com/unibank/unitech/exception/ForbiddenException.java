package com.unibank.unitech.exception;

public class ForbiddenException extends CommonException {

    public ForbiddenException(String message) {
        super("forbidden", message);
    }
}

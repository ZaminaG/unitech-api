package com.unibank.unitech.exception;

public class NotFoundException extends CommonException {
    public NotFoundException(String message) {
        super("not_found", message);
    }
}

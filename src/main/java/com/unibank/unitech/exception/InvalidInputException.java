package com.unibank.unitech.exception;

public class InvalidInputException extends CommonException {
    public InvalidInputException(String errorCode, String message) {
        super(errorCode, message);
    }
}

package com.unibank.unitech.exception;

public class AlreadyExistException extends CommonException {

    public AlreadyExistException(String message) {
        super("already_exists", message);
    }
}

package com.unibank.unitech.exception;

import com.unibank.unitech.exception.model.CommonErrorResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log4j2
public class ErrorHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AlreadyExistException.class)
    public CommonErrorResponse handleAlreadyExistsException(AlreadyExistException ex) {
        addErrorLog(HttpStatus.BAD_REQUEST, ex.getErrorCode(), ex.getMessage());
        return new CommonErrorResponse(ex.getErrorCode(), ex.getMessage());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(ForbiddenException.class)
    public CommonErrorResponse handleForbiddenException(ForbiddenException ex) {
        addErrorLog(HttpStatus.FORBIDDEN, ex.getErrorCode(), ex.getMessage());
        return new CommonErrorResponse(ex.getErrorCode(), ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidInputException.class)
    public CommonErrorResponse handleInvalidException(InvalidInputException ex) {
        addErrorLog(HttpStatus.BAD_REQUEST, ex.getErrorCode(), ex.getMessage());
        return new CommonErrorResponse(ex.getErrorCode(), ex.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public CommonErrorResponse handleInvalidException(NotFoundException ex) {
        addErrorLog(HttpStatus.NOT_FOUND, ex.getErrorCode(), ex.getMessage());
        return new CommonErrorResponse(ex.getErrorCode(), ex.getMessage());
    }

    private void addErrorLog(HttpStatus httpStatus, String errorCode, String errorMessage) {
        log.error("[Error] | HttpStatus: {} | Code: {} | Message: {}", httpStatus, errorCode, errorMessage);
    }
}

package com.unibank.unitech.exception.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommonErrorResponse {
    private String errorCode;
    private String message;
}

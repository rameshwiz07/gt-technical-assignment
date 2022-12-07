package com.gt.exception;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public class GTException extends RuntimeException {
    private final String code;
    private final String message;

}

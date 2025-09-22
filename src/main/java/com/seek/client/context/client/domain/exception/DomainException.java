package com.seek.client.context.client.domain.exception;

import lombok.Getter;

@Getter
public class DomainException extends RuntimeException {
    private TypeException exception;
    public DomainException(String message, Throwable cause, TypeException typeException) {
        super(message, cause);
    }

}

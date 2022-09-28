package com.ayi.curso.rest.serv.app.exceptions;

public class WriteAccessException extends ReadAccessException {
    public WriteAccessException(String message) {
        super(message);
    }
}

package com.victorsemperevidal.albumsandfotos.domain.exceptions;

public class ExternalClientException extends RuntimeException {
    private final int code;

    public ExternalClientException(int code, String message, Throwable t) {
        super(message, t);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}

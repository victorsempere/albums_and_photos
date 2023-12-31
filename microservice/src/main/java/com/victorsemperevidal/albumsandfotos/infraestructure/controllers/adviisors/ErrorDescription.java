package com.victorsemperevidal.albumsandfotos.infraestructure.controllers.adviisors;

import java.io.Serializable;

public class ErrorDescription implements Serializable {
    private final int code;
    private final String message;

    public ErrorDescription(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}

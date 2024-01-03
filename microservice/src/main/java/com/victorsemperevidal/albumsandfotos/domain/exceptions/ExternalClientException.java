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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + code;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ExternalClientException other = (ExternalClientException) obj;
        if (code != other.code)
            return false;
        return true;
    }

    
}

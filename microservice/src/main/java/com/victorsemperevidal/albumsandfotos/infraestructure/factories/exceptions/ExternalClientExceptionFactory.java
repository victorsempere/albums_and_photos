package com.victorsemperevidal.albumsandfotos.infraestructure.factories.exceptions;

import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.invoker.ApiException;
import com.victorsemperevidal.albumsandfotos.domain.exceptions.ExternalClientException;

@Service
public class ExternalClientExceptionFactory {

    public ExternalClientException getInstance(ApiException apiException) {
        return new ExternalClientException(apiException.getCode(),
                apiException.getMessage(), apiException);
    }

}

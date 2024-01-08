package com.victorsemperevidal.albumsandphotos.infraestructure.factories.exceptions;

import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandphotos.domain.exceptions.ExternalClientException;
import com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.invoker.ApiException;

@Service
public class ExternalClientExceptionFactory {

    public ExternalClientException getInstance(ApiException apiException) {
        return new ExternalClientException(apiException.getCode(),
                apiException.getMessage(), apiException);
    }

}

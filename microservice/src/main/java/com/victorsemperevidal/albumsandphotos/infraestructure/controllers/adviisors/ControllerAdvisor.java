package com.victorsemperevidal.albumsandphotos.infraestructure.controllers.adviisors;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestClientResponseException;

import com.victorsemperevidal.albumsandphotos.domain.exceptions.ExternalClientException;

@ControllerAdvice
public class ControllerAdvisor {

    @ResponseBody
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorDescription handleException(Exception e, Model model) {
        return new ErrorDescription(500, e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(RestClientResponseException.class)
    public ResponseEntity<String> handleRestClientResponseException(
            RestClientResponseException restClientResponseException) {
        HttpStatusCode statusCode = restClientResponseException.getStatusCode();
        String responseBody = restClientResponseException.getResponseBodyAsString();
        return ResponseEntity.status(statusCode).body(responseBody);
    }

    @ResponseBody
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ExternalClientException.class)
    public ErrorDescription handleException(ExternalClientException e, Model model) {
        return new ErrorDescription(e.getCode(), e.getMessage());
    }
}

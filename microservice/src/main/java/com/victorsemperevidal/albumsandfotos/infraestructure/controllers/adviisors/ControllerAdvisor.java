package com.victorsemperevidal.albumsandfotos.infraestructure.controllers.adviisors;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.victorsemperevidal.albumsandfotos.domain.exceptions.ExternalClientException;

@ControllerAdvice
public class ControllerAdvisor {

    @ResponseBody
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorDescription handleException(Exception e, Model model) {
        return new ErrorDescription(500, e.getMessage());
    }

    @ResponseBody
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ExternalClientException.class)
    public ErrorDescription handleException(ExternalClientException e, Model model) {
        return new ErrorDescription(e.getCode(), e.getMessage());
    }
}

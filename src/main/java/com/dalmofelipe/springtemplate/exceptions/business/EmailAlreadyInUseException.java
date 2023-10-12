package com.dalmofelipe.springtemplate.exceptions.business;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.dalmofelipe.springtemplate.exceptions.BaseRuntimeException;

@ResponseStatus(HttpStatus.CONFLICT)
public class EmailAlreadyInUseException extends BaseRuntimeException {
    private static final String KEY = "email.already.in.use";

    public EmailAlreadyInUseException() {
        super();
    }

    @Override
    public String getExceptionKey() {
        return KEY;
    }
}

package com.dalmofelipe.springtemplate.exceptions.business;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.dalmofelipe.springtemplate.exceptions.BaseRuntimeException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExampleNameRuleException extends BaseRuntimeException {
    private static final String KEY = "example.name.rule";

    public ExampleNameRuleException() {
        super();
    }

    @Override
    public String getExceptionKey() {
        return KEY;
    }
}

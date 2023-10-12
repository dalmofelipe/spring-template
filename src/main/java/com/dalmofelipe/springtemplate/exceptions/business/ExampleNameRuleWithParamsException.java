package com.dalmofelipe.springtemplate.exceptions.business;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.dalmofelipe.springtemplate.exceptions.BaseRuntimeException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExampleNameRuleWithParamsException extends BaseRuntimeException {
    private static final String KEY = "example.name.rule.with.params";

    public ExampleNameRuleWithParamsException(String value) {
        super(Map.of("value", value));
    }

    @Override
    public String getExceptionKey() {
        return KEY;
    }
}
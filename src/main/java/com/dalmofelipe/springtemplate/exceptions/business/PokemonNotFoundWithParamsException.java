package com.dalmofelipe.springtemplate.exceptions.business;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.dalmofelipe.springtemplate.exceptions.BaseRuntimeException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PokemonNotFoundWithParamsException extends BaseRuntimeException {
    private static final String KEY = "pokemon.not.found.with.params";

    public PokemonNotFoundWithParamsException(String value) {
        super(Map.of("value", value));
    }

    @Override
    public String getExceptionKey() {
        return KEY;
    }
}
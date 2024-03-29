package com.dalmofelipe.springtemplate.exceptions.business;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.dalmofelipe.springtemplate.exceptions.BaseRuntimeException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PokemonNotFoundException extends BaseRuntimeException {
    private static final String KEY = "pokemon.not.found";

    public PokemonNotFoundException() {
        super();
    }

    @Override
    public String getExceptionKey() {
        return KEY;
    }
}

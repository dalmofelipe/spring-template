package com.dalmofelipe.springtemplate.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dalmofelipe.springtemplate.services.OpenMovieDbRestTemplateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@RequestMapping(value = "/open-movie")
public class OpenMovieEndpoints {
    
    @Autowired
    private OpenMovieDbRestTemplateService openMovieDbRestTemplateService;

    
    @GetMapping("/rest-template/{movieTittle}")
    public ResponseEntity<Object> searchMovie(
            @PathVariable("movieTittle") String movieTittle) 
                throws JsonMappingException, JsonProcessingException {
        return openMovieDbRestTemplateService.searchMovie(movieTittle);
    }
}

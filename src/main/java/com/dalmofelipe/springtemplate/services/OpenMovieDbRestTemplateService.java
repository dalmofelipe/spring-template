package com.dalmofelipe.springtemplate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dalmofelipe.springtemplate.dtos.OpenMovieDTO;
import com.dalmofelipe.springtemplate.dtos.OpenMovieErrorDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.Gson;

@SuppressWarnings("null")
@Service
public class OpenMovieDbRestTemplateService {

    @Autowired
    private Environment env;

    @Value("${my.moviedb.token:default-class}")
    private String omdbToken;
    private String baseURL;
    private RestTemplate restTemplate;

    {
        System.out.printf(">>> TOKEN class/props >>> %s\n", this.omdbToken);
        this.restTemplate = new RestTemplate();
        this.baseURL = "http://www.omdbapi.com/";
    }

    public ResponseEntity<Object> searchMovie(String title) 
            throws JsonMappingException, JsonProcessingException {
        
        var token = env.getProperty("MOVIEDB_TOKEN", "INFORME_UMA_TOKEN");
        var url = this.baseURL +"?t="+ title +"&r=json&type=movie&apikey="+ token;

        var jsonStringResponse = this.restTemplate
            .getForEntity(url, String.class).getBody().toString();

        // System.out.printf("\n>>> MovieJSON >>> %s\n\n", jsonStringResponse);

        Gson gson = new Gson();
        OpenMovieDTO openMovie = gson
            .fromJson(jsonStringResponse, OpenMovieDTO.class);

        // ObjectMapper objectMapper = new ObjectMapper();
        // OpenMovieDTO openMovie = objectMapper
        //     .readValue(jsonStringResponse, OpenMovieDTO.class);

        if(openMovie.getResponse().equals("False")) {
            OpenMovieErrorDTO error = new OpenMovieErrorDTO();
            error.setResponse("False");
            error.setError("Movie or serie not found!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }

        return ResponseEntity.ok().body(openMovie);
    }

}
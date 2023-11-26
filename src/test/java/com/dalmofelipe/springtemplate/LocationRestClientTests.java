package com.dalmofelipe.springtemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

public class LocationRestClientTests 
{
    private String baseURL = "https://ipapi.co/json";
    private final RestClient restClient;

    public LocationRestClientTests()
    {
        restClient = RestClient.builder()
            .baseUrl(baseURL)
            .build();
    }
    
    @Test
    void shouldBeGetLocationByRestClientWithSuccess() 
        throws JsonMappingException, JsonProcessingException 
    {
        String response = restClient.get().retrieve().body(String.class);
        
        assertNotNull(response);
        assertNotEquals(response.length(), 0L);

        DocumentContext documentContext = JsonPath.parse(response);

        ObjectMapper mapper = new ObjectMapper();
        
        JsonNode jsonObj = mapper.readTree(response);

        assertEquals(documentContext.read("$.ip"), jsonObj.get("ip").toString().replace("\"", ""));
    }
}

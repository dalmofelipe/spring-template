package com.dalmofelipe.springtemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class LocationRestTemplateTests {

    private String baseURL = "https://ipapi.co/json";

    @Autowired
    TestRestTemplate restTemplate;


    @Test
    void shouldBeGetLocationByRestTemplateWithSuccessTest() 
        throws JsonMappingException, JsonProcessingException {
        
        ResponseEntity<String> response = restTemplate.getForEntity(baseURL, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());

        ObjectMapper mapper = new ObjectMapper();
        
        JsonNode jsonObj = mapper.readTree(response.getBody());

        assertEquals(documentContext.read("$.ip"), jsonObj.get("ip").toString().replace("\"", ""));
    }

}

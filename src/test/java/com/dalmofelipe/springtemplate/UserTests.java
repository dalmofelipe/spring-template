package com.dalmofelipe.springtemplate;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.dalmofelipe.springtemplate.dtos.UserCreateDTO;
import com.dalmofelipe.springtemplate.dtos.UserOutputDTO;
import com.dalmofelipe.springtemplate.enums.UserRole;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;


@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
class UserTests {

	@Autowired
    TestRestTemplate restTemplate;
    
	@Test
    void shouldReturnAUserSavedFromSQL() {
        ResponseEntity<String> response = restTemplate
            //.withBasicAuth("Dalmo", "123123")
            .getForEntity("/api/users/df6e663a-f302-444a-a7fa-5ebecfa160de", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        String id = documentContext.read("$.id");
        assertThat(id).isEqualTo("df6e663a-f302-444a-a7fa-5ebecfa160de");

		String eamil = documentContext.read("$.email");
        assertThat(eamil).isEqualTo("dalmo@email.com");
    }

    @Test
    void shouldReturnAUserSavedFromSQLMappedToModel() {
        ResponseEntity<UserOutputDTO> response = restTemplate
            .getForEntity("/api/users/df6e663a-f302-444a-a7fa-5ebecfa160de", UserOutputDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        UserOutputDTO user = response.getBody();
        
        if(user == null) return;

        assertThat(user.getId().toString()).isEqualTo("df6e663a-f302-444a-a7fa-5ebecfa160de");
        assertThat(user.getEmail()).isEqualTo("dalmo@email.com");
    }

    @Test
    void shouldCreateNewUser() throws JsonMappingException, JsonProcessingException {
        var user = new UserCreateDTO();
        user.setName("FromTest");
        user.setEmail("test@email.com");
        user.setPassword("123test");
        user.setRole(UserRole.PLENO);

        ResponseEntity<Void> createResponse = restTemplate
            .postForEntity("/api/users", user, Void.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        ResponseEntity<String> getResponse = restTemplate
            .getForEntity("/api/users/search?name=From", String.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        ObjectMapper mapper = new ObjectMapper();
        
        JsonNode jsonObj = mapper.readTree(getResponse.getBody());
        ArrayNode arrayNode = (ArrayNode) jsonObj.get("data");
        
        assertThat(arrayNode.get(0).get("id").toString()).isNotNull();
        assertThat(arrayNode.get(0).get("email").toString().replaceAll("\"", ""))
            .isEqualTo(user.getEmail());
    }

}

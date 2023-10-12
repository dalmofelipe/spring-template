package com.dalmofelipe.springtemplate.endpoints;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dalmofelipe.springtemplate.dtos.UserCreateDTO;
import com.dalmofelipe.springtemplate.dtos.UserUpdateDTO;
import com.dalmofelipe.springtemplate.responses.ResponseHandler;
import com.dalmofelipe.springtemplate.services.UserService;

import jakarta.validation.Valid;


@RestController
@RequestMapping(value = "/users")
public class UserEndpoints {

    @Autowired
    private UserService userService;

    // @PathVariable = req.param
    // @RequestBody  = req.body
    // @RequestParam = req.query

    @GetMapping
    public ResponseEntity<Object> listUsers() {
        return ResponseHandler.generateResponse("read all", HttpStatus.OK, userService.listAll());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserCreateDTO> showUser(@PathVariable UUID userId) {
        return ResponseEntity.ok().body(userService.showUser(userId));
    }

    @PostMapping
    public ResponseEntity<Object> saveUser(@Valid @RequestBody UserCreateDTO userDTO) {
        return ResponseHandler.generateResponse("create", HttpStatus.OK, userService.save(userDTO));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Object> updateUser(@Validated @RequestBody UserUpdateDTO updateDTO, @PathVariable UUID userId) {
        return ResponseHandler.generateResponse("update", HttpStatus.OK, userService.update(userId, updateDTO));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable UUID userId) {
        try {
            userService.remove(userId);
            return ResponseHandler.generateResponse("delete", HttpStatus.NO_CONTENT, null);
        } catch(Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }

    @GetMapping("/filter")
    public String filterUsers(
            @RequestParam(required = false) String role,
            @RequestParam(required = false) List<String> words,
            @RequestParam(required = false) List<Double> numbers) {

        StringBuilder ulHtml = new StringBuilder();

        if(words != null) {
            ulHtml.append("<h1>Palavras</h1><ul>");
            for(String word : words) {
                ulHtml.append("<li>").append(word).append("</li>");
            }
            ulHtml.append("</ul><br><br>");
        }

        if(numbers != null) {
            ulHtml.append("<h1>Numeros em Dobro</h1><ul>");
            for(var n:numbers) {
                ulHtml.append("<li>").append(n*2).append("</li>");
            }
            ulHtml.append("</ul>");
        }

        return "Olá " + role + "! " + ulHtml;
    }
}

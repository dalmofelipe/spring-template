package com.dalmofelipe.springtemplate.endpoints;

import com.dalmofelipe.springtemplate.dtos.UserDto;
import com.dalmofelipe.springtemplate.responses.ResponseHandler;
import com.dalmofelipe.springtemplate.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public ResponseEntity<UserDto> showUser(@PathVariable UUID userId) {
        return ResponseEntity.ok().body(userService.showUser(userId));
    }

    @PostMapping
    public ResponseEntity<Object> saveUser(@RequestBody UserDto userDto) {
        return ResponseHandler.generateResponse("create",HttpStatus.OK, userService.save(userDto));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Object> updateUser(@RequestBody UserDto userDto, @PathVariable UUID userId) {
        return ResponseHandler.generateResponse("update", HttpStatus.OK, userService.update(userId, userDto));
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
            @RequestParam(required = false) List<String> states,
            @RequestParam(required = false) List<Integer> numbers) {

        StringBuilder ulHtml = new StringBuilder();

        if(states != null) {
            ulHtml.append("<h1>Estados da União</h1><ul>");
            for(var state:states) {
                ulHtml.append("<li>").append(state).append("</li>");
            }
            ulHtml.append("</ul><br><br>");
        }

        if(numbers != null) {
            ulHtml.append("<h1>Valores</h1><ul>");
            for(var n:numbers) {
                ulHtml.append("<li>").append(n*2).append("</li>");
            }
            ulHtml.append("</ul>");
        }

        return "Olá " + role + "! " + ulHtml;
    }

}

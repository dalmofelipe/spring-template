package com.dalmofelipe.springtemplate.endpoints;

import com.dalmofelipe.springtemplate.dtos.UserDto;
import com.dalmofelipe.springtemplate.entities.UserModel;
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
    public ResponseEntity<List<UserDto>> listUsers() {
        return ResponseEntity.ok().body(userService.listAll());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> showUser(@PathVariable UUID userId) {
        return ResponseEntity.ok().body(userService.showUser(userId));
    }

    @PostMapping
    public ResponseEntity<UserModel> saveUser(@RequestBody UserDto userDto) {
        return ResponseEntity.status(201).body(userService.save(userDto));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserModel> updateUser(@RequestBody UserDto userDto,
            @PathVariable UUID userId) {
        return ResponseEntity.ok().body(userService.update(userId, userDto));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID userId) {
        userService.remove(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/filter")
    public String filterUsers(
            @RequestParam(required = false) String role, @RequestParam(required = false) List<String> states,
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

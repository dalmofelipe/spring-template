package com.dalmofelipe.springtemplate.endpoinsts;

import com.dalmofelipe.springtemplate.dtos.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/users")
public class UserEndpoints {

    // @PathVariable = req.param
    // @RequestBody  = req.body
    // @RequestParam = req.query

    @GetMapping
    public String listUsers() {
        return "retornar uma lista de usuários(as)";
    }

    @GetMapping("/{userId}")
    public String showUser(@PathVariable UUID userId) {
        return "exibe usuário(a) com ID = " + userId;
    }

    @PostMapping
    public ResponseEntity<String> saveUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok().body("salva usuário(a) " + userDto + " no banco de dados");
    }

    @PutMapping("/{userId}")
    public String updateUser(@RequestBody UserDto userDto, @PathVariable UUID userId) {
        return "atualiza dados do(a) usuário(a) com ID = " + userId;
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable UUID userId) {
        return "deleta o usuário com ID " + userId;
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

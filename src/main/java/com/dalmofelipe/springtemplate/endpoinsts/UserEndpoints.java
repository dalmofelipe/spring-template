package com.dalmofelipe.springtemplate.endpoinsts;

import com.dalmofelipe.springtemplate.dtos.UserDto;
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
    public String saveUser(@RequestBody UserDto userDto) {
        return "salva usuário(a) " + userDto + " no banco de dados";
    }

    @PutMapping("/{userId}")
    public String updateUser(@RequestBody UserDto userDto, @PathVariable UUID userId) {
        return "atualiza dados do(a) usuário(a) com ID = " + userId;
    }

    @GetMapping("/filter")
    public String filterUsers(
        @RequestParam(required = false) String role,
        @RequestParam(required = false) List<String> states
    ) {
        StringBuilder ulHtml = null;

        if(states != null) {
            ulHtml = new StringBuilder("<ul>");
            for(var state:states) {
                ulHtml.append("<li>").append(state).append("</li>");
            }
            ulHtml.append("</ul>");
        }

        return "retornando busca com filtros de funções " + role
            + " nos estados  " + ulHtml;
    }

}

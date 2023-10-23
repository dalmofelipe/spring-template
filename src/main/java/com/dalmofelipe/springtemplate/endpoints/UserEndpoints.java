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
import com.dalmofelipe.springtemplate.dtos.UserOutputDTO;
import com.dalmofelipe.springtemplate.dtos.UserUpdateDTO;
import com.dalmofelipe.springtemplate.enums.UserRole;
import com.dalmofelipe.springtemplate.exceptions.dtos.ApiErrorDTO;
import com.dalmofelipe.springtemplate.responses.ResponseHandler;
import com.dalmofelipe.springtemplate.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@Tag(name = "Users", description = "Endpoints para gestão de usuários")
@RestController
@RequestMapping(value = "/api/users")
public class UserEndpoints {

    @Autowired
    private UserService userService;

    // @PathVariable = req.param
    // @RequestBody  = req.body
    // @RequestParam = req.query

    @Operation(
        summary = "READ - Lista todos os usuários", 
        description = "Retorna todos os usuários sem paginação")
    @ApiResponse(
        responseCode = "200", 
        description = "Sempre retornará um ```ResponseHandler``` contendo o atributo **data** que é uma lista de usuários.")
    @GetMapping
    public ResponseEntity<Object> listUsers() {
        return ResponseHandler.generateResponse("list all", HttpStatus.OK, 
            userService.listAll());
    }
    
    @Operation(
        summary = "SEARCH - Rota para pesquisa de usuários por NAME e/ou ROLE", 
        description = "- Não é necessário informar o nome da ```role``` completa. "
            +"Ex. roles que começam com a letra J\n"
            +"- Todos usuários serão retornados, se nenhum parâmetro for informado"
    )
    @ApiResponse(
        responseCode = "200", 
        description = "Sempre retornará um ```ResponseHandler``` contendo o atributo **data** que é uma lista de usuários encontrados ou não."
    )
    @GetMapping("/search")
    public ResponseEntity<Object> searchUsers(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) UserRole role
    ) {
        return ResponseHandler.generateResponse("search by filters", HttpStatus.OK, 
            userService.searchWithFilters(name, role));
    }

    @Operation(summary = "READ - Busca um usuário pelo ID", description = "Retorna dados de um usuário")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200", 
            description = "Retorna um ```UserOutputDTO``` contendo os dados de um usuário."
        ),
        @ApiResponse(
            responseCode = "404", 
            description = "Se usuários não encontrado com ID informado.", 
            content = { 
                //content = { @Content(schema = @Schema()) }
                @Content(schema = @Schema(implementation = ApiErrorDTO.class), mediaType = "application/json") 
            }
        )
    })
    @GetMapping("/{userId}")
    public ResponseEntity<UserOutputDTO> showUser(@PathVariable UUID userId) {
        return ResponseEntity.ok().body(userService.showUser(userId));
    }

    @Operation(summary = "CREATE - Cria um novo usuário", description = "Retorna dados do novo usuário criado")
    @ApiResponses({
        @ApiResponse(
            responseCode = "201", 
            description = "Retorna um ```UserOutputDTO``` contendo os dados do novo usuário."
        ),
        @ApiResponse(
            responseCode = "400", 
            description = "Caso informe dados incorretos.", 
            content = { 
                //content = { @Content(schema = @Schema()) }
                @Content(schema = @Schema(implementation = ApiErrorDTO.class), mediaType = "application/json") 
            }
        )
    })
    @PostMapping
    public ResponseEntity<UserOutputDTO> saveUser(@Valid @RequestBody UserCreateDTO userDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userDTO).toDTO());
    }

    @Operation(summary = "UPDATE - Atualiza dados de um usuário", description = "Atualiza dados de um usuário")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200", 
            description = "Retorna um ```UserOutputDTO``` contendo os dados do novo usuário.",
            content = { 
                @Content(schema = @Schema(implementation = UserOutputDTO.class), mediaType = "application/json") 
            }
        ),
        @ApiResponse(
            responseCode = "400", 
            description = "Caso informe dados incorretos.", 
            content = { 
                @Content(schema = @Schema(implementation = ApiErrorDTO.class), mediaType = "application/json") 
            }
        ),
        @ApiResponse(
            responseCode = "404", 
            description = "Se usuários não encontrado com ID informado.", 
            content = { 
                //content = { @Content(schema = @Schema()) }
                @Content(schema = @Schema(implementation = ApiErrorDTO.class), mediaType = "application/json") 
            }
        )
    })
    @PutMapping("/{userId}")
    public ResponseEntity<Object> updateUser(
        @Validated @RequestBody UserUpdateDTO updateDTO, @PathVariable UUID userId
    ) {
        return ResponseHandler.generateResponse("updated", HttpStatus.OK, 
            userService.update(userId, updateDTO));
    }

    @Operation(
        summary = "DELETE - Excluir um usuário", 
        description = "Excluir um usuário"
    )
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable UUID userId) {
        userService.remove(userId);
    }

    @GetMapping("/filter") // este endpoints esta oculto pelo application.properties
    public String filterUsers(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) List<String> words,
        @RequestParam(required = false) List<Double> numbers
    ) {
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

        return "Olá " + name + "! " + ulHtml;
    }
}

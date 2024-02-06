package com.dalmofelipe.springtemplate.endpoints;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dalmofelipe.springtemplate.exceptions.business.PokemonNotFoundWithParamsException;
import com.dalmofelipe.springtemplate.records.PokemonRecord;
import com.dalmofelipe.springtemplate.services.PokeApiWebClientService;

@RestController
@RequestMapping("/poke-api")
public class PokeApiWebClientEndpoints {

    @Autowired
    PokeApiWebClientService pokeApiWebClientService;
    

    @GetMapping("/web-client")
    public ResponseEntity<PokemonRecord> searchPokemonByName(
        @RequestParam("pokeName") String pokeName
    ) {
        Optional<PokemonRecord> pokeOtp = this.pokeApiWebClientService
            .searchPokemonByName(pokeName);

        PokemonRecord pokemon = pokeOtp.orElseThrow(() -> 
            new PokemonNotFoundWithParamsException(pokeName));
        
        return ResponseEntity.ok().body(pokemon);
    }

}

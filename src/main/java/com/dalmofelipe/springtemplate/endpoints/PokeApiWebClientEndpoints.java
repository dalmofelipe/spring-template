package com.dalmofelipe.springtemplate.endpoints;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dalmofelipe.springtemplate.records.PokemonRecord;
import com.dalmofelipe.springtemplate.records.PokemonTinyRecord;
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
        
        return ResponseEntity.ok().body(pokeOtp.get());
    }


    @GetMapping("/web-client/tiny")
    public ResponseEntity<PokemonTinyRecord> searchPokemonByNameTiny(
        @RequestParam("pokeName") String pokeName
    ) {
        Optional<PokemonTinyRecord> pokeOtp = this.pokeApiWebClientService
            .searchPokemonByNameTiny(pokeName);
        
        return ResponseEntity.ok().body(pokeOtp.get());
    }

}

package com.dalmofelipe.springtemplate.services;

import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import com.dalmofelipe.springtemplate.exceptions.business.PokemonNotFoundException;
import com.dalmofelipe.springtemplate.exceptions.business.PokemonNotFoundWithParamsException;
import com.dalmofelipe.springtemplate.records.PokemonRecord;
import com.dalmofelipe.springtemplate.records.PokemonTinyRecord;

import reactor.core.publisher.Mono;

@SuppressWarnings("null")
@Service
public class PokeApiWebClientService {
    
    private final String pokemonUrl = "https://pokeapi.co/api/v2/pokemon/";
    private final int size = 2 * 1024 * 1024;
    private final ExchangeStrategies strategies = ExchangeStrategies
        .builder()
        .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(size))
        .build();

    public static ExchangeFilterFunction errorHandler() {

        return ExchangeFilterFunction
                .ofResponseProcessor(clientResponse -> {

            if (clientResponse.statusCode().is5xxServerError()) {
                return clientResponse.bodyToMono(String.class).flatMap(errorBody -> 
                    Mono.error(new RuntimeException("Error Interno!")));
            } 
            else if (clientResponse.statusCode().is4xxClientError()) {
                return clientResponse.bodyToMono(String.class).flatMap(errorBody ->
                    Mono.error(new PokemonNotFoundException()));
            }
            else {
                return Mono.just(clientResponse);
            }
        });
    }

    private WebClient newClient(String baseUrl) {
        return WebClient
            .builder()
            .exchangeStrategies(this.strategies)
            // .filter(errorHandler())
            .baseUrl(baseUrl)
            .build();
    }
    
    @Cacheable("pokemons")
    public Optional<PokemonRecord> searchPokemonByName(String name) {
        return this.newClient(pokemonUrl)
            .get()
            .uri(builder -> builder.path(name.toLowerCase()).build())
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            //.subscribe()
            .bodyToMono(PokemonRecord.class)
            .onErrorResume(e -> Mono.error(new PokemonNotFoundWithParamsException(name)))
            .blockOptional();
    }

    @Cacheable("pokemons-tiny")
    public Optional<PokemonTinyRecord> searchPokemonByNameTiny(String pokeName) {
        return this.newClient(pokemonUrl)
            .get()
            .uri(builder -> builder.path(pokeName.toLowerCase()).build())
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            //.subscribe()
            .bodyToMono(PokemonTinyRecord.class)
            .onErrorResume(e -> Mono.error(new PokemonNotFoundWithParamsException(pokeName)))
            .blockOptional();
    }

}

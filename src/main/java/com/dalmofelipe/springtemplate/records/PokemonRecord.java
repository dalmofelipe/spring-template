package com.dalmofelipe.springtemplate.records;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

public record PokemonRecord(

    List<JsonNode> abilities,
    Integer baseExperience,
    List<JsonNode> forms,

    @JsonProperty("game_indices")
    List<JsonNode> gameIndices,
    Integer height,

    @JsonProperty("held_items")
    List<JsonNode> heldItems,
    
    Integer id,

    @JsonProperty("is_default")
    Boolean isDefault,

    @JsonProperty("location_area_encounters")
    String locationAreaEncounters,

    List<JsonNode> moves,
    String name,
    Integer order,

    @JsonProperty("past_abilities")
    List<JsonNode> pastAbilities,

    @JsonProperty("past_types")
    List<JsonNode> pastTypes,

    JsonNode species,
    JsonNode sprites,
    List<JsonNode> stats,
    List<JsonNode> types,
    Integer weight

) {}

package com.dalmofelipe.springtemplate.records;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PokeSpriteRecord(

    @JsonProperty("front_default")
    String frontDefault,

    @JsonProperty("front_shiny")
    String frontShiny

) {}

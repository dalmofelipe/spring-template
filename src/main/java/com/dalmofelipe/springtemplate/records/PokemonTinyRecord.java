package com.dalmofelipe.springtemplate.records;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public record PokemonTinyRecord(

    Integer id,
    String name,
    Integer height,
    Integer order,
    Integer weight,
    List<PokeMoveRecord> moves,
    PokeSpriteRecord sprites,
    List<JsonNode> stats,
    List<JsonNode> types

) {
    public List<Object> getMoves() {
        return this.moves().stream()
            .map(m -> m.move().get("name")).toList();
    }

    public List<JsonNode> getStats() {
        return this.stats().stream()
            .map(statJson -> {
                ObjectMapper mapper = new ObjectMapper();

                JsonNode newJson = mapper.createObjectNode()
                    .put("name", statJson.get("stat").get("name").asText())
                    .put("base_default", statJson.get("base_stat").asInt());
                
                return newJson;
            })
            .toList();
    }

    public List<String> getTypes() {
        return this.types().stream()
            .map(statJson -> statJson.get("type").get("name").toString().replace("\"", ""))
            .toList();
    }
}

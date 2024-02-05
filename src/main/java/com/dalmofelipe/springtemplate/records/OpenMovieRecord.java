package com.dalmofelipe.springtemplate.records;

import com.fasterxml.jackson.databind.JsonNode;

public record OpenMovieRecord(
    String Title,
    String Year,
    String Rated,
    String Released,
    String Runtime,
    String Genre,
    String Director,
    String Writer,
    String Actors,
    String Plot,
    String Language,
    String Country,
    String Awards,
    String Poster,
    JsonNode Ratings,
    String Metascore,
    String imdbRating,
    String imdbVotes,
    String imdbID,
    String Type,
    String DVD,
    String BoxOffice,
    String Production,
    String Website,
    String Response,
    String Error
) {}

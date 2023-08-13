package com.dalmofelipe.springtemplate.records;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public record UserRecord(String name, String email, @JsonIgnoreProperties String password) {
    @Override
    public String toString() {
        return "[Name:"+ this.name +", E-mail: "+ this.email +"]";
    }
}

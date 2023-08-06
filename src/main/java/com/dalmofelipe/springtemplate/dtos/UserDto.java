package com.dalmofelipe.springtemplate.dtos;

public record UserDto(String name, String email, String password) {
    @Override
    public String toString() {
        return "[Name:"+ this.name +", E-mail: "+ this.email +"]";
    }
}

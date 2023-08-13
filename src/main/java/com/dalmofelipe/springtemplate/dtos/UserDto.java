package com.dalmofelipe.springtemplate.dtos;

import com.dalmofelipe.springtemplate.entities.UserModel;
import lombok.Data;

import java.util.UUID;

@Data
public class UserDto {

    private UUID id;
    private String name;
    private String email;
    private String password;

    public UserDto() {}

    public UserDto(UUID id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public UserModel toModel() {
        UserModel model = new UserModel();
        model.setId(this.id);
        model.setName(this.name);
        model.setEmail(this.email);
        model.setPassword(this.password);
        return model;
    }

    @Override
    public String toString() {
        return "[Name:"+ this.name +", E-mail: "+ this.email +"]";
    }
}

package com.dalmofelipe.springtemplate.dtos;

import org.springframework.beans.BeanUtils;

import com.dalmofelipe.springtemplate.entities.UserModel;
import com.dalmofelipe.springtemplate.enums.UserRole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String name;
    private String email;
    private String password;
    private UserRole role;

    // para uso no repositoty - findAllUsers
    public UserDTO(String name, String email, UserRole role) {
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public UserModel toModel() {
        UserModel model = new UserModel();
        BeanUtils.copyProperties(this, model);
        return model;
    }

    @Override
    public String toString() {
        return "[Name:"+ this.name +", E-mail: "+ this.email +"]";
    }
}

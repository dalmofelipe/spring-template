package com.dalmofelipe.springtemplate.dtos;

import java.util.UUID;

import org.springframework.beans.BeanUtils;

import com.dalmofelipe.springtemplate.entities.UserModel;
import com.dalmofelipe.springtemplate.enums.UserRole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOutputDto {

    private UUID id;
    private String name;
    private String email;
    private UserRole role;

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

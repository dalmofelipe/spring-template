package com.dalmofelipe.springtemplate.dtos;

import org.springframework.beans.BeanUtils;

import com.dalmofelipe.springtemplate.entities.UserModel;
import com.dalmofelipe.springtemplate.enums.UserRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDTO {

    @Size(min = 3, max = 50, message = "{user.name.size}")
    private String name;

    @Email(message = "{user.email.invalid}")
    private String email;
    
    @Size(min = 6, max = 20, message = "{user.password.size}")
    private String password;
    
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

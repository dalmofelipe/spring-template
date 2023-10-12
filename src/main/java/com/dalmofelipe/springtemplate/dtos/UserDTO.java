package com.dalmofelipe.springtemplate.dtos;

import org.springframework.beans.BeanUtils;

import com.dalmofelipe.springtemplate.entities.UserModel;
import com.dalmofelipe.springtemplate.enums.UserRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @Size(min = 3, max = 50, message = "{user.name.size}")
    @NotNull(message = "{user.name.is.mandatory}")
    private String name;

    @Email(message = "{user.email.invalid}")
    @NotBlank(message = "{user.email.is.mandatory}")
    private String email;
    
    @Size(min = 6, max = 20, message = "{user.password.size}")
    @NotNull(message = "{user.password.is.mandatory}")
    private String password;
    
    private UserRole role;

    {
        this.role = UserRole.JUNIOR;
    }

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

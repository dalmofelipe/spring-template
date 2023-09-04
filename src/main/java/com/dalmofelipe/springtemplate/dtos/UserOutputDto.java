package com.dalmofelipe.springtemplate.dtos;

import java.util.UUID;

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

    @Override
    public String toString() {
        return "[Name:"+ this.name +", E-mail: "+ this.email +"]";
    }
}

package com.dalmofelipe.springtemplate.enums;

import lombok.Getter;

@Getter
public enum UserRole {

    JUNIOR("JUNIOR"), PLENO("PLENO"), SENIOR("SENIOR"), JEDI("JEDI");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

}
package com.dalmofelipe.springtemplate.enums;

public enum UserRole {

    JUNIOR("JUNIOR"), PLENO("PLENO"), SENIOR("SENIOR"), JEDI("JEDI");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }

}
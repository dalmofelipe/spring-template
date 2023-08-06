package com.dalmofelipe.springtemplate.entities;

public enum UserRole {

    ADMIN("ADMIN"), MODERATOR("MODERATOR"), NORMAL("NORMAL");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
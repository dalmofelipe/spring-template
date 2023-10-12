package com.dalmofelipe.springtemplate.exceptions.dtos;

import lombok.Data;

@Data
public class ErrorDTO {
    
    private String key;
    private String message;

    public ErrorDTO() {  }

    public ErrorDTO(String key, String message) {
        this.key = key;
        this.message = message;
    }
}
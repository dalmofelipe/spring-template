package com.dalmofelipe.springtemplate.exceptions.dtos;

import java.time.LocalDateTime;
import java.util.Set;

import lombok.Data;

@Data
public class ApiErrorDTO {

    private LocalDateTime timestamp;
    private Integer status;
    private String code;
    private Set<ErrorDTO> errors;

    public ApiErrorDTO() {  }

    public ApiErrorDTO(LocalDateTime timestamp, Integer status, String code, Set<ErrorDTO> errors) {
        this.timestamp = timestamp;
        this.status = status;
        this.code = code;
        this.errors = errors;
    }
}

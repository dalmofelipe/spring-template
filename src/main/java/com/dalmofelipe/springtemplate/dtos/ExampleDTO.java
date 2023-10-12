package com.dalmofelipe.springtemplate.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ExampleDTO {

    @NotNull(message = "{required.validation}")
    private Long id;

    @NotBlank(message = "{required.validation}")
    @Size(min = 4, max = 30, message = "{size.validation}")
    private String name;

    public ExampleDTO() {  }

    public ExampleDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
package com.bcp1IO.fnac.dto.category;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record CategoryDTO(
        //@NotEmpty(message = "El Titulo no puede estar vacío")
        //@Size(min=3, max=20, message = "El titulo no puede tener menos de 3 caracteres o mas de 20")
        @NotEmpty(message = "The title cannot be empty.")
        @Size(min=3, max=20, message = "The title cannot be less than 3 characters or more than 20.")
        String title,

        @NotEmpty(message = "La Descripción no puede estar vacía")
        @Size(min=10, max=50, message = "La descripción no puede tener menos de 10 caracteres o mas de 50")
        String description
) {
}

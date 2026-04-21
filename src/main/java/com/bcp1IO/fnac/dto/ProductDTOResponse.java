package com.bcp1IO.fnac.dto;

public record ProductDTOResponse(
        String name,
        String description,
        double price,
        String categoryTitle
) {
}

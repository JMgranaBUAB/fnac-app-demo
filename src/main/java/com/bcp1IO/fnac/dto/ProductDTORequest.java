package com.bcp1IO.fnac.dto;

public record ProductDTORequest(
        String name,
        String description,
        double price,
        int categoryId
) {
}

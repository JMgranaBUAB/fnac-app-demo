package com.bcp1IO.fnac.dto;

import java.util.List;

public record ProductDTOResponse(
        String name,
        String description,
        double price,
        String categoryTitle,
        List<String> userNames
) {
}

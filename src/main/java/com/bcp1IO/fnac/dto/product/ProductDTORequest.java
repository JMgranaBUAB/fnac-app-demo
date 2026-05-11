package com.bcp1IO.fnac.dto.product;

import java.util.List;

public record ProductDTORequest(
        String name,
        String description,
        double price,
        int categoryId,
        List<Integer> userIds
) {
}

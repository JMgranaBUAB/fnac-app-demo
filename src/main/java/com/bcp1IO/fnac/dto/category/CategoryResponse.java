package com.bcp1IO.fnac.dto.category;

import java.util.List;

public record CategoryResponse(String name, List<String> products) {
}

package com.foalex.bookstore.dto.category;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record UpdateCategoryRequestDto(
        @NotBlank
        @Length(max = 32)
        String name,
        @Length(max = 512)
        String description) {
}

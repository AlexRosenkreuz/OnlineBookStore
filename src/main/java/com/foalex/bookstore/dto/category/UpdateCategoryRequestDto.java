package com.foalex.bookstore.dto.category;

import org.hibernate.validator.constraints.Length;

public record UpdateCategoryRequestDto(

        @Length(
                max = 30,
                message = "Name must be no longer than 30 characters."
        )
        String name,
        @Length(
                max = 500,
                message = "Description can't be longer than 500 characters."
        )
        String description) {
}

package com.foalex.bookstore.dto.book;

import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

public record UpdateBookRequestDto(
        @Length(
                max = 50,
                message = "Title must be no longer than 50 characters."
        )
        String title,
        @Length(
                max = 30,
                message = "Author's name must be no longer than 30 characters."
        )
        String author,
        @Length(
                max = 30,
                message = "ISBN must be no longer than 30 characters."
        )
        String isbn,
        @Positive(message = "Price must be a positive number.")
        BigDecimal price,
        @Length(
                max = 1000,
                message = "Description can't be longer than 1000 characters."
        )
        String description,
        @URL(message = "URL for the image is not valid.")
        String coverImage,
        List<@Positive Long> categoryIds) {
}

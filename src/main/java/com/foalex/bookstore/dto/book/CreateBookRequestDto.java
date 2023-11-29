package com.foalex.bookstore.dto.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

public record CreateBookRequestDto(
        @NotBlank(message = "You must specify the title.")
        @Length(
                max = 50,
                message = "Title must be no longer than 50 characters."
        )
        String title,
        @NotBlank(message = "You must specify the author.")
        @Length(
                max = 30,
                message = "Author's name must be no longer than 30 characters."
        )
        String author,
        @NotBlank(message = "You must specify ISBN.")
        @Length(
                max = 30,
                message = "ISBN must be no longer than 30 characters."
        )
        String isbn,
        @NotNull(message = "You must specify price.")
        @Positive(message = "Price must be a positive number.")
        BigDecimal price,
        @Length(
                max = 1000,
                message = "Description can't be longer than 1000 characters."
        )
        String description,
        @URL(message = "URL to the image is not valid.")
        String coverImage,
        @NotEmpty(message = "You must specify at least 1 category id.")
        List<@Positive Long> categoryIds) {
}

package com.foalex.bookstore.dto.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import org.hibernate.validator.constraints.Length;

public record BookDto(
        @NotBlank @Length(max = 128) String title,
        @NotBlank @Length(max = 128) String author,
        @NotBlank @Length(max = 128) String isbn,
        @NotNull @Positive BigDecimal price,
        @Length(max = 1024) String description,
        @Length(max = 128) String coverImage) {
}

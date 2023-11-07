package com.foalex.bookstore.dto.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.validator.constraints.Length;

public record CreateBookRequestDto(
        @NotBlank @Length(max = 128) String title,
        @NotBlank @Length(max = 128) String author,
        @NotBlank @Length(max = 128) String isbn,
        @NotNull @Positive BigDecimal price,
        @Length(max = 1024) String description,
        @Length(max = 128) String coverImage,
        @NotNull List<@Positive Long> categoryIds) {
}

package com.foalex.bookstore.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;

@Data
public class CreateBookRequestDto {
    @NotNull
    @Length(max = 128)
    private String title;

    @NotNull
    @Length(max = 128)
    private String author;

    @NotNull
    @UniqueElements
    @Length(max = 128)
    private String isbn;

    @NotNull
    @Positive
    private BigDecimal price;

    @Length(max = 1028)
    private String description;

    @Length(max = 256)
    private String coverImage;
}

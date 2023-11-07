package com.foalex.bookstore.dto.book;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
    @Min(0)
    private BigDecimal price;

    @Length(max = 1028)
    private String description;

    @Length(max = 256)
    private String coverImage;
}

package com.foalex.bookstore.dto.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

public class BookDto {
    @NotBlank
    @Length(max = 128)
    private String title;
    @NotBlank
    @Length(max = 128)
    private String author;
    @NotBlank
    @Length(max = 128)
    private String isbn;
    @NotNull
    @Positive
    private BigDecimal price;
    @Length(max = 1024)
    private String description;
    @Length(max = 128)
    private String coverImage;
    @Setter
    private List<Long> categoryIds;
}

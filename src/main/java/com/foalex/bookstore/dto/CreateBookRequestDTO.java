package com.foalex.bookstore.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class CreateBookRequestDTO {
    private String title;

    private String author;

    private String isbn;

    private BigDecimal price;

    private String description;

    private String coverImage;
}

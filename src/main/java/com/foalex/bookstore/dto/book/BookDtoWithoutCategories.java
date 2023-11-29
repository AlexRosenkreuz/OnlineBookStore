package com.foalex.bookstore.dto.book;

import java.math.BigDecimal;

public record BookDtoWithoutCategories(
        String title,
        String author,
        String isbn,
        BigDecimal price,
        String description,
        String coverImage) {
}

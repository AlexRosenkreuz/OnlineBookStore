package com.foalex.bookstore.service;

import com.foalex.bookstore.dto.BookDto;
import com.foalex.bookstore.dto.CreateBookRequestDto;
import java.util.List;

public interface BookService {
    BookDto save(CreateBookRequestDto requestDTo);

    List<BookDto> findAll();

    BookDto getById(Long id);
}

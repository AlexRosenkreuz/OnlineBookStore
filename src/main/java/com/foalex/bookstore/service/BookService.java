package com.foalex.bookstore.service;

import java.util.List;
import com.foalex.bookstore.dto.book.BookDto;
import com.foalex.bookstore.dto.book.CreateBookRequestDto;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookDto save(CreateBookRequestDto requestDto);

    List<BookDto> findAll(Pageable pageable);

    BookDto getById(Long id);

    BookDto update(Long id, CreateBookRequestDto requestDto);

    void delete(Long id);

}

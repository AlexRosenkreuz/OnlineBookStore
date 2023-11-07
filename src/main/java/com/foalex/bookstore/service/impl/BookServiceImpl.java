package com.foalex.bookstore.service.impl;

import java.util.List;
import com.foalex.bookstore.mapper.BookMapper;
import com.foalex.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import com.foalex.bookstore.dto.book.BookDto;
import com.foalex.bookstore.dto.book.CreateBookRequestDto;
import com.foalex.bookstore.exception.EntityNotFoundException;
import com.foalex.bookstore.model.Book;
import com.foalex.bookstore.repository.BookRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto save(CreateBookRequestDto requestDto) {
        Book book = bookMapper.toBook(requestDto);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public List<BookDto> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable).stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookDto getById(Long id) {
        return bookRepository.findById(id)
                .map(bookMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Can't find book by id: " + id));
    }

    @Override
    public BookDto update(Long id, CreateBookRequestDto requestDto) {
        Book book = bookMapper.toBook(requestDto);
        book.setId(id);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public void delete(Long id) {
        checkIfBookExistsById(id);
        bookRepository.deleteById(id);
    }

    private void checkIfBookExistsById(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new EntityNotFoundException("Book with id=%d doesn't exist".formatted(id));
        }
    }
}

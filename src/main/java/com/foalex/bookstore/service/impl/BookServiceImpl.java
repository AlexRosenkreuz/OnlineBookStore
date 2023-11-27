package com.foalex.bookstore.service.impl;

import com.foalex.bookstore.dto.book.BookDto;
import com.foalex.bookstore.dto.book.BookDtoWithoutCategories;
import com.foalex.bookstore.dto.book.CreateBookRequestDto;
import com.foalex.bookstore.dto.book.UpdateBookRequestDto;
import com.foalex.bookstore.exception.EntityNotFoundException;
import com.foalex.bookstore.mapper.BookMapper;
import com.foalex.bookstore.model.Book;
import com.foalex.bookstore.model.Category;
import com.foalex.bookstore.repository.BookRepository;
import com.foalex.bookstore.repository.CategoryRepository;
import com.foalex.bookstore.service.BookService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto save(CreateBookRequestDto requestDto) {
        Book book = bookMapper.toBook(requestDto);
        addBookCategories(requestDto.categoryIds(), book);
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
                .orElseThrow(() -> new EntityNotFoundException("""
                             Get operation failed. 
                             Book with id %d doesn't exist.""".formatted(id)));
    }

    @Override
    public BookDto update(Long id, UpdateBookRequestDto requestDto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("""
                             Update operation failed. 
                             Book with id %d doesn't exist.""".formatted(id)));
        bookMapper.mapUpdateRequestToBook(requestDto, book);
        addBookCategories(requestDto.categoryIds(), book);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public void delete(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("""
                             Delete operation failed. 
                             Book with id %d doesn't exist.""".formatted(id)));
        bookRepository.delete(book);
    }

    @Override
    public List<BookDtoWithoutCategories> findBooksByCategoryId(Long id, Pageable pageable) {
        return bookRepository.findAllByCategoriesId(id, pageable)
                .stream()
                .map(bookMapper::toDtoWithoutCategories)
                .toList();
    }

    private void addBookCategories(List<Long> categoryIds, Book book) {
        if (categoryIds != null) {
            Set<Category> categories = new HashSet<>(
                    categoryRepository.findAllById(categoryIds));
            book.setCategories(categories);
        }
    }
}

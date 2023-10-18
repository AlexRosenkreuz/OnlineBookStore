package com.foalex.bookstore.service.impl;

import java.util.List;
import com.foalex.bookstore.model.Book;
import com.foalex.bookstore.repository.BookRepository;
import com.foalex.bookstore.service.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }
}

package com.foalex.bookstore.service;

import java.util.List;
import com.foalex.bookstore.model.Book;

public interface BookService {
    Book save(Book book);

    List<Book> findAll();
}

package com.foalex.bookstore.service;

import com.foalex.bookstore.model.Book;
import java.util.List;

public interface BookService {
    Book save(Book book);

    List<Book> findAll();
}

package com.foalex.bookstore.repository;

import java.util.List;
import com.foalex.bookstore.model.Book;

public interface BookRepository {
    Book save(Book book);

    List<Book> findAll();
}

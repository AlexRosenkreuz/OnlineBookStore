package com.foalex.bookstore.repository;

import com.foalex.bookstore.model.Book;
import java.util.List;

public interface BookRepository {
    Book save(Book book);

    List<Book> findAll();
}

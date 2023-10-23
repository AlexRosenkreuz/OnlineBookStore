package com.foalex.bookstore.repository.impl;

import com.foalex.bookstore.exception.DataProcessingException;
import com.foalex.bookstore.model.Book;
import com.foalex.bookstore.repository.BookRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {
    private final SessionFactory sessionFactory;

    @Override
    public Book save(Book book) {
        try {
            sessionFactory.inTransaction(session -> session.persist(book));
            return book;
        } catch (Exception e) {
            throw new DataProcessingException("Can't create new book", e);
        }
    }

    @Override
    public List<Book> findAll() {
        try {
            return sessionFactory.fromSession(
                    session -> session.createQuery("from Book", Book.class).getResultList()
            );
        } catch (Exception e) {
            throw new DataProcessingException("Can't load list of books", e);
        }
    }
}

package com.foalex.bookstore.repository;

import com.foalex.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface BookRepository extends JpaRepository<Book, Long>, QuerydslPredicateExecutor<Book> {
}

package com.foalex.bookstore.repository;

import com.foalex.bookstore.model.Book;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @EntityGraph(attributePaths = "categories")
    Optional<Book> findById(Long id);

    @EntityGraph(attributePaths = "categories")
    Page<Book> findAll(Pageable pageable);

    List<Book> findAllByCategoriesId(Long id, Pageable pageable);
}

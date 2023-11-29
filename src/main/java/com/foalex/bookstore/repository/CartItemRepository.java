package com.foalex.bookstore.repository;

import com.foalex.bookstore.model.CartItem;
import com.foalex.bookstore.model.ShoppingCart;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByShoppingCartAndId(ShoppingCart cart, Long id);

    @EntityGraph(attributePaths = "book")
    Optional<CartItem> findByShoppingCartAndBookId(ShoppingCart cart, Long bookId);
}

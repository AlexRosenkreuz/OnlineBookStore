package com.foalex.bookstore.repository;

import com.foalex.bookstore.model.CartItem;
import com.foalex.bookstore.model.ShoppingCart;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    @EntityGraph(attributePaths = "book")
    Boolean existsByShoppingCartAndBookId(ShoppingCart cart, Long bookId);
}

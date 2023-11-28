package com.foalex.bookstore.repository;

import com.foalex.bookstore.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    ShoppingCart findByUserEmail(String email);

    @Query("""
        from ShoppingCart c
        inner join fetch c.user u
        left join fetch c.cartItems ci
        left join fetch ci.book
        where u.email = :email""")
    ShoppingCart findByUserEmailWithCartItems(@Param("email") String email);
}

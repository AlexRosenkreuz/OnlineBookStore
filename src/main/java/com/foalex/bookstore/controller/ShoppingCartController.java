package com.foalex.bookstore.controller;

import com.foalex.bookstore.dto.shoppingcart.CartItemDto;
import com.foalex.bookstore.dto.shoppingcart.CreateCartItemRequestDto;
import com.foalex.bookstore.dto.shoppingcart.ShoppingCartDto;
import com.foalex.bookstore.dto.shoppingcart.UpdateCartItemRequestDto;
import com.foalex.bookstore.service.ShoppingCartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Shopping cart management",
        description = "Endpoints for interacting with shopping cart")
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api/cart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @Operation(
            summary = "Get user cart",
            description = "Retrieves all items that from your cart."
    )
    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ShoppingCartDto getMyCart(Authentication authentication) {
        return shoppingCartService.getUserCart(authentication);
    }

    @Operation(
            summary = "Add book to your cart",
            description = "Adds certain amount of books to the cart by id."
    )
    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public CartItemDto addCartItem(
            Authentication authentication,
            @Valid @RequestBody CreateCartItemRequestDto requestDto) {
        return shoppingCartService.addCartItem(authentication, requestDto);
    }

    @Operation(
            summary = "Update cart item",
            description = "Updates the quantity of your cart item by id."
    )
    @PutMapping("/cart-items/{cartItemId}")
    @PreAuthorize("hasRole('USER')")
    public CartItemDto updateCartItem(
            @Positive @PathVariable Long cartItemId,
            @Valid @RequestBody UpdateCartItemRequestDto requestDto) {
        return shoppingCartService.updateCartItem(cartItemId, requestDto);
    }

    @Operation(
            summary = "Delete item from cart",
            description = "Deletes item from your cart by id."
    )
    @DeleteMapping("/cart-items/{cartItemId}")
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCartItem(@Positive @PathVariable Long cartItemId) {
        shoppingCartService.deleteCartItem(cartItemId);
    }
}

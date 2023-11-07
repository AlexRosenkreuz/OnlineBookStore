package com.foalex.bookstore.controller;

import com.foalex.bookstore.dto.BookDto;
import com.foalex.bookstore.dto.CreateBookRequestDto;
import com.foalex.bookstore.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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

@Tag(name = "Book management", description = "Endpoints for mapping books")
@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    @Operation(
            summary = "Retrieves all books",
            description = "Retrieves books with pagination and sorting by fields."
    )
    @GetMapping
    public List<BookDto> getAll(Pageable pageable) {
        return bookService.findAll(pageable);
    }

    @Operation(
            summary = "Retrieves a book by id",
            description = "Gets a book with specified id."
    )
    @GetMapping("/{id}")
    public BookDto getBookById(@PathVariable Long id) {
        return bookService.getById(id);
    }

    @Operation(
            summary = "Creates a new book",
            description = "Creates a new book with specified fields."
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto createBook(@RequestBody @Valid CreateBookRequestDto bookDto) {
        return bookService.save(bookDto);
    }

    @Operation(
            summary = "Updates existing book",
            description = "Updates existing book's fields."
    )
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookDto updateBook(@PathVariable Long id,
                              @RequestBody @Valid CreateBookRequestDto bookDto) {
        return bookService.update(id, bookDto);
    }

    @Operation(
            summary = "Deletes book by id",
            description = "Deletes a book with specified id."
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable @Positive Long id) {
        bookService.delete(id);
    }
}

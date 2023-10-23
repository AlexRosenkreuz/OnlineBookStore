package com.foalex.bookstore;

import com.foalex.bookstore.model.Book;
import com.foalex.bookstore.service.BookService;
import java.math.BigDecimal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookStoreApplication {
    private final BookService bookService;

    public BookStoreApplication(BookService bookService) {
        this.bookService = bookService;
    }

    public static void main(String[] args) {
        SpringApplication.run(BookStoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            Book book = new Book();
            book.setTitle("BJ Alex");
            book.setAuthor("Mingwa");
            book.setDescription("The long-awaited BJ Alex is now released as a physical book!");
            book.setIsbn("9788834907863");
            book.setPrice(BigDecimal.valueOf(91));
            bookService.save(book);
            bookService.findAll().forEach(System.out::println);
        };
    }
}

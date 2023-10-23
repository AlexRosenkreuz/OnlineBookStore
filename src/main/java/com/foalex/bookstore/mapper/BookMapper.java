package com.foalex.bookstore.mapper;

import com.foalex.bookstore.dto.BookDTO;
import com.foalex.bookstore.dto.CreateBookRequestDTO;
import com.foalex.bookstore.model.Book;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface BookMapper {
    BookDTO toDto(Book book);

    Book toBook(CreateBookRequestDTO bookRequestDto);
}

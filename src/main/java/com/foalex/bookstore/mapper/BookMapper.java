package com.foalex.bookstore.mapper;

import com.foalex.bookstore.dto.book.BookDto;
import com.foalex.bookstore.dto.book.CreateBookRequestDto;
import com.foalex.bookstore.dto.book.UpdateBookRequestDto;
import com.foalex.bookstore.model.Book;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface BookMapper {
    BookDto toDto(Book book);

    Book toBook(CreateBookRequestDto bookRequestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapUpdateRequestToBook(
            UpdateBookRequestDto updateBookRequestDto, @MappingTarget Book book
    );
}

package com.foalex.bookstore.mapper;

import com.foalex.bookstore.dto.book.BookDto;
import com.foalex.bookstore.dto.book.BookDtoWithoutCategories;
import com.foalex.bookstore.dto.book.CreateBookRequestDto;
import com.foalex.bookstore.dto.book.UpdateBookRequestDto;
import com.foalex.bookstore.model.Book;
import com.foalex.bookstore.model.Category;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface BookMapper {
    @Mapping(target = "categoryIds", ignore = true)
    BookDto toDto(Book book);

    BookDtoWithoutCategories toDtoWithoutCategories(Book book);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "deleted", ignore = true),
            @Mapping(target = "categories", ignore = true),
            @Mapping(target = "cartItems", ignore = true)
    })
    Book toBook(CreateBookRequestDto bookRequestDto);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "deleted", ignore = true),
            @Mapping(target = "categories", ignore = true),
            @Mapping(target = "cartItems", ignore = true)
    })
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateBook(
            UpdateBookRequestDto updateBookRequestDto, @MappingTarget Book book
    );

    @AfterMapping
    default void setCategoryIds(@MappingTarget BookDto bookDto, Book book) {
        bookDto.setCategoryIds(book.getCategories()
                .stream()
                .map(Category::getId)
                .toList()
        );
    }
}

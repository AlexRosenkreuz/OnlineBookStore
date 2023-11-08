package com.foalex.bookstore.service;

import com.foalex.bookstore.dto.book.BookDtoWithoutCategories;
import com.foalex.bookstore.dto.category.CategoryDto;
import com.foalex.bookstore.dto.category.CreateCategoryRequestDto;
import com.foalex.bookstore.dto.category.UpdateCategoryRequestDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    CategoryDto save(CreateCategoryRequestDto requestDto);

    List<CategoryDto> findAll(Pageable pageable);

    CategoryDto getById(Long id);

    CategoryDto update(Long id, UpdateCategoryRequestDto requestDto);

    void delete(Long id);

    List<BookDtoWithoutCategories> findBooksByCategoryId(Long id, Pageable pageable);
}

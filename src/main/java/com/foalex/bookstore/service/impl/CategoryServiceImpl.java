package com.foalex.bookstore.service.impl;

import com.foalex.bookstore.dto.category.CategoryDto;
import com.foalex.bookstore.dto.category.CreateCategoryRequestDto;
import com.foalex.bookstore.dto.category.UpdateCategoryRequestDto;
import com.foalex.bookstore.exception.EntityNotFoundException;
import com.foalex.bookstore.mapper.BookMapper;
import com.foalex.bookstore.mapper.CategoryMapper;
import com.foalex.bookstore.model.Category;
import com.foalex.bookstore.repository.BookRepository;
import com.foalex.bookstore.repository.CategoryRepository;
import com.foalex.bookstore.service.CategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final BookRepository bookRepository;
    private final CategoryMapper categoryMapper;
    private final BookMapper bookMapper;

    @Override
    public CategoryDto save(CreateCategoryRequestDto requestDto) {
        Category category = categoryMapper.toCategory(requestDto);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public List<CategoryDto> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable).stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    @Override
    public CategoryDto getById(Long id) {
        return categoryMapper.toDto(categoryByOperationAndId("Get", id));
    }

    @Override
    public CategoryDto update(Long id, UpdateCategoryRequestDto requestDto) {
        Category category = categoryByOperationAndId("Update", id);
        categoryMapper.mapUpdateRequestToCategory(requestDto, category);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public void delete(Long id) {
        categoryRepository.delete(categoryByOperationAndId("Delete", id));
    }

    private Category categoryByOperationAndId(String operation, Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("""
                             %s operation failed.
                             Category with id %d doesn't exist."""
                        .formatted(operation, id)));
    }
}

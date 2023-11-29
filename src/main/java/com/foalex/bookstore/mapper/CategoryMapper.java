package com.foalex.bookstore.mapper;

import com.foalex.bookstore.dto.category.CategoryDto;
import com.foalex.bookstore.dto.category.CreateCategoryRequestDto;
import com.foalex.bookstore.dto.category.UpdateCategoryRequestDto;
import com.foalex.bookstore.model.Category;
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
public interface CategoryMapper {
    CategoryDto toDto(Category category);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "deleted", ignore = true)
    })
    Category toCategory(CreateCategoryRequestDto requestDto);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "deleted", ignore = true)
    })
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapUpdateRequestToCategory(
            UpdateCategoryRequestDto updateBookRequestDto, @MappingTarget Category category
    );
}

package com.almunia.netflix.services;

import com.almunia.netflix.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
     List<CategoryDto> getAllCategories();
     CategoryDto getCategoryById(int id);

    CategoryDto getCategoryByName(String name);

    CategoryDto createCategory(CategoryDto categoryDto);
     CategoryDto updateCategory(CategoryDto categoryDto);
     CategoryDto deleteCategory(int id);
}

package com.almunia.netflix.services;

import com.almunia.netflix.dto.CategoryDto;
import com.almunia.netflix.exceptions.NetflixException;

import java.util.List;

public interface CategoryService {
     List<CategoryDto> getAllCategories();
     CategoryDto getCategoryById(int id) throws NetflixException;

    CategoryDto getCategoryByName(String name) throws NetflixException;

    CategoryDto createCategory(CategoryDto categoryDto) throws NetflixException;
     CategoryDto updateCategory(CategoryDto categoryDto) throws NetflixException;
     CategoryDto deleteCategory(int id) throws NetflixException;
}

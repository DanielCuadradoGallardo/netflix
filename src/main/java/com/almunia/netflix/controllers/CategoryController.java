package com.almunia.netflix.controllers;

import com.almunia.netflix.dto.CategoryDto;
import com.almunia.netflix.response.NetflixResponse;

import java.util.List;

public interface CategoryController {

    NetflixResponse<List<CategoryDto>> getAllCategories();
    NetflixResponse<CategoryDto> getCategoryById(int id);
    NetflixResponse<CategoryDto> createCategory(CategoryDto categoryDto);
    NetflixResponse<CategoryDto> updateCategory(CategoryDto categoryDto);
    NetflixResponse<CategoryDto> deleteCategory(int id);
}

package com.almunia.netflix.controllers;

import com.almunia.netflix.dto.CategoryDto;
import com.almunia.netflix.exceptions.NetflixException;
import com.almunia.netflix.response.NetflixResponse;

import java.util.List;

public interface CategoryController {

    NetflixResponse<List<CategoryDto>> getAllCategories();
    NetflixResponse<CategoryDto> getCategoryById(int id) throws NetflixException;
    NetflixResponse<CategoryDto> createCategory(CategoryDto categoryDto) throws NetflixException;
    NetflixResponse<CategoryDto> updateCategory(CategoryDto categoryDto) throws NetflixException;
    NetflixResponse<CategoryDto> deleteCategory(int id) throws NetflixException;
}

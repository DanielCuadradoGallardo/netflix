package com.almunia.netflix.services;

import com.almunia.netflix.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    public List<CategoryDto> getAllCategories();
}

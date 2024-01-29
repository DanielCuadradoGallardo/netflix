package com.almunia.netflix.controllers;

import com.almunia.netflix.dto.CategoryDto;
import com.almunia.netflix.response.NetflixResponse;

import java.util.List;

public interface CategoryController {

    NetflixResponse<List<CategoryDto>> getAllCategories();
}

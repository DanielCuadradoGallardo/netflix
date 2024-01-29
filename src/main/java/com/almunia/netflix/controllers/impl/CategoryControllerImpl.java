package com.almunia.netflix.controllers.impl;

import com.almunia.netflix.controllers.CategoryController;
import com.almunia.netflix.dto.CategoryDto;
import com.almunia.netflix.response.NetflixResponse;
import com.almunia.netflix.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryControllerImpl implements CategoryController {

    private final CategoryService categoryService;

    public CategoryControllerImpl(final CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<List<CategoryDto>> getAllCategories(){
        return new NetflixResponse<>(200, String.valueOf(HttpStatus.OK), "OK",
                categoryService.getAllCategories());
    }
}

package com.almunia.netflix.services.impl;

import com.almunia.netflix.dto.CategoryDto;
import com.almunia.netflix.entities.Category;
import com.almunia.netflix.repositories.CategoryRepository;
import com.almunia.netflix.services.CategoryService;
import com.almunia.netflix.utils.constants.ExceptionConstants;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
        modelMapper = new ModelMapper();
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream().map(category -> modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryById(int id) {
        return modelMapper.map(categoryRepository.findById(id).orElseThrow(() -> new RuntimeException(ExceptionConstants.CATEGORY_NOT_FOUND)), CategoryDto.class);
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = new Category(0, categoryDto.getName(), null);
        if (categoryRepository.findCategoryByName(category.getName()).isPresent()) {
            throw new RuntimeException(ExceptionConstants.CATEGORY_ALREADY_EXISTS);
        } else {
            return modelMapper.map(categoryRepository.save(category), CategoryDto.class);
        }
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto) {
        Category category = new Category(categoryDto.getId(), categoryDto.getName(), null);
        if(categoryRepository.findCategoryById(category.getId()).isPresent()){
            if (categoryRepository.findCategoryByName(category.getName()).isPresent()) {
                throw new RuntimeException(ExceptionConstants.CATEGORY_ALREADY_EXISTS);
            } else {
                return modelMapper.map(categoryRepository.save(category), CategoryDto.class);
            }
        }else{
            throw new RuntimeException(ExceptionConstants.CATEGORY_NOT_FOUND);
        }
    }

    @Override
    public CategoryDto deleteCategory(int id) {
        Category category = categoryRepository.findCategoryById(id).orElse(null);
        if(category != null){
            categoryRepository.delete(category);
            return modelMapper.map(category, CategoryDto.class);
        }else{
            throw new RuntimeException(ExceptionConstants.CATEGORY_NOT_FOUND);
        }
    }
}

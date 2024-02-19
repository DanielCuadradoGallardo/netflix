package com.almunia.netflix.services.impl;

import com.almunia.netflix.dto.CategoryDto;
import com.almunia.netflix.entities.Category;
import com.almunia.netflix.exceptions.AlreadyExistsException;
import com.almunia.netflix.exceptions.NetflixException;
import com.almunia.netflix.exceptions.NotFoundException;
import com.almunia.netflix.repositories.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {
    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private CategoryServiceImpl categoryService;
    private final ModelMapper modelMapper = new ModelMapper();

    @Test
    void getAllCategories() {
        // GIVEN
        Category category1 = Category.builder()
                .name("Action")
                .build();

        Category category2 = Category.builder()
                .name("Drama")
                .build();

        // WHEN
        when(categoryRepository.findAll()).thenReturn(Arrays.asList(category1, category2));

        // THEN
        assertEquals(modelMapper.map(category1, CategoryDto.class), categoryService.getAllCategories().get(0));
        assertEquals(modelMapper.map(category2, CategoryDto.class), categoryService.getAllCategories().get(1));
    }

    @Test
    void getCategoryById() throws NetflixException {
        // GIVEN
        Category category = Category.builder()
                .name("Action")
                .build();

        // WHEN
        when(categoryRepository.findById(anyInt())).thenReturn(Optional.of(category));

        // THEN
        assertEquals(modelMapper.map(category, CategoryDto.class), categoryService.getCategoryById(1));
    }

    @Test
    void getCategoryById_NotFound() {
        // WHEN
        when(categoryRepository.findById(anyInt())).thenReturn(Optional.empty());

        // THEN
        assertThrows(NotFoundException.class, () -> categoryService.getCategoryById(1));
    }

    @Test
    void getCategoryByName() throws NetflixException {
        // GIVEN
        Category category = Category.builder()
                .name("Action")
                .build();

        // WHEN
        when(categoryRepository.findCategoryByName(anyString())).thenReturn(Optional.of(category));

        // THEN
        assertEquals(modelMapper.map(category, CategoryDto.class), categoryService.getCategoryByName("Action"));
    }

    @Test
    void getCategoryByName_NotFound() {
        // WHEN
        when(categoryRepository.findCategoryByName(anyString())).thenReturn(Optional.empty());

        // THEN
        assertThrows(NotFoundException.class, () -> categoryService.getCategoryByName("Action"));
    }

    @Test
    void createCategory() throws NetflixException {
        // GIVEN
        Category category = Category.builder()
                .name("Action")
                .build();

        CategoryDto categoryDto = CategoryDto.builder()
                .name("Action")
                .build();

        // WHEN
        when(categoryRepository.findCategoryByName(anyString())).thenReturn(Optional.empty());
        when(categoryRepository.save(any())).thenReturn(category);

        // THEN
        assertEquals(modelMapper.map(category, CategoryDto.class), categoryService.createCategory(categoryDto));
    }

    @Test
    void createCategory_AlreadyExists() {
        // GIVEN
        Category category = Category.builder()
                .name("Action")
                .build();

        CategoryDto categoryDto = CategoryDto.builder()
                .name("Action")
                .build();

        // WHEN
        when(categoryRepository.findCategoryByName(anyString())).thenReturn(Optional.of(category));

        // THEN
        assertThrows(AlreadyExistsException.class, () -> categoryService.createCategory(categoryDto));
    }

    @Test
    void updateCategory() throws NetflixException {
        // GIVEN
        Category category = Category.builder()
                .name("Action")
                .build();

        CategoryDto categoryDto = CategoryDto.builder()
                .name("Action")
                .build();

        // WHEN
        when(categoryRepository.findCategoryById(anyInt())).thenReturn(Optional.of(category));
        when(categoryRepository.findCategoryByName(anyString())).thenReturn(Optional.empty());
        when(categoryRepository.save(any())).thenReturn(category);

        // THEN
        assertEquals(modelMapper.map(category, CategoryDto.class), categoryService.updateCategory(categoryDto));
    }

    @Test
    void updateCategory_NotFound() {
        // GIVEN
        CategoryDto categoryDto = CategoryDto.builder()
                .name("Action")
                .build();

        // WHEN
        when(categoryRepository.findCategoryById(anyInt())).thenReturn(Optional.empty());

        // THEN
        assertThrows(NotFoundException.class, () -> categoryService.updateCategory(categoryDto));
    }

    @Test
    void updateCategory_AlreadyExists() {
        // GIVEN
        Category category = Category.builder()
                .name("Action")
                .build();

        CategoryDto categoryDto = CategoryDto.builder()
                .name("Action")
                .build();

        // WHEN
        when(categoryRepository.findCategoryById(anyInt())).thenReturn(Optional.of(category));
        when(categoryRepository.findCategoryByName(anyString())).thenReturn(Optional.of(category));

        // THEN
        assertThrows(AlreadyExistsException.class, () -> categoryService.updateCategory(categoryDto));
    }

    @Test
    void deleteCategory() throws NetflixException {
        // GIVEN
        Category category = Category.builder()
                .name("Action")
                .build();

        // WHEN
        when(categoryRepository.findCategoryById(anyInt())).thenReturn(Optional.of(category));

        // THEN
        assertEquals(modelMapper.map(category, CategoryDto.class), categoryService.deleteCategory(1));
    }

    @Test
    void deleteCategory_NotFound() {
        // WHEN
        when(categoryRepository.findCategoryById(anyInt())).thenReturn(Optional.empty());

        // THEN
        assertThrows(NotFoundException.class, () -> categoryService.deleteCategory(1));
    }
}

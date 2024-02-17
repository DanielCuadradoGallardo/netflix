package com.almunia.netflix.controllers.impl;

import com.almunia.netflix.controllers.CategoryController;
import com.almunia.netflix.dto.CategoryDto;
import com.almunia.netflix.exceptions.NetflixException;
import com.almunia.netflix.response.NetflixResponse;
import com.almunia.netflix.services.CategoryService;
import com.almunia.netflix.utils.constants.CommonConstants;
import com.almunia.netflix.utils.constants.RestConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(RestConstants.RESOURCE_CATEGORIES)
public class CategoryControllerImpl implements CategoryController {

    private final CategoryService categoryService;

    public CategoryControllerImpl(final CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<List<CategoryDto>> getAllCategories(){
        return new NetflixResponse<>(200, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                categoryService.getAllCategories());
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping(value = RestConstants.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<CategoryDto> getCategoryById(@PathVariable("id") int id) throws NetflixException {
        return new NetflixResponse<>(200, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                categoryService.getCategoryById(id));
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) throws NetflixException {
        return new NetflixResponse<>(201, String.valueOf(HttpStatus.CREATED), CommonConstants.CATEGORY_CREATED_SUCCESSFULLY,
                categoryService.createCategory(categoryDto));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto) throws NetflixException {
        return new NetflixResponse<>(200, String.valueOf(HttpStatus.OK), CommonConstants.CATEGORY_UPDATED_SUCCESSFULLY,
                categoryService.updateCategory(categoryDto));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = RestConstants.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<CategoryDto> deleteCategory(@PathVariable("id") int id) throws NetflixException {
        return new NetflixResponse<>(200, String.valueOf(HttpStatus.OK), CommonConstants.CATEGORY_DELETED_SUCCESSFULLY,
                categoryService.deleteCategory(id));
    }
}

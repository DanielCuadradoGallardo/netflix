package com.almunia.netflix.services.impl;

import com.almunia.netflix.dto.CategoryDto;
import com.almunia.netflix.dto.SerieDto;
import com.almunia.netflix.entities.Category;
import com.almunia.netflix.entities.Serie;
import com.almunia.netflix.exceptions.AlreadyExistsException;
import com.almunia.netflix.exceptions.NetflixException;
import com.almunia.netflix.exceptions.NotFoundException;
import com.almunia.netflix.repositories.SerieRepository;
import com.almunia.netflix.services.CategoryService;
import com.almunia.netflix.services.SerieService;
import com.almunia.netflix.utils.constants.ExceptionConstants;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SerieServiceImpl implements SerieService {
    private final SerieRepository serieRepository;
    private final CategoryService categoryService;

    private final ModelMapper modelMapper;

    public SerieServiceImpl(final SerieRepository serieRepository, final CategoryService categoryService) {
        this.serieRepository = serieRepository;
        this.categoryService = categoryService;
        modelMapper = new ModelMapper();
    }

    @Override
    public List<SerieDto> getAllSeries() {
        return serieRepository.findAll().stream().map(serie -> modelMapper.map(serie, SerieDto.class)).collect(Collectors.toList());
    }

    @Override
    public SerieDto getSerieById(int id) throws NetflixException {
        return modelMapper.map(serieRepository.findById(id).orElseThrow(() -> new NotFoundException(ExceptionConstants.SERIE_NOT_FOUND)), SerieDto.class);
    }

    @Override
    public List<SerieDto> getSeriesByCategory(String categoryName) {
        return serieRepository.findSerieByCategoriesName(categoryName).stream().map(serie -> modelMapper.map(serie, SerieDto.class)).collect(Collectors.toList());
    }

    @Override
    public SerieDto createSerie(SerieDto serieDto) throws NetflixException {
        Serie serie = new Serie(0, serieDto.getName(), serieDto.getDescription(), serieDto.getRecommended_age(), new ArrayList<>(), new ArrayList<>());
        for (CategoryDto categoryDto : serieDto.getCategories()) {
            categoryDto = categoryService.getCategoryByName(categoryDto.getName());
            Category category = new Category(categoryDto.getId(), categoryDto.getName(), null);
            serie.getCategories().add(category);
        }
        if (serieRepository.findSerieByName(serie.getName()).isPresent()) {
            throw new AlreadyExistsException(ExceptionConstants.SERIE_ALREADY_EXISTS);
        } else {
            return modelMapper.map(serieRepository.save(serie), SerieDto.class);
        }
    }

    @Override
    public SerieDto updateSerie(SerieDto serieDto) throws NetflixException {
        Serie serie = new Serie(serieDto.getId(), serieDto.getName(), serieDto.getDescription(), serieDto.getRecommended_age(), null, null);
        if(serieRepository.findSerieById(serie.getId()).isPresent()){
            if (serieRepository.findSerieByName(serie.getName()).isPresent()) {
                throw new AlreadyExistsException(ExceptionConstants.SERIE_ALREADY_EXISTS);
            } else {
                return modelMapper.map(serieRepository.save(serie), SerieDto.class);
            }
        }else{
            throw new NotFoundException(ExceptionConstants.SERIE_NOT_FOUND);
        }
    }

    @Override
    public SerieDto deleteSerie(int id) throws NetflixException {
        Serie serie = serieRepository.findSerieById(id).orElse(null);
        if(serie != null){
            serie.setCategories(null);
            serieRepository.delete(serie);
            return modelMapper.map(serie, SerieDto.class);
        }else{
            throw new NotFoundException(ExceptionConstants.SERIE_NOT_FOUND);
        }
    }
}
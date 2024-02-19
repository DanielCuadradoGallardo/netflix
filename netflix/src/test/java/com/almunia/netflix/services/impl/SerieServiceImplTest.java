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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SerieServiceImplTest {
    @Mock
    private SerieRepository serieRepository;
    @Mock
    private CategoryService categoryService;
    @InjectMocks
    private SerieServiceImpl serieService;
    private final ModelMapper modelMapper = new ModelMapper();

    @Test
    void getAllSeries() {
        // GIVEN
        Serie serie1 = Serie.builder()
                .name("Serie 1")
                .description("Description of Serie 1")
                .recommended_age(12)
                .build();

        Serie serie2 = Serie.builder()
                .name("Serie 2")
                .description("Description of Serie 2")
                .recommended_age(16)
                .build();

        // WHEN
        when(serieRepository.findAll()).thenReturn(Arrays.asList(serie1, serie2));

        // THEN
        assertEquals(modelMapper.map(serie1, SerieDto.class), serieService.getAllSeries().get(0));
        assertEquals(modelMapper.map(serie2, SerieDto.class), serieService.getAllSeries().get(1));
    }

    @Test
    void getSerieById() throws NetflixException {
        // GIVEN
        Serie serie = Serie.builder()
                .name("Serie 1")
                .description("Description of Serie 1")
                .recommended_age(12)
                .build();

        // WHEN
        when(serieRepository.findById(anyInt())).thenReturn(Optional.of(serie));

        // THEN
        assertEquals(modelMapper.map(serie, SerieDto.class), serieService.getSerieById(1));
    }

    @Test
    void getSerieById_NotFound() {
        // WHEN
        when(serieRepository.findById(anyInt())).thenReturn(Optional.empty());

        // THEN
        assertThrows(NotFoundException.class, () -> serieService.getSerieById(1));
    }

    @Test
    void getSeriesByCategory() {
        // GIVEN
        Serie serie1 = Serie.builder()
                .name("Serie 1")
                .description("Description of Serie 1")
                .recommended_age(12)
                .build();

        Serie serie2 = Serie.builder()
                .name("Serie 2")
                .description("Description of Serie 2")
                .recommended_age(16)
                .build();

        // WHEN
        when(serieRepository.findSerieByCategoriesName(anyString())).thenReturn(Arrays.asList(serie1, serie2));

        // THEN
        assertEquals(modelMapper.map(serie1, SerieDto.class), serieService.getSeriesByCategory("Action").get(0));
        assertEquals(modelMapper.map(serie2, SerieDto.class), serieService.getSeriesByCategory("Action").get(1));
    }

    @Test
    void createSerie() throws NetflixException {
        // GIVEN
        Serie serie = Serie.builder()
                .name("Serie 1")
                .description("Description of Serie 1")
                .recommended_age(12)
                .build();

        CategoryDto category1 = CategoryDto.builder()
                .name("Action")
                .build();

        CategoryDto category2 = CategoryDto.builder()
                .name("Drama")
                .build();

        SerieDto serieDto = SerieDto.builder()
                .name("Serie 1")
                .description("Description of Serie 1")
                .recommended_age(12)
                .categories(new ArrayList<>(Arrays.asList(category1, category2)))
                .build();

        // WHEN
        when(categoryService.getCategoryByName(anyString())).thenReturn(category1);
        when(serieRepository.findSerieByName(anyString())).thenReturn(Optional.empty());
        when(serieRepository.save(any())).thenReturn(serie);

        // THEN
        assertEquals(modelMapper.map(serie, SerieDto.class), serieService.createSerie(serieDto));
    }

    @Test
    void createSerie_AlreadyExists() throws NetflixException {
        // GIVEN
        Serie serie = Serie.builder()
                .name("Serie 1")
                .description("Description of Serie 1")
                .recommended_age(12)
                .build();

        CategoryDto category1 = CategoryDto.builder()
                .name("Action")
                .build();

        CategoryDto category2 = CategoryDto.builder()
                .name("Drama")
                .build();

        SerieDto serieDto = SerieDto.builder()
                .name("Serie 1")
                .description("Description of Serie 1")
                .recommended_age(12)
                .categories(Arrays.asList(category1, category2))
                .build();

        // WHEN
        when(categoryService.getCategoryByName(anyString())).thenReturn(category1);
        when(serieRepository.findSerieByName(anyString())).thenReturn(Optional.of(serie));

        // THEN
        assertThrows(AlreadyExistsException.class, () -> serieService.createSerie(serieDto));
    }

    @Test
    void updateSerie() throws NetflixException {
        // GIVEN
        Serie serie = Serie.builder()
                .name("Serie 1")
                .description("Description of Serie 1")
                .recommended_age(12)
                .build();

        SerieDto serieDto = SerieDto.builder()
                .id(1)
                .name("Serie 1 Updated")
                .description("Updated description")
                .recommended_age(16)
                .categories(new ArrayList<>())
                .build();

        // WHEN
        when(serieRepository.findSerieById(anyInt())).thenReturn(Optional.of(serie));
        when(serieRepository.findSerieByName(anyString())).thenReturn(Optional.empty());
        when(serieRepository.save(any())).thenReturn(serie);

        // THEN
        assertEquals(modelMapper.map(serie, SerieDto.class), serieService.updateSerie(serieDto));
    }

    @Test
    void updateSerie_NotFound() {
        // GIVEN
        SerieDto serieDto = SerieDto.builder()
                .id(1)
                .name("Serie 1 Updated")
                .description("Updated description")
                .recommended_age(16)
                .categories(new ArrayList<>())
                .build();

        // WHEN
        when(serieRepository.findSerieById(anyInt())).thenReturn(Optional.empty());

        // THEN
        assertThrows(NotFoundException.class, () -> serieService.updateSerie(serieDto));
    }

    @Test
    void updateSerie_AlreadyExists() {
        // GIVEN
        Serie serie = Serie.builder()
                .name("Serie 1")
                .description("Description of Serie 1")
                .recommended_age(12)
                .build();

        SerieDto serieDto = SerieDto.builder()
                .id(1)
                .name("Serie 1 Updated")
                .description("Updated description")
                .recommended_age(16)
                .categories(new ArrayList<>())
                .build();

        // WHEN
        when(serieRepository.findSerieById(anyInt())).thenReturn(Optional.of(serie));
        when(serieRepository.findSerieByName(anyString())).thenReturn(Optional.of(serie));

        // THEN
        assertThrows(AlreadyExistsException.class, () -> serieService.updateSerie(serieDto));
    }

    @Test
    void deleteSerie() throws NetflixException {
        // GIVEN
        Serie serie = Serie.builder()
                .name("Serie 1")
                .description("Description of Serie 1")
                .recommended_age(12)
                .build();

        // WHEN
        when(serieRepository.findSerieById(anyInt())).thenReturn(Optional.of(serie));

        // THEN
        assertEquals(modelMapper.map(serie, SerieDto.class), serieService.deleteSerie(1));
    }

    @Test
    void deleteSerie_NotFound() {
        // WHEN
        when(serieRepository.findSerieById(anyInt())).thenReturn(Optional.empty());

        // THEN
        assertThrows(NotFoundException.class, () -> serieService.deleteSerie(1));
    }
}

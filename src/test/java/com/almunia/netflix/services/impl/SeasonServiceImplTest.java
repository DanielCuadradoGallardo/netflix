package com.almunia.netflix.services.impl;

import com.almunia.netflix.dto.SeasonDto;
import com.almunia.netflix.dto.SerieDto;
import com.almunia.netflix.entities.Serie;
import com.almunia.netflix.entities.Season;
import com.almunia.netflix.exceptions.AlreadyExistsException;
import com.almunia.netflix.exceptions.NetflixException;
import com.almunia.netflix.exceptions.NotFoundException;
import com.almunia.netflix.repositories.SeasonRepository;
import com.almunia.netflix.services.SerieService;
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
class SeasonServiceImplTest {
    @Mock
    private SeasonRepository seasonRepository;
    @Mock
    private SerieService serieService;
    @InjectMocks
    private SeasonServiceImpl seasonService;
    private final ModelMapper modelMapper = new ModelMapper();

    @Test
    void getAllSeasons() {
        // GIVEN
        Season season1 = Season.builder()
                .title("Season 1")
                .description("The beginning of the series")
                .build();

        Season season2 = Season.builder()
                .title("Season 2")
                .description("Continuation of the series")
                .build();

        // WHEN
        when(seasonRepository.findAll()).thenReturn(Arrays.asList(season1, season2));

        // THEN
        assertEquals(modelMapper.map(season1, SeasonDto.class), seasonService.getAllSeasons().get(0));
        assertEquals(modelMapper.map(season2, SeasonDto.class), seasonService.getAllSeasons().get(1));
    }

    @Test
    void getSeasonById() throws NetflixException {
        // GIVEN
        Season season = Season.builder()
                .title("Season 1")
                .description("The beginning of the series")
                .build();

        // WHEN
        when(seasonRepository.findById(anyInt())).thenReturn(Optional.of(season));

        // THEN
        assertEquals(modelMapper.map(season, SeasonDto.class), seasonService.getSeasonById(1));
    }

    @Test
    void getSeasonById_NotFound() {
        // WHEN
        when(seasonRepository.findById(anyInt())).thenReturn(Optional.empty());

        // THEN
        assertThrows(NotFoundException.class, () -> seasonService.getSeasonById(1));
    }

    @Test
    void createSeason() throws NetflixException {
        // GIVEN
        Season season = Season.builder()
                .title("Season 1")
                .description("The beginning of the series")
                .build();

        SerieDto serie = SerieDto.builder()
                .id(1)
                .name("Series 1")
                .description("The first series")
                .recommended_age(12)
                .build();

        SeasonDto seasonDto = SeasonDto.builder()
                .title("Season 1")
                .serie(serie)
                .build();

        // WHEN
        when(serieService.getSerieById(anyInt())).thenReturn(serie);
        when(seasonRepository.findSeasonBySerieNameAndTitle(anyString(), anyString())).thenReturn(Optional.empty());
        when(seasonRepository.save(any())).thenReturn(season);

        // THEN
        assertEquals(modelMapper.map(season, SeasonDto.class), seasonService.createSeason(seasonDto));
    }

    @Test
    void createSeason_AlreadyExists() throws NetflixException {
        // GIVEN
        Season season = Season.builder()
                .title("Season 1")
                .description("The beginning of the series")
                .build();

        SerieDto serie = SerieDto.builder()
                .name("Series 1")
                .description("The first series")
                .recommended_age(12)
                .build();

        SeasonDto seasonDto = SeasonDto.builder()
                .title("Season 1")
                .serie(serie)
                .build();

        // WHEN
        when(serieService.getSerieById(anyInt())).thenReturn(serie);
        when(seasonRepository.findSeasonBySerieNameAndTitle(anyString(), anyString())).thenReturn(Optional.of(season));

        // THEN
        assertThrows(AlreadyExistsException.class, () -> seasonService.createSeason(seasonDto));
    }

    @Test
    void updateSeason() throws NetflixException {
        // GIVEN
        Serie serie = Serie.builder()
                .name("Series 1")
                .description("The first series")
                .recommended_age(12)
                .build();

        Season season = Season.builder()
                .title("Season 1")
                .description("The beginning of the series")
                .serie(serie)
                .build();

        SeasonDto seasonDto = SeasonDto.builder()
                .title("")
                .description("")
                .build();

        Season seasonUpdated = Season.builder()
                .title("Season 1 Updated")
                .description("Updated description")
                .build();

        // WHEN
        when(seasonRepository.findSeasonById(anyInt())).thenReturn(Optional.of(season));
        when(seasonRepository.findSeasonBySerie_idAndTitle(anyInt(), anyString())).thenReturn(Optional.empty());
        when(seasonRepository.save(any())).thenReturn(seasonUpdated);

        // THEN
        assertEquals(modelMapper.map(seasonUpdated, SeasonDto.class), seasonService.updateSeason(seasonDto));
    }

    @Test
    void updateSeason_NotFound() {
        // GIVEN
        SeasonDto seasonDto = SeasonDto.builder()
                .title("Season 3")
                .description("New season")
                .build();

        // WHEN
        when(seasonRepository.findSeasonById(anyInt())).thenReturn(Optional.empty());

        // THEN
        assertThrows(NotFoundException.class, () -> seasonService.updateSeason(seasonDto));
    }

    @Test
    void updateSeason_AlreadyExists() {
        // GIVEN
        Serie serie = Serie.builder()
                .name("Series 1")
                .description("The first series")
                .recommended_age(12)
                .build();

        Season season = Season.builder()
                .title("Season 1")
                .description("The beginning of the series")
                .serie(serie)
                .build();

        SeasonDto seasonDto = SeasonDto.builder()
                .title("Season 3")
                .description("New season")
                .build();

        // WHEN
        when(seasonRepository.findSeasonById(anyInt())).thenReturn(Optional.of(season));
        when(seasonRepository.findSeasonBySerie_idAndTitle(anyInt(), anyString())).thenReturn(Optional.of(season));

        // THEN
        assertThrows(AlreadyExistsException.class, () -> seasonService.updateSeason(seasonDto));
    }

    @Test
    void deleteSeason() throws NetflixException {
        // GIVEN
        Serie serie = Serie.builder()
                .name("Series 1")
                .description("The first series")
                .recommended_age(12)
                .seasons(new ArrayList<>())
                .build();

        Season season = Season.builder()
                .title("Season 1")
                .description("The beginning of the series")
                .serie(serie)
                .build();

        // WHEN
        when(seasonRepository.findSeasonById(anyInt())).thenReturn(Optional.of(season));

        // THEN
        assertEquals(modelMapper.map(season, SeasonDto.class), seasonService.deleteSeason(1));
    }

    @Test
    void deleteSeason_NotFound() {
        // WHEN
        when(seasonRepository.findSeasonById(anyInt())).thenReturn(Optional.empty());

        // THEN
        assertThrows(NotFoundException.class, () -> seasonService.deleteSeason(1));
    }
}

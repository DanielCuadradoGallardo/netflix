package com.almunia.netflix.services;

import com.almunia.netflix.dto.SerieDto;
import com.almunia.netflix.exceptions.NetflixException;

import java.util.List;

public interface SerieService {
    List<SerieDto> getAllSeries();
    SerieDto getSerieById(int id) throws NetflixException;

    List<SerieDto> getSeriesByCategory(String categoryName);

    SerieDto createSerie(SerieDto serieDto) throws NetflixException;

    SerieDto updateSerie(SerieDto serieDto) throws NetflixException;
    SerieDto deleteSerie(int id) throws NetflixException;
}

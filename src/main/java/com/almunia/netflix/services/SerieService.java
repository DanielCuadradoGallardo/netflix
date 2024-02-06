package com.almunia.netflix.services;

import com.almunia.netflix.dto.SerieDto;

import java.util.List;

public interface SerieService {
    List<SerieDto> getAllSeries();
    SerieDto getSerieById(int id);

    List<SerieDto> getSeriesByCategory(String categoryName);

    SerieDto createSerie(SerieDto serieDto);

    //SerieDto addSeasonToSerie(int id, int seasonId);

    SerieDto updateSerie(SerieDto serieDto);
    SerieDto deleteSerie(int id);
}

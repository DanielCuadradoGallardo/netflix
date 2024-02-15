package com.almunia.netflix.controllers;

import com.almunia.netflix.dto.SerieDto;
import com.almunia.netflix.response.NetflixResponse;

import java.util.List;

public interface SerieController {
    NetflixResponse<List<SerieDto>> getAllSeries();

    NetflixResponse<SerieDto> getSerieById(int id);

    NetflixResponse<List<SerieDto>> getSeriesByCategory(String categoryName);

    NetflixResponse<SerieDto> createSerie(SerieDto serieDto);

    NetflixResponse<SerieDto> updateSerie(SerieDto serieDto);

    NetflixResponse<SerieDto> deleteSerie(int id);
}

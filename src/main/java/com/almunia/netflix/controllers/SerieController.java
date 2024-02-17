package com.almunia.netflix.controllers;

import com.almunia.netflix.dto.SerieDto;
import com.almunia.netflix.exceptions.NetflixException;
import com.almunia.netflix.response.NetflixResponse;

import java.util.List;

public interface SerieController {
    NetflixResponse<List<SerieDto>> getAllSeries();

    NetflixResponse<SerieDto> getSerieById(int id) throws NetflixException;

    NetflixResponse<List<SerieDto>> getSeriesByCategory(String categoryName);

    NetflixResponse<SerieDto> createSerie(SerieDto serieDto) throws NetflixException;

    NetflixResponse<SerieDto> updateSerie(SerieDto serieDto) throws NetflixException;

    NetflixResponse<SerieDto> deleteSerie(int id) throws NetflixException;
}

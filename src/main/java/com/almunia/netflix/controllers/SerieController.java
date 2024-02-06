package com.almunia.netflix.controllers;

import com.almunia.netflix.dto.SerieDto;
import com.almunia.netflix.response.NetflixResponse;
import com.almunia.netflix.utils.constants.RestConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

public interface SerieController {
    NetflixResponse<List<SerieDto>> getAllSeries();

    NetflixResponse<SerieDto> getSerieById(int id);

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = RestConstants.RESOURCE_CATEGORY, produces = MediaType.APPLICATION_JSON_VALUE)
    NetflixResponse<List<SerieDto>> getSeriesByCategory(@PathVariable("category") String categoryName);

    NetflixResponse<SerieDto> createSerie(SerieDto serieDto);

    NetflixResponse<SerieDto> updateSerie(SerieDto serieDto);

    NetflixResponse<SerieDto> deleteSerie(int id);
}

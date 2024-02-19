package com.almunia.netflix.services;

import com.almunia.netflix.dto.SeasonDto;
import com.almunia.netflix.exceptions.NetflixException;

import java.util.List;

public interface SeasonService {
    List<SeasonDto> getAllSeasons();

    SeasonDto getSeasonById(int id) throws NetflixException;

    SeasonDto createSeason(SeasonDto seasonDto) throws NetflixException;

    SeasonDto updateSeason(SeasonDto seasonDto) throws NetflixException;

    SeasonDto deleteSeason(int id) throws NetflixException;
}

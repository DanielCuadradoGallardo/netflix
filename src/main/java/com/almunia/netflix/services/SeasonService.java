package com.almunia.netflix.services;

import com.almunia.netflix.dto.SeasonDto;

import java.util.List;

public interface SeasonService {
    List<SeasonDto> getAllSeasons();

    SeasonDto getSeasonById(int id);

    SeasonDto createSeason(SeasonDto seasonDto);

    SeasonDto deleteSeason(int id);

    SeasonDto updateSeason(SeasonDto seasonDto);
}

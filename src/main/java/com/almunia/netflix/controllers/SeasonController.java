package com.almunia.netflix.controllers;

import com.almunia.netflix.dto.SeasonDto;
import com.almunia.netflix.exceptions.NetflixException;
import com.almunia.netflix.response.NetflixResponse;

import java.util.List;

public interface SeasonController {

    NetflixResponse<List<SeasonDto>> getAllSeasons();
    NetflixResponse<SeasonDto> getSeasonById(int id) throws NetflixException;
    NetflixResponse<SeasonDto> createSeason(SeasonDto seasonDto) throws NetflixException;
    NetflixResponse<SeasonDto> updateSeason(SeasonDto seasonDto) throws NetflixException;
    NetflixResponse<SeasonDto> deleteSeason(int id) throws NetflixException;
}

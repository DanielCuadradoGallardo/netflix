package com.almunia.netflix.controllers;

import com.almunia.netflix.dto.SeasonDto;
import com.almunia.netflix.response.NetflixResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface SeasonController {

    NetflixResponse<List<SeasonDto>> getAllSeasons();
    NetflixResponse<SeasonDto> getSeasonById(int id);
    NetflixResponse<SeasonDto> createSeason(SeasonDto seasonDto);
    NetflixResponse<SeasonDto> updateSeason(SeasonDto seasonDto);
    NetflixResponse<SeasonDto> deleteSeason(int id);
}

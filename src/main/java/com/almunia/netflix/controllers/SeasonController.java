package com.almunia.netflix.controllers;

import com.almunia.netflix.dto.SeasonDto;
import com.almunia.netflix.response.NetflixResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface SeasonController {

    NetflixResponse<List<SeasonDto>> getAllSeasons();
    NetflixResponse<SeasonDto> getSeasonById(@PathVariable("id") int id);
    NetflixResponse<SeasonDto> createSeasons(@RequestBody SeasonDto seasonDto);
    NetflixResponse<SeasonDto> updateSeason(@RequestBody SeasonDto seasonDto);
    NetflixResponse<SeasonDto> deleteSeason(@PathVariable("id") int id);
}

package com.almunia.netflix.controllers.impl;

import com.almunia.netflix.controllers.SeasonController;
import com.almunia.netflix.dto.SeasonDto;
import com.almunia.netflix.response.NetflixResponse;
import com.almunia.netflix.services.SeasonService;
import com.almunia.netflix.utils.constants.CommonConstants;
import com.almunia.netflix.utils.constants.RestConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(RestConstants.RESOURCE_SEASONS)
public class SeasonControllerImpl implements SeasonController{

    private final SeasonService seasonService;

    public SeasonControllerImpl(final SeasonService seasonService){
        this.seasonService = seasonService;
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<List<SeasonDto>> getAllSeasons(){
        return new NetflixResponse<>(200, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                seasonService.getAllSeasons());
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = RestConstants.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<SeasonDto> getSeasonById(@PathVariable("id") int id) {
        return new NetflixResponse<>(200, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                seasonService.getSeasonById(id));
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<SeasonDto> createSeason(@RequestBody SeasonDto seasonDto) {
        return new NetflixResponse<>(201, String.valueOf(HttpStatus.CREATED), CommonConstants.SEASON_CREATED_SUCCESSFULLY,
                seasonService.createSeason(seasonDto));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<SeasonDto> updateSeason(@RequestBody SeasonDto seasonDto) {
        return new NetflixResponse<>(200, String.valueOf(HttpStatus.OK), CommonConstants.SEASON_UPDATED_SUCCESSFULLY,
                seasonService.updateSeason(seasonDto));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = RestConstants.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<SeasonDto> deleteSeason(@PathVariable("id") int id) {
        return new NetflixResponse<>(200, String.valueOf(HttpStatus.OK), CommonConstants.SEASON_DELETED_SUCCESSFULLY,
                seasonService.deleteSeason(id));
    }
}

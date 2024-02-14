package com.almunia.netflix.controllers;

import com.almunia.netflix.dto.AwardDto;
import com.almunia.netflix.response.NetflixResponse;
import com.almunia.netflix.utils.constants.RestConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface AwardController {
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    NetflixResponse<List<AwardDto>> getAllAwards();

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = RestConstants.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    NetflixResponse<AwardDto> getAwardById(@PathVariable("id") int id);

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    NetflixResponse<AwardDto> createAward(@RequestBody AwardDto awardDto);

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    NetflixResponse<AwardDto> updateAward(@RequestBody AwardDto awardDto);

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = RestConstants.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    NetflixResponse<AwardDto> deleteAward(@PathVariable("id") int id);
}

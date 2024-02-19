package com.almunia.netflix.controllers;

import com.almunia.netflix.dto.AwardDto;
import com.almunia.netflix.exceptions.NetflixException;
import com.almunia.netflix.response.NetflixResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface AwardController {

    NetflixResponse<List<AwardDto>> getAllAwards();

    NetflixResponse<AwardDto> getAwardById(@PathVariable("id") int id) throws NetflixException;

    NetflixResponse<AwardDto> createAward(@RequestBody AwardDto awardDto) throws NetflixException;

    NetflixResponse<AwardDto> updateAward(@RequestBody AwardDto awardDto) throws NetflixException;

    NetflixResponse<AwardDto> deleteAward(@PathVariable("id") int id) throws NetflixException;
}

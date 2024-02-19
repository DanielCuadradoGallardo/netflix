package com.almunia.netflix.services;

import com.almunia.netflix.dto.AwardDto;
import com.almunia.netflix.exceptions.NetflixException;

import java.util.List;

public interface AwardService {
    List<AwardDto> getAllAwards();

    AwardDto getAwardById(int id) throws NetflixException;

    AwardDto getAwardByName(String name) throws NetflixException;

    AwardDto createAward(AwardDto awardDto) throws NetflixException;

    AwardDto updateAward(AwardDto awardDto) throws NetflixException;

    AwardDto deleteAward(int id) throws NetflixException;
}

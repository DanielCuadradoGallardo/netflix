package com.almunia.netflix.services;

import com.almunia.netflix.dto.AwardDto;

import java.util.List;

public interface AwardService {
    List<AwardDto> getAllAwards();

    AwardDto getAwardById(int id);

    AwardDto getAwardByName(String name);

    AwardDto createAward(AwardDto awardDto);

    AwardDto updateAward(AwardDto awardDto);

    AwardDto deleteAward(int id);
}

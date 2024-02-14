package com.almunia.netflix.services.impl;

import com.almunia.netflix.dto.AwardDto;
import com.almunia.netflix.entities.Award;
import com.almunia.netflix.repositories.AwardRepository;
import com.almunia.netflix.services.AwardService;
import com.almunia.netflix.utils.constants.ExceptionConstants;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AwardServiceImpl implements AwardService {
    private final AwardRepository awardRepository;
    private final ModelMapper modelMapper;

    public AwardServiceImpl(final AwardRepository awardRepository) {
        this.awardRepository = awardRepository;
        modelMapper = new ModelMapper();
    }

    @Override
    public List<AwardDto> getAllAwards() {
        return awardRepository.findAll().stream().map(award -> modelMapper.map(award, AwardDto.class)).collect(Collectors.toList());
    }

    @Override
    public AwardDto getAwardById(int id) {
        return modelMapper.map(awardRepository.findById(id).orElseThrow(() -> new RuntimeException(ExceptionConstants.AWARD_NOT_FOUND)), AwardDto.class);
    }

    @Override
    public AwardDto getAwardByName(String name) {
        return modelMapper.map(awardRepository.findAwardByName(name).orElseThrow(() -> new RuntimeException(ExceptionConstants.AWARD_NOT_FOUND)), AwardDto.class);
    }

    @Override
    public AwardDto createAward(AwardDto awardDto) {
        Award award = new Award(0, awardDto.getName(), null);
        if (awardRepository.findAwardByName(award.getName()).isPresent()) {
            throw new RuntimeException(ExceptionConstants.AWARD_ALREADY_EXISTS);
        } else {
            return modelMapper.map(awardRepository.save(award), AwardDto.class);
        }
    }

    @Override
    public AwardDto updateAward(AwardDto awardDto) {
        Award award = new Award(awardDto.getId(), awardDto.getName(), null);
        if(awardRepository.findAwardById(award.getId()).isPresent()){
            if (awardRepository.findAwardByName(award.getName()).isPresent()) {
                throw new RuntimeException(ExceptionConstants.AWARD_ALREADY_EXISTS);
            } else {
                return modelMapper.map(awardRepository.save(award), AwardDto.class);
            }
        }else{
            throw new RuntimeException(ExceptionConstants.AWARD_NOT_FOUND);
        }
    }

    @Override
    public AwardDto deleteAward(int id) {
        Award award = awardRepository.findAwardById(id).orElse(null);
        if(award != null){
            awardRepository.delete(award);
            return modelMapper.map(award, AwardDto.class);
        }else{
            throw new RuntimeException(ExceptionConstants.AWARD_NOT_FOUND);
        }
    }
}

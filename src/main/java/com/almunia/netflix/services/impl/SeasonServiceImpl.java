package com.almunia.netflix.services.impl;

import com.almunia.netflix.dto.SeasonDto;
import com.almunia.netflix.dto.SerieDto;
import com.almunia.netflix.entities.Season;
import com.almunia.netflix.entities.Serie;
import com.almunia.netflix.repositories.SeasonRepository;
import com.almunia.netflix.services.SeasonService;
import com.almunia.netflix.services.SerieService;
import com.almunia.netflix.utils.constants.ExceptionConstants;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeasonServiceImpl implements SeasonService {

    private final SeasonRepository seasonRepository;
    private final SerieService serieService;
    private final ModelMapper modelMapper;

    public SeasonServiceImpl(final SeasonRepository seasonRepository, final SerieService serieService) {
        this.seasonRepository = seasonRepository;
        this.serieService = serieService;
        modelMapper = new ModelMapper();
    }

    @Override
    public List<SeasonDto> getAllSeasons() {
        return seasonRepository.findAll().stream().map(season -> modelMapper.map(season, SeasonDto.class)).collect(Collectors.toList());
    }

    @Override
    public SeasonDto getSeasonById(int id) {
        return modelMapper.map(seasonRepository.findById(id).orElse(null), SeasonDto.class);
    }

    @Override
    public SeasonDto createSeason(SeasonDto seasonDto) {
        SerieDto serieDto = serieService.getSerieById(seasonDto.getSerie().getId());

        Serie serie = new Serie(serieDto.getId(), serieDto.getName(), serieDto.getDescription(), serieDto.getRecommended_age(), null, null);

        Season season = new Season(seasonDto.getId(), seasonDto.getTitle(), seasonDto.getDescription(), serie, null);

        if (seasonRepository.findSeasonBySerieNameAndTitle(season.getSerie().getName(), season.getTitle()).isPresent()) {
            throw new RuntimeException(ExceptionConstants.SEASON_ALREADY_EXISTS);
        } else {
            return modelMapper.map(seasonRepository.save(season), SeasonDto.class);
        }
    }

    @Override
    public SeasonDto deleteSeason(int id) {
        Season season = seasonRepository.findSeasonById(id).orElse(null);
        if(season != null){
            season.setSerie(null);
            season.setChapters(null);
            seasonRepository.delete(season);
            return modelMapper.map(season, SeasonDto.class);
        }else{
            throw new RuntimeException(ExceptionConstants.SEASON_NOT_FOUND);
        }
    }

    @Override
    public SeasonDto updateSeason(SeasonDto seasonDto) {
        Season season = new Season(seasonDto.getId(), seasonDto.getTitle(), seasonDto.getDescription(), null, null);
        Season seasonToUpdate = seasonRepository.findSeasonById(season.getId()).orElse(null);

        if(seasonToUpdate != null){
            season.setSerie(seasonToUpdate.getSerie());

            if (seasonRepository.findSeasonBySerie_idAndTitle(season.getSerie().getId(), season.getTitle()).isPresent()) {
                throw new RuntimeException(ExceptionConstants.SEASON_ALREADY_EXISTS);
            } else {
                if (season.getTitle() == null || season.getTitle().isEmpty()) {
                    season.setTitle(seasonToUpdate.getTitle());
                }
                if (season.getDescription() == null || season.getDescription().isEmpty()) {
                    season.setDescription(seasonToUpdate.getDescription());
                }
                season.setChapters(seasonToUpdate.getChapters());
                
                Season savedSeason = seasonRepository.save(season);
                savedSeason.setSerie(null);
                return modelMapper.map(savedSeason, SeasonDto.class);
            }
        }else{
            throw new RuntimeException(ExceptionConstants.SEASON_NOT_FOUND);
        }
    }

}

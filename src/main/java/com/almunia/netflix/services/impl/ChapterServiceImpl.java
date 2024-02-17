package com.almunia.netflix.services.impl;

import com.almunia.netflix.dto.ChapterDto;
import com.almunia.netflix.dto.SeasonDto;
import com.almunia.netflix.entities.Chapter;
import com.almunia.netflix.entities.Season;
import com.almunia.netflix.exceptions.AlreadyExistsException;
import com.almunia.netflix.exceptions.NetflixException;
import com.almunia.netflix.exceptions.NotFoundException;
import com.almunia.netflix.repositories.ChapterRepository;
import com.almunia.netflix.services.ChapterService;
import com.almunia.netflix.services.SeasonService;
import com.almunia.netflix.utils.constants.ExceptionConstants;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChapterServiceImpl implements ChapterService {
    private final ChapterRepository chapterRepository;
    private final SeasonService seasonService;
    private final ModelMapper modelMapper;


    public ChapterServiceImpl(final ChapterRepository chapterRepository, final SeasonService seasonService) {
        this.chapterRepository = chapterRepository;
        this.seasonService = seasonService;
        modelMapper = new ModelMapper();
    }

    @Override
    public List<ChapterDto> getAllChapters() {
        return chapterRepository.findAll().stream().map(chapter -> modelMapper.map(chapter, ChapterDto.class)).collect(Collectors.toList());
    }

    @Override
    public ChapterDto getChapterById(int id) throws NetflixException {
        return modelMapper.map(chapterRepository.findById(id).orElseThrow(() -> new NotFoundException(ExceptionConstants.CHAPTER_NOT_FOUND)), ChapterDto.class);
    }

    @Override
    public ChapterDto createChapter(ChapterDto chapterDto) throws NetflixException {
        SeasonDto seasonDto = seasonService.getSeasonById(chapterDto.getSeason().getId());

        Season season = new Season(seasonDto.getId(), seasonDto.getTitle(), seasonDto.getDescription(), null, null);

        Chapter chapter = new Chapter(chapterDto.getId(), chapterDto.getTitle(), chapterDto.getDescription(), chapterDto.getDuration(), chapterDto.getRelease_date(), season, null);

        if (chapterRepository.findChapterBySeasonTitleAndTitle(chapter.getSeason().getTitle(), chapter.getTitle()).isPresent()) {
            throw new AlreadyExistsException(ExceptionConstants.CHAPTER_ALREADY_EXISTS);
        } else {
            return modelMapper.map(chapterRepository.save(chapter), ChapterDto.class);
        }
    }

    @Override
    public ChapterDto updateChapter(ChapterDto chapterDto) throws NetflixException {
        Chapter chapter = new Chapter(chapterDto.getId(), chapterDto.getTitle(), chapterDto.getDescription(), chapterDto.getDuration(), chapterDto.getRelease_date(), null, null);
        Chapter chapterToUpdate = chapterRepository.findChapterById(chapter.getId()).orElse(null);

        if(chapterToUpdate != null){
            chapter.setSeason(chapterToUpdate.getSeason());

            if (chapterRepository.findChapterBySeason_idAndTitle(chapter.getSeason().getId(), chapter.getTitle()).isPresent()) {
                throw new AlreadyExistsException(ExceptionConstants.CHAPTER_ALREADY_EXISTS);
            } else {
                if (chapter.getTitle() == null || chapter.getTitle().isEmpty()) {
                    chapter.setTitle(chapterToUpdate.getTitle());
                }
                if (chapter.getDescription() == null || chapter.getDescription().isEmpty()) {
                    chapter.setDescription(chapterToUpdate.getDescription());
                }
                chapter.setActors(chapterToUpdate.getActors());

                Chapter savedChapter = chapterRepository.save(chapter);
                savedChapter.setSeason(null);
                return modelMapper.map(savedChapter, ChapterDto.class);
            }
        }else{
            throw new NotFoundException(ExceptionConstants.CHAPTER_NOT_FOUND);
        }
    }

    @Override
    public ChapterDto deleteChapter(int id) throws NetflixException {
        Chapter chapter = chapterRepository.findChapterById(id).orElse(null);
        if(chapter != null){
            chapter.setActors(null);
            Season season = chapter.getSeason();
            chapterRepository.delete(chapter);
            season.getChapters().remove(chapter);
            return modelMapper.map(chapter, ChapterDto.class);
        }else{
            throw new NotFoundException(ExceptionConstants.CHAPTER_NOT_FOUND);
        }
    }
}

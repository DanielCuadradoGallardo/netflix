package com.almunia.netflix.services.impl;

import com.almunia.netflix.dto.ChapterDto;
import com.almunia.netflix.dto.SeasonDto;
import com.almunia.netflix.entities.Chapter;
import com.almunia.netflix.entities.Season;
import com.almunia.netflix.exceptions.AlreadyExistsException;
import com.almunia.netflix.exceptions.NetflixException;
import com.almunia.netflix.exceptions.NotFoundException;
import com.almunia.netflix.repositories.ChapterRepository;
import com.almunia.netflix.services.SeasonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ChapterServiceImplTest {
    @Mock
    private ChapterRepository chapterRepository;
    @Mock
    private SeasonService seasonService;
    @InjectMocks
    private ChapterServiceImpl chapterService;
    private final ModelMapper modelMapper = new ModelMapper();

    @Test
    void getAllChapters() {
        // GIVEN
        Chapter chapter1 = Chapter.builder()
                .title("Chapter 1")
                .description("Introduction to the story")
                .duration(30)
                .release_date(LocalDate.of(2022, 1, 1))
                .build();

        Chapter chapter2 = Chapter.builder()
                .title("Chapter 2")
                .description("The adventure continues")
                .duration(45)
                .release_date(LocalDate.of(2022, 2, 15))
                .build();

        // WHEN
        when(chapterRepository.findAll()).thenReturn(Arrays.asList(chapter1, chapter2));

        // THEN
        assertEquals(modelMapper.map(chapter1, ChapterDto.class), chapterService.getAllChapters().get(0));
        assertEquals(modelMapper.map(chapter2, ChapterDto.class), chapterService.getAllChapters().get(1));
    }

    @Test
    void getChapterById() throws NetflixException {
        // GIVEN
        Chapter chapter = Chapter.builder()
                .title("Chapter 1")
                .description("Introduction to the story")
                .duration(30)
                .release_date(LocalDate.of(2022, 1, 1))
                .build();

        // WHEN
        when(chapterRepository.findById(anyInt())).thenReturn(Optional.of(chapter));

        // THEN
        assertEquals(modelMapper.map(chapter, ChapterDto.class), chapterService.getChapterById(1));
    }

    @Test
    void getChapterById_NotFound() {
        // WHEN
        when(chapterRepository.findById(anyInt())).thenReturn(Optional.empty());

        // THEN
        assertThrows(NotFoundException.class, () -> chapterService.getChapterById(1));
    }

    @Test
    void createChapter() throws NetflixException {
        // GIVEN
        Chapter chapter = Chapter.builder()
                .title("Chapter 1")
                .description("Introduction to the story")
                .duration(30)
                .release_date(LocalDate.of(2022, 1, 1))
                .build();

        SeasonDto season = SeasonDto.builder()
                .id(1)
                .title("Season 1")
                .description("The beginning of the series")
                .build();

        ChapterDto chapterDto = ChapterDto.builder()
                .title("Chapter 1")
                .season(season)
                .build();

        // WHEN
        when(seasonService.getSeasonById(anyInt())).thenReturn(season);
        when(chapterRepository.findChapterBySeasonTitleAndTitle(anyString(), anyString())).thenReturn(Optional.empty());
        when(chapterRepository.save(any())).thenReturn(chapter);

        // THEN
        assertEquals(modelMapper.map(chapter, ChapterDto.class), chapterService.createChapter(chapterDto));
    }

    @Test
    void createChapter_AlreadyExists() throws NetflixException {
        // GIVEN
        Chapter chapter = Chapter.builder()
                .title("Chapter 1")
                .description("Introduction to the story")
                .duration(30)
                .release_date(LocalDate.of(2022, 1, 1))
                .build();

        SeasonDto season = SeasonDto.builder()
                .title("Season 1")
                .description("The beginning of the series")
                .build();

        ChapterDto chapterDto = ChapterDto.builder()
                .title("Chapter 1")
                .description("Introduction to the story")
                .duration(30)
                .release_date(LocalDate.of(2022, 1, 1))
                .season(season)
                .build();

        // WHEN
        when(seasonService.getSeasonById(anyInt())).thenReturn(season);
        when(chapterRepository.findChapterBySeasonTitleAndTitle(anyString(), anyString())).thenReturn(Optional.of(chapter));

        // THEN
        assertThrows(AlreadyExistsException.class, () -> chapterService.createChapter(chapterDto));
    }

    @Test
    void updateChapter() throws NetflixException {
        // GIVEN
        Season season = Season.builder()
                .title("Season 1")
                .description("The beginning of the series")
                .build();

        Chapter chapter = Chapter.builder()
                .title("Chapter 1")
                .description("Introduction to the story")
                .duration(30)
                .release_date(LocalDate.of(2022, 1, 1))
                .season(season)
                .build();

        ChapterDto chapterDto = ChapterDto.builder()
                .title("")
                .description("")
                .duration(60)
                .release_date(LocalDate.of(2023, 2, 13))
                .build();

        Chapter chapterUpdated = Chapter.builder()
                .title("Chapter 1")
                .description("Introduction to the story")
                .duration(60)
                .release_date(LocalDate.of(2023, 2, 13))
                .build();

        // WHEN
        when(chapterRepository.findChapterById(anyInt())).thenReturn(Optional.of(chapter));
        when(chapterRepository.findChapterBySeason_idAndTitle(anyInt(), anyString())).thenReturn(Optional.empty());
        when(chapterRepository.save(any())).thenReturn(chapterUpdated);

        // THEN
        assertEquals(modelMapper.map(chapterUpdated, ChapterDto.class), chapterService.updateChapter(chapterDto));
    }

    @Test
    void updateChapter_NotFound() {
        // GIVEN
        ChapterDto chapterDto = ChapterDto.builder()
                .title("Chapter 3")
                .description("Continuation of the story")
                .duration(30)
                .release_date(LocalDate.of(2022, 1, 1))
                .build();

        // WHEN
        when(chapterRepository.findChapterById(anyInt())).thenReturn(Optional.empty());

        // THEN
        assertThrows(NotFoundException.class, () -> chapterService.updateChapter(chapterDto));
    }

    @Test
    void updateChapter_AlreadyExists() {
        // GIVEN
        Season season = Season.builder()
                .title("Season 1")
                .description("The beginning of the series")
                .build();

        Chapter chapter = Chapter.builder()
                .title("Chapter 1")
                .description("Introduction to the story")
                .duration(30)
                .release_date(LocalDate.of(2022, 1, 1))
                .season(season)
                .build();

        ChapterDto chapterDto = ChapterDto.builder()
                .title("Chapter 3")
                .description("Continuation of the story")
                .duration(30)
                .release_date(LocalDate.of(2022, 1, 1))
                .build();

        // WHEN
        when(chapterRepository.findChapterById(anyInt())).thenReturn(Optional.of(chapter));
        when(chapterRepository.findChapterBySeason_idAndTitle(anyInt(), anyString())).thenReturn(Optional.of(chapter));

        // THEN
        assertThrows(AlreadyExistsException.class, () -> chapterService.updateChapter(chapterDto));
    }

    @Test
    void deleteChapter() throws NetflixException {
        // GIVEN
        Season season = Season.builder()
                .title("Season 1")
                .description("The beginning of the series")
                .chapters(new ArrayList<>())
                .build();

        Chapter chapter = Chapter.builder()
                .title("Chapter 1")
                .description("Introduction to the story")
                .duration(30)
                .release_date(LocalDate.of(2022, 1, 1))
                .season(season)
                .build();

        // WHEN
        when(chapterRepository.findChapterById(anyInt())).thenReturn(Optional.of(chapter));

        // THEN
        assertEquals(modelMapper.map(chapter, ChapterDto.class), chapterService.deleteChapter(1));
    }

    @Test
    void deleteChapter_NotFound() {
        // WHEN
        when(chapterRepository.findChapterById(anyInt())).thenReturn(Optional.empty());

        // THEN
        assertThrows(NotFoundException.class, () -> chapterService.deleteChapter(1));
    }
}

package com.almunia.netflix.services;

import com.almunia.netflix.dto.ChapterDto;
import com.almunia.netflix.exceptions.NetflixException;

import java.util.List;

public interface ChapterService {
    List<ChapterDto> getAllChapters();

    ChapterDto getChapterById(int id) throws NetflixException;

    ChapterDto createChapter(ChapterDto chapterDto) throws NetflixException;

    ChapterDto updateChapter(ChapterDto chapterDto) throws NetflixException;

    ChapterDto deleteChapter(int id) throws NetflixException;
}

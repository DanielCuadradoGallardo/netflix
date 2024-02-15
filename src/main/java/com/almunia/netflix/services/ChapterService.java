package com.almunia.netflix.services;

import com.almunia.netflix.dto.ChapterDto;

import java.util.List;

public interface ChapterService {
    List<ChapterDto> getAllChapters();

    ChapterDto getChapterById(int id);

    ChapterDto createChapter(ChapterDto chapterDto);

    ChapterDto deleteChapter(int id);

    ChapterDto updateChapter(ChapterDto chapterDto);
}

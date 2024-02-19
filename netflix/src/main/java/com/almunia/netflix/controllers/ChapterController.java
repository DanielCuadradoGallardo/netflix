package com.almunia.netflix.controllers;

import com.almunia.netflix.dto.ChapterDto;
import com.almunia.netflix.exceptions.NetflixException;
import com.almunia.netflix.response.NetflixResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ChapterController {

    NetflixResponse<List<ChapterDto>> getAllChapters();

    NetflixResponse<ChapterDto> getChapterById(@PathVariable("id") int id) throws NetflixException;

    NetflixResponse<ChapterDto> createChapter(@RequestBody ChapterDto chapterDto) throws NetflixException;

    NetflixResponse<ChapterDto> updateChapter(@RequestBody ChapterDto chapterDto) throws NetflixException;

    NetflixResponse<ChapterDto> deleteChapter(@PathVariable("id") int id) throws NetflixException;
}

package com.almunia.netflix.controllers;

import com.almunia.netflix.dto.ChapterDto;
import com.almunia.netflix.exceptions.NetflixException;
import com.almunia.netflix.response.NetflixResponse;
import com.almunia.netflix.utils.constants.RestConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ChapterController {
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    NetflixResponse<List<ChapterDto>> getAllChapters();

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = RestConstants.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    NetflixResponse<ChapterDto> getChapterById(@PathVariable("id") int id) throws NetflixException;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    NetflixResponse<ChapterDto> createChapter(@RequestBody ChapterDto chapterDto) throws NetflixException;

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    NetflixResponse<ChapterDto> updateChapter(@RequestBody ChapterDto chapterDto) throws NetflixException;

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = RestConstants.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    NetflixResponse<ChapterDto> deleteChapter(@PathVariable("id") int id) throws NetflixException;
}

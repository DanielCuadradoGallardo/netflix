package com.almunia.netflix.controllers.impl;

import com.almunia.netflix.controllers.ChapterController;
import com.almunia.netflix.dto.ChapterDto;
import com.almunia.netflix.response.NetflixResponse;
import com.almunia.netflix.services.ChapterService;
import com.almunia.netflix.utils.constants.CommonConstants;
import com.almunia.netflix.utils.constants.RestConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(RestConstants.RESOURCE_SEASONS)
public class ChapterControllerImpl implements ChapterController {

    private  final ChapterService chapterService;

    public ChapterControllerImpl(final ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<List<ChapterDto>> getAllChapters(){
        return new NetflixResponse<>(200, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                chapterService.getAllChapters());
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = RestConstants.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<ChapterDto> getChapterById(@PathVariable("id") int id) {
        return new NetflixResponse<>(200, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                chapterService.getChapterById(id));
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<ChapterDto> createChapter(@RequestBody ChapterDto chapterDto) {
        return new NetflixResponse<>(201, String.valueOf(HttpStatus.CREATED), CommonConstants.CHAPTER_CREATED_SUCCESSFULLY,
                chapterService.createChapter(chapterDto));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<ChapterDto> updateChapter(@RequestBody ChapterDto chapterDto) {
        return new NetflixResponse<>(200, String.valueOf(HttpStatus.OK), CommonConstants.CHAPTER_UPDATED_SUCCESSFULLY,
                chapterService.updateChapter(chapterDto));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = RestConstants.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<ChapterDto> deleteChapter(@PathVariable("id") int id) {
        return new NetflixResponse<>(200, String.valueOf(HttpStatus.OK), CommonConstants.CHAPTER_DELETED_SUCCESSFULLY,
                chapterService.deleteChapter(id));
    }
}

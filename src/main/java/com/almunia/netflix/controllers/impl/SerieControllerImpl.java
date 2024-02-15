package com.almunia.netflix.controllers.impl;

import com.almunia.netflix.controllers.SerieController;
import com.almunia.netflix.dto.SerieDto;
import com.almunia.netflix.response.NetflixResponse;
import com.almunia.netflix.services.SerieService;
import com.almunia.netflix.utils.constants.CommonConstants;
import com.almunia.netflix.utils.constants.RestConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(RestConstants.RESOURCE_SERIES)
public class SerieControllerImpl implements SerieController {
    private final SerieService serieService;

    public SerieControllerImpl(final SerieService serieService){
        this.serieService = serieService;
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<List<SerieDto>> getAllSeries(){
        return new NetflixResponse<>(200, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                serieService.getAllSeries());
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping(value = RestConstants.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<SerieDto> getSerieById(@PathVariable("id") int id) {
        return new NetflixResponse<>(200, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                serieService.getSerieById(id));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping(value = RestConstants.RESOURCE_CATEGORY, produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<List<SerieDto>> getSeriesByCategory(@PathVariable("category") String categoryName) {
        return new NetflixResponse<>(200, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                serieService.getSeriesByCategory(categoryName));
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<SerieDto> createSerie(@RequestBody SerieDto serieDto) {
        return new NetflixResponse<>(201, String.valueOf(HttpStatus.CREATED), CommonConstants.SERIE_CREATED_SUCCESSFULLY,
                serieService.createSerie(serieDto));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<SerieDto> updateSerie(@RequestBody SerieDto serieDto) {
        return new NetflixResponse<>(200, String.valueOf(HttpStatus.OK), CommonConstants.SERIE_UPDATED_SUCCESSFULLY,
                serieService.updateSerie(serieDto));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = RestConstants.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<SerieDto> deleteSerie(@PathVariable("id") int id) {
        return new NetflixResponse<>(200, String.valueOf(HttpStatus.OK), CommonConstants.SERIE_DELETED_SUCCESSFULLY,
                serieService.deleteSerie(id));
    }
}

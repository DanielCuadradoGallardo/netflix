package com.almunia.netflix.controllers.impl;

import com.almunia.netflix.controllers.AwardController;
import com.almunia.netflix.dto.AwardDto;
import com.almunia.netflix.response.NetflixResponse;
import com.almunia.netflix.services.AwardService;
import com.almunia.netflix.utils.constants.CommonConstants;
import com.almunia.netflix.utils.constants.RestConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(RestConstants.RESOURCE_AWARDS)
public class AwardControllerImpl implements AwardController {

    private final AwardService awardService;

    public AwardControllerImpl(final AwardService awardService) {
        this.awardService = awardService;
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<List<AwardDto>> getAllAwards(){
        return new NetflixResponse<>(200, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                awardService.getAllAwards());
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping(value = RestConstants.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<AwardDto> getAwardById(@PathVariable("id") int id) {
        return new NetflixResponse<>(200, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                awardService.getAwardById(id));
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<AwardDto> createAward(@RequestBody AwardDto awardDto) {
        return new NetflixResponse<>(201, String.valueOf(HttpStatus.CREATED), CommonConstants.AWARD_CREATED_SUCCESSFULLY,
                awardService.createAward(awardDto));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<AwardDto> updateAward(@RequestBody AwardDto awardDto) {
        return new NetflixResponse<>(200, String.valueOf(HttpStatus.OK), CommonConstants.AWARD_UPDATED_SUCCESSFULLY,
                awardService.updateAward(awardDto));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = RestConstants.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<AwardDto> deleteAward(@PathVariable("id") int id) {
        return new NetflixResponse<>(200, String.valueOf(HttpStatus.OK), CommonConstants.AWARD_DELETED_SUCCESSFULLY,
                awardService.deleteAward(id));
    }
}

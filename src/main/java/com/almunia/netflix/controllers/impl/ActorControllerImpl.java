package com.almunia.netflix.controllers.impl;

import com.almunia.netflix.controllers.ActorController;
import com.almunia.netflix.dto.ActorDto;
import com.almunia.netflix.response.NetflixResponse;
import com.almunia.netflix.services.ActorService;
import com.almunia.netflix.utils.constants.CommonConstants;
import com.almunia.netflix.utils.constants.RestConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(RestConstants.RESOURCE_ACTORS)
public class ActorControllerImpl implements ActorController {

    private final ActorService actorService;

    public ActorControllerImpl(final ActorService actorService){
        this.actorService = actorService;
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<List<ActorDto>> getAllActors(){
        return new NetflixResponse<>(200, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                actorService.getAllActors());
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping(value = RestConstants.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<ActorDto> getActorById(@PathVariable("id") int id) {
        return new NetflixResponse<>(200, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                actorService.getActorById(id));
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<ActorDto> createActor(@RequestBody ActorDto actorDto) {
        return new NetflixResponse<>(201, String.valueOf(HttpStatus.CREATED), CommonConstants.ACTOR_CREATED_SUCCESSFULLY,
                actorService.createActor(actorDto));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<ActorDto> updateActor(@RequestBody ActorDto actorDto) {
        return new NetflixResponse<>(200, String.valueOf(HttpStatus.OK), CommonConstants.ACTOR_UPDATED_SUCCESSFULLY,
                actorService.updateActor(actorDto));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = RestConstants.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<ActorDto> deleteActor(@PathVariable("id") int id) {
        return new NetflixResponse<>(200, String.valueOf(HttpStatus.OK), CommonConstants.ACTOR_DELETED_SUCCESSFULLY,
                actorService.deleteActor(id));
    }
}

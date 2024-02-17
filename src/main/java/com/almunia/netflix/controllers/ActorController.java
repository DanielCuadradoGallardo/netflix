package com.almunia.netflix.controllers;

import com.almunia.netflix.dto.ActorDto;
import com.almunia.netflix.exceptions.NetflixException;
import com.almunia.netflix.exceptions.NotFoundException;
import com.almunia.netflix.response.NetflixResponse;
import com.almunia.netflix.utils.constants.RestConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ActorController {

    NetflixResponse<List<ActorDto>> getAllActors();

    NetflixResponse<ActorDto> getActorById(int id);

    NetflixResponse<ActorDto> createActor(ActorDto actorDto);

    NetflixResponse<ActorDto> updateActor(ActorDto actorDto) throws NetflixException;

    NetflixResponse<ActorDto> deleteActor(int id);
}

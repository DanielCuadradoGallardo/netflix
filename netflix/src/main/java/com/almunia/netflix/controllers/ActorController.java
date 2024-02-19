package com.almunia.netflix.controllers;

import com.almunia.netflix.dto.ActorDto;
import com.almunia.netflix.exceptions.NetflixException;
import com.almunia.netflix.response.NetflixResponse;

import java.util.List;

public interface ActorController {

    NetflixResponse<List<ActorDto>> getAllActors();

    NetflixResponse<ActorDto> getActorById(int id) throws NetflixException;

    NetflixResponse<ActorDto> createActor(ActorDto actorDto) throws NetflixException;

    NetflixResponse<ActorDto> updateActor(ActorDto actorDto) throws NetflixException;

    NetflixResponse<ActorDto> deleteActor(int id) throws NetflixException;
}

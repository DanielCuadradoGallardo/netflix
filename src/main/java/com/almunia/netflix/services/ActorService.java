package com.almunia.netflix.services;

import com.almunia.netflix.dto.ActorDto;
import com.almunia.netflix.exceptions.NetflixException;
import com.almunia.netflix.exceptions.NotFoundException;

import java.util.List;

public interface ActorService {
    List<ActorDto> getAllActors();

    ActorDto getActorById(int id) throws NetflixException;

    ActorDto getActorByName(String name) throws NetflixException;

    ActorDto createActor(ActorDto actorDto) throws NetflixException;

    ActorDto updateActor(ActorDto actorDto) throws NetflixException;

    ActorDto deleteActor(int id) throws NetflixException;
}

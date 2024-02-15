package com.almunia.netflix.services;

import com.almunia.netflix.dto.ActorDto;

import java.util.List;

public interface ActorService {
    List<ActorDto> getAllActors();

    ActorDto getActorById(int id);

    ActorDto getActorByName(String name);

    ActorDto createActor(ActorDto actorDto);

    ActorDto updateActor(ActorDto actorDto);

    ActorDto deleteActor(int id);
}

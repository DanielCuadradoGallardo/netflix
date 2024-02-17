package com.almunia.netflix.services.impl;

import com.almunia.netflix.dto.ActorDto;
import com.almunia.netflix.entities.Actor;
import com.almunia.netflix.exceptions.NetflixException;
import com.almunia.netflix.exceptions.NotFoundException;
import com.almunia.netflix.repositories.ActorRepository;
import com.almunia.netflix.services.ActorService;
import com.almunia.netflix.utils.constants.ExceptionConstants;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActorServiceImpl implements ActorService {
    private final ActorRepository actorRepository;
    private final ModelMapper modelMapper;

    public ActorServiceImpl(final ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
        modelMapper = new ModelMapper();
    }

    @Override
    public List<ActorDto> getAllActors() {
        return actorRepository.findAll().stream().map(actor -> modelMapper.map(actor, ActorDto.class)).collect(Collectors.toList());
    }

    @Override
    public ActorDto getActorById(int id) {
        return modelMapper.map(actorRepository.findById(id).orElseThrow(() -> new RuntimeException(ExceptionConstants.ACTOR_NOT_FOUND)), ActorDto.class);
    }

    @Override
    public ActorDto getActorByName(String name) {
        return modelMapper.map(actorRepository.findActorByName(name).orElseThrow(() -> new RuntimeException(ExceptionConstants.ACTOR_NOT_FOUND)), ActorDto.class);
    }

    @Override
    public ActorDto createActor(ActorDto actorDto) {
        Actor actor = new Actor(0, actorDto.getName(), actorDto.getBirth_date(), actorDto.getBirth_place(), actorDto.getBiography(), null, null);
        if (actorRepository.findActorByName(actor.getName()).isPresent()) {
            throw new RuntimeException(ExceptionConstants.ACTOR_ALREADY_EXISTS);
        } else {
            return modelMapper.map(actorRepository.save(actor), ActorDto.class);
        }
    }

    @Override
    public ActorDto updateActor(ActorDto actorDto) throws NetflixException{
        Actor actor = new Actor(0, actorDto.getName(), actorDto.getBirth_date(), actorDto.getBirth_place(), actorDto.getBiography(), null, null);
        if(actorRepository.findActorById(actor.getId()).isPresent()){
            if (actorRepository.findActorByName(actor.getName()).isPresent()) {
                throw new RuntimeException(ExceptionConstants.ACTOR_ALREADY_EXISTS);
            } else {
                return modelMapper.map(actorRepository.save(actor), ActorDto.class);
            }
        }else{
            throw new NotFoundException(ExceptionConstants.ACTOR_NOT_FOUND);
        }
    }

    @Override
    public ActorDto deleteActor(int id) {
        Actor actor = actorRepository.findActorById(id).orElse(null);
        if(actor != null){
            actorRepository.delete(actor);
            return modelMapper.map(actor, ActorDto.class);
        }else{
            throw new RuntimeException(ExceptionConstants.ACTOR_NOT_FOUND);
        }
    }
}

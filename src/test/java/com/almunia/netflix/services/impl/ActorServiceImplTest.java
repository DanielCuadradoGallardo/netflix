package com.almunia.netflix.services.impl;

import com.almunia.netflix.dto.ActorDto;
import com.almunia.netflix.entities.Actor;
import com.almunia.netflix.exceptions.AlreadyExistsException;
import com.almunia.netflix.exceptions.NetflixException;
import com.almunia.netflix.exceptions.NotFoundException;
import com.almunia.netflix.repositories.ActorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ActorServiceImplTest {
    @Mock
    private ActorRepository actorRepository;
    @InjectMocks
    private ActorServiceImpl actorService;
    private final ModelMapper modelMapper = new ModelMapper();

    @Test
    void getAllActors() {
        //GIVEN
        Actor actor1 = Actor.builder()
                .name("John Doe")
                .birth_date(LocalDate.of(1985, 5, 15))
                .birth_place("New York")
                .biography("An accomplished actor with a versatile skill set.")
                .build();

        Actor actor2 = Actor.builder()
                .name("Jane Smith")
                .birth_date(LocalDate.of(1990, 8, 20))
                .birth_place("Los Angeles")
                .biography("A rising star in the film industry.")
                .build();

        //WHEN
        when(actorRepository.findAll()).thenReturn(Arrays.asList(actor1, actor2));

        //THEN
        assertEquals(modelMapper.map(actor1, ActorDto.class), actorService.getAllActors().get(0));
        assertEquals(modelMapper.map(actor2, ActorDto.class), actorService.getAllActors().get(1));
    }

    @Test
    void getActorById() throws NetflixException {
        //GIVEN
        Actor actor = Actor.builder()
                .name("John Doe")
                .birth_date(LocalDate.of(1985, 5, 15))
                .birth_place("New York")
                .biography("An accomplished actor with a versatile skill set.")
                .build();

        //WHEN
        when(actorRepository.findById(anyInt())).thenReturn(Optional.of(actor));

        //THEN
        assertEquals(modelMapper.map(actor, ActorDto.class), actorService.getActorById(1));
    }

    @Test
    void getActorById_NotFound() {
        //WHEN
        when(actorRepository.findById(anyInt())).thenReturn(Optional.empty());

        //THEN
        assertThrows(NotFoundException.class, () -> actorService.getActorById(1));
    }

    @Test
    void getActorByName() throws NetflixException {
        //GIVEN
        Actor actor = Actor.builder()
                .name("John Doe")
                .birth_date(LocalDate.of(1985, 5, 15))
                .birth_place("New York")
                .biography("An accomplished actor with a versatile skill set.")
                .build();

        //WHEN
        when(actorRepository.findActorByName(anyString())).thenReturn(Optional.of(actor));

        //THEN
        assertEquals(modelMapper.map(actor, ActorDto.class), actorService.getActorByName("John Doe"));
    }

    @Test
    void getActorByName_NotFound() {
        //WHEN
        when(actorRepository.findActorByName(anyString())).thenReturn(Optional.empty());

        //THEN
        assertThrows(NotFoundException.class, () -> actorService.getActorByName("John Doe"));
    }

    @Test
    void createActor() throws NetflixException {
        //GIVEN
        Actor actor = Actor.builder()
                .name("John Doe")
                .birth_date(LocalDate.of(1985, 5, 15))
                .birth_place("New York")
                .biography("An accomplished actor with a versatile skill set.")
                .build();

        ActorDto actorDto = ActorDto.builder()
                .name("John Doe")
                .birth_date(LocalDate.of(1985, 5, 15))
                .birth_place("New York")
                .biography("An accomplished actor with a versatile skill set.")
                .build();

        //WHEN
        when(actorRepository.findActorByName(anyString())).thenReturn(Optional.empty());
        when(actorRepository.save(any())).thenReturn(actor);

        //THEN
        assertEquals(modelMapper.map(actor, ActorDto.class), actorService.createActor(actorDto));
    }

    @Test
    void createActor_AlreadyExists() {
        //GIVEN
        Actor actor = Actor.builder()
                .name("John Doe")
                .birth_date(LocalDate.of(1985, 5, 15))
                .birth_place("New York")
                .biography("An accomplished actor with a versatile skill set.")
                .build();

        ActorDto actorDto = ActorDto.builder()
                .name("John Doe")
                .birth_date(LocalDate.of(1985, 5, 15))
                .birth_place("New York")
                .biography("An accomplished actor with a versatile skill set.")
                .build();

        //WHEN
        when(actorRepository.findActorByName(anyString())).thenReturn(Optional.of(actor));

        //THEN
        assertThrows(AlreadyExistsException.class, () -> actorService.createActor(actorDto));
    }

    @Test
    void updateActor() throws NetflixException {
        //GIVEN
        Actor actor = Actor.builder()
                .name("John Doe")
                .birth_date(LocalDate.of(1985, 5, 15))
                .birth_place("New York")
                .biography("An accomplished actor with a versatile skill set.")
                .build();

        ActorDto actorDto = ActorDto.builder()
                .name("John Doe")
                .birth_date(LocalDate.of(1985, 5, 15))
                .birth_place("New York")
                .biography("An accomplished actor with a versatile skill set.")
                .build();

        //WHEN
        when(actorRepository.findActorById(anyInt())).thenReturn(Optional.of(actor));
        when(actorRepository.findActorByName(anyString())).thenReturn(Optional.empty());
        when(actorRepository.save(any())).thenReturn(actor);

        //THEN
        assertEquals(modelMapper.map(actor, ActorDto.class), actorService.updateActor(actorDto));
    }

    @Test
    void updateActor_NotFound() {
        //GIVEN
        ActorDto actorDto = ActorDto.builder()
                .name("John Doe")
                .birth_date(LocalDate.of(1985, 5, 15))
                .birth_place("New York")
                .biography("An accomplished actor with a versatile skill set.")
                .build();

        //WHEN
        when(actorRepository.findActorById(anyInt())).thenReturn(Optional.empty());

        //THEN
        assertThrows(NotFoundException.class, () -> actorService.updateActor(actorDto));
    }

    @Test
    void updateActor_AlreadyExists() {
        //GIVEN
        Actor actor = Actor.builder()
                .name("John Doe")
                .birth_date(LocalDate.of(1985, 5, 15))
                .birth_place("New York")
                .biography("An accomplished actor with a versatile skill set.")
                .build();

        ActorDto actorDto = ActorDto.builder()
                .name("John Doe")
                .birth_date(LocalDate.of(1985, 5, 15))
                .birth_place("New York")
                .biography("An accomplished actor with a versatile skill set.")
                .build();

        //WHEN
        when(actorRepository.findActorById(anyInt())).thenReturn(Optional.of(actor));
        when(actorRepository.findActorByName(anyString())).thenReturn(Optional.of(actor));

        //THEN
        assertThrows(AlreadyExistsException.class, () -> actorService.updateActor(actorDto));
    }

    @Test
    void deleteActor() throws NetflixException {
        //GIVEN
        Actor actor = Actor.builder()
                .name("John Doe")
                .birth_date(LocalDate.of(1985, 5, 15))
                .birth_place("New York")
                .biography("An accomplished actor with a versatile skill set.")
                .build();

        //WHEN
        when(actorRepository.findActorById(anyInt())).thenReturn(Optional.of(actor));

        //THEN
        assertEquals(modelMapper.map(actor, ActorDto.class), actorService.deleteActor(1));
    }

    @Test
    void deleteActor_NotFound() {
        //WHEN
        when(actorRepository.findActorById(anyInt())).thenReturn(Optional.empty());

        //THEN
        assertThrows(NotFoundException.class, () -> actorService.deleteActor(1));
    }
}
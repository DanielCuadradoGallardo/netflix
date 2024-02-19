package com.almunia.netflix.services.impl;

import com.almunia.netflix.dto.AwardDto;
import com.almunia.netflix.entities.Award;
import com.almunia.netflix.exceptions.AlreadyExistsException;
import com.almunia.netflix.exceptions.NetflixException;
import com.almunia.netflix.exceptions.NotFoundException;
import com.almunia.netflix.repositories.AwardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AwardServiceImplTest {
    @Mock
    private AwardRepository awardRepository;
    @InjectMocks
    private AwardServiceImpl awardService;
    private final ModelMapper modelMapper = new ModelMapper();

    @Test
    void getAllAwards() {
        // GIVEN
        Award award1 = Award.builder()
                .name("Best Actor")
                .build();

        Award award2 = Award.builder()
                .name("Best Actress")
                .build();

        // WHEN
        when(awardRepository.findAll()).thenReturn(Arrays.asList(award1, award2));

        // THEN
        assertEquals(modelMapper.map(award1, AwardDto.class), awardService.getAllAwards().get(0));
        assertEquals(modelMapper.map(award2, AwardDto.class), awardService.getAllAwards().get(1));
    }

    @Test
    void getAwardById() throws NetflixException {
        // GIVEN
        Award award = Award.builder()
                .name("Best Actor")
                .build();

        // WHEN
        when(awardRepository.findById(anyInt())).thenReturn(Optional.of(award));

        // THEN
        assertEquals(modelMapper.map(award, AwardDto.class), awardService.getAwardById(1));
    }

    @Test
    void getAwardById_NotFound() {
        // WHEN
        when(awardRepository.findById(anyInt())).thenReturn(Optional.empty());

        // THEN
        assertThrows(NotFoundException.class, () -> awardService.getAwardById(1));
    }

    @Test
    void getAwardByName() throws NetflixException {
        // GIVEN
        Award award = Award.builder()
                .name("Best Actor")
                .build();

        // WHEN
        when(awardRepository.findAwardByName(anyString())).thenReturn(Optional.of(award));

        // THEN
        assertEquals(modelMapper.map(award, AwardDto.class), awardService.getAwardByName("Best Actor"));
    }

    @Test
    void getAwardByName_NotFound() {
        // WHEN
        when(awardRepository.findAwardByName(anyString())).thenReturn(Optional.empty());

        // THEN
        assertThrows(NotFoundException.class, () -> awardService.getAwardByName("Best Actor"));
    }

    @Test
    void createAward() throws NetflixException {
        // GIVEN
        Award award = Award.builder()
                .name("Best Actor")
                .build();

        AwardDto awardDto = AwardDto.builder()
                .name("Best Actor")
                .build();

        // WHEN
        when(awardRepository.findAwardByName(anyString())).thenReturn(Optional.empty());
        when(awardRepository.save(any())).thenReturn(award);

        // THEN
        assertEquals(modelMapper.map(award, AwardDto.class), awardService.createAward(awardDto));
    }

    @Test
    void createAward_AlreadyExists() {
        // GIVEN
        Award award = Award.builder()
                .name("Best Actor")
                .build();

        AwardDto awardDto = AwardDto.builder()
                .name("Best Actor")
                .build();

        // WHEN
        when(awardRepository.findAwardByName(anyString())).thenReturn(Optional.of(award));

        // THEN
        assertThrows(AlreadyExistsException.class, () -> awardService.createAward(awardDto));
    }

    @Test
    void updateAward() throws NetflixException {
        // GIVEN
        Award award = Award.builder()
                .name("Best Actor")
                .build();

        AwardDto awardDto = AwardDto.builder()
                .name("Best Actor")
                .build();

        // WHEN
        when(awardRepository.findAwardById(anyInt())).thenReturn(Optional.of(award));
        when(awardRepository.findAwardByName(anyString())).thenReturn(Optional.empty());
        when(awardRepository.save(any())).thenReturn(award);

        // THEN
        assertEquals(modelMapper.map(award, AwardDto.class), awardService.updateAward(awardDto));
    }

    @Test
    void updateAward_NotFound() {
        // GIVEN
        AwardDto awardDto = AwardDto.builder()
                .name("Best Actor")
                .build();

        // WHEN
        when(awardRepository.findAwardById(anyInt())).thenReturn(Optional.empty());

        // THEN
        assertThrows(NotFoundException.class, () -> awardService.updateAward(awardDto));
    }

    @Test
    void updateAward_AlreadyExists() {
        // GIVEN
        Award award = Award.builder()
                .name("Best Actor")
                .build();

        AwardDto awardDto = AwardDto.builder()
                .name("Best Actor")
                .build();

        // WHEN
        when(awardRepository.findAwardById(anyInt())).thenReturn(Optional.of(award));
        when(awardRepository.findAwardByName(anyString())).thenReturn(Optional.of(award));

        // THEN
        assertThrows(AlreadyExistsException.class, () -> awardService.updateAward(awardDto));
    }

    @Test
    void deleteAward() throws NetflixException {
        // GIVEN
        Award award = Award.builder()
                .name("Best Actor")
                .build();

        // WHEN
        when(awardRepository.findAwardById(anyInt())).thenReturn(Optional.of(award));

        // THEN
        assertEquals(modelMapper.map(award, AwardDto.class), awardService.deleteAward(1));
    }

    @Test
    void deleteAward_NotFound() {
        // WHEN
        when(awardRepository.findAwardById(anyInt())).thenReturn(Optional.empty());

        // THEN
        assertThrows(NotFoundException.class, () -> awardService.deleteAward(1));
    }
}
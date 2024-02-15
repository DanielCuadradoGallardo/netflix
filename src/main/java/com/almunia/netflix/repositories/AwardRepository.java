package com.almunia.netflix.repositories;

import com.almunia.netflix.entities.Award;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AwardRepository extends JpaRepository<Award, Integer> {
    Optional<Award> findAwardByName(String name);
    Optional<Award> findAwardById(int id);
}

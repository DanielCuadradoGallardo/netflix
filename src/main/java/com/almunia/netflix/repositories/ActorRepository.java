package com.almunia.netflix.repositories;

import com.almunia.netflix.entities.Actor;
import com.almunia.netflix.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {
    Optional<Actor> findActorByName(String name);
    Optional<Actor> findActorById(int id);
}

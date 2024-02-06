package com.almunia.netflix.repositories;

import com.almunia.netflix.entities.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface SerieRepository extends JpaRepository<Serie, Integer> {
    Optional<Serie> findSerieByName(String name);

    Optional<Serie> findSerieById(int id);

    Set<Serie> findSerieByCategoriesName(String categoryName);
}

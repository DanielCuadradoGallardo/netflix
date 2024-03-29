package com.almunia.netflix.repositories;

import com.almunia.netflix.entities.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeasonRepository extends JpaRepository<Season, Integer> {
    Optional<Season> findSeasonById(int seasonId);


    Optional<Season> findSeasonBySerieNameAndTitle(String serie_name, String title);

    Optional<Season> findSeasonBySerie_idAndTitle(int id, String title);
}

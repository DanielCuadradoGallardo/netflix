package com.almunia.netflix.repositories;

import com.almunia.netflix.entities.Award;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AwardRepository extends JpaRepository<Award, Integer> {
}

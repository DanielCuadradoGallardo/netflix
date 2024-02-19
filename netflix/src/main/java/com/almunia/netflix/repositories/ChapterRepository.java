package com.almunia.netflix.repositories;

import com.almunia.netflix.entities.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Integer> {
    Optional<Chapter> findChapterById(int id);

    Optional<Chapter> findChapterBySeasonTitleAndTitle(String title, String title1);

    Optional<Chapter> findChapterBySeason_idAndTitle(int id, String title);
}

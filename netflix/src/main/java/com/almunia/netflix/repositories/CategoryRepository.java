package com.almunia.netflix.repositories;

import com.almunia.netflix.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
     Optional<Category> findCategoryByName(String name);
     Optional<Category> findCategoryById(int id);
}

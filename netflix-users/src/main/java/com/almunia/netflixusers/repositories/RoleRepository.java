package com.almunia.netflixusers.repositories;

import com.almunia.netflixusers.entities.ERole;
import com.almunia.netflixusers.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}

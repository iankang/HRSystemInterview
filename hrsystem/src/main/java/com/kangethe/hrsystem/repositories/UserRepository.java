package com.kangethe.hrsystem.repositories;

import com.kangethe.hrsystem.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
    Set<User> findUsersByAssessmentsId(Long assessmentId);
}

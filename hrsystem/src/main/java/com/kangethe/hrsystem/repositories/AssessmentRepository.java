package com.kangethe.hrsystem.repositories;

import com.kangethe.hrsystem.entities.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssessmentRepository extends JpaRepository<Assessment,Long> {
    List<Assessment> findAssessmentsByUsersId(Long userId);
}

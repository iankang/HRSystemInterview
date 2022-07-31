package com.kangethe.hrsystem.repositories;

import com.kangethe.hrsystem.entities.AssessmentScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssessmentScoreRepository extends JpaRepository<AssessmentScore, Long> {
}

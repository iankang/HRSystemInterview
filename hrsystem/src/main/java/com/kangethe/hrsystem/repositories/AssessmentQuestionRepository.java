package com.kangethe.hrsystem.repositories;

import com.kangethe.hrsystem.entities.AssessmentQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssessmentQuestionRepository extends JpaRepository<AssessmentQuestion,Long> {

    List<AssessmentQuestion> findAssessmentQuestionByAssessmentId(Long assessmentId);
}

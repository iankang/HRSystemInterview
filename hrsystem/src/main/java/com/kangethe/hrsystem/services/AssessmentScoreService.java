package com.kangethe.hrsystem.services;

import com.kangethe.hrsystem.entities.AssessmentScore;
import com.kangethe.hrsystem.exception.NotFoundException;
import com.kangethe.hrsystem.repositories.AssessmentScoreRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssessmentScoreService {

    private final AssessmentScoreRepository assessmentScoreRepository;

    public AssessmentScoreService(AssessmentScoreRepository assessmentScoreRepository) {
        this.assessmentScoreRepository = assessmentScoreRepository;
    }

    public ResponseEntity<AssessmentScore> addAssessment(AssessmentScore assessmentScore) {
        return new ResponseEntity<>(assessmentScoreRepository.save(assessmentScore), HttpStatus.OK);
    }

    public ResponseEntity<List<AssessmentScore>> getAllAssessments() {
        return new ResponseEntity<>(assessmentScoreRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<AssessmentScore> updateAssessment(Long assessmentScoreId, AssessmentScore assessmentScore) {

        AssessmentScore actualAssessment = assessmentScoreRepository.findById(assessmentScoreId)
                .orElseThrow(() -> new NotFoundException("Assessment with id: " + assessmentScoreId + " Not Found"));
        actualAssessment.setScores(assessmentScore.getScores());
        actualAssessment.setLowerBound(assessmentScore.getLowerBound());
        actualAssessment.setScoreUpperBound(assessmentScore.getScoreUpperBound());
        return new ResponseEntity<>(assessmentScoreRepository.save(actualAssessment), HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> deleteAssessment(Long assessmentId) {
        if (assessmentScoreRepository.existsById(assessmentId)) {
            throw new NotFoundException("Assessment Score with id: " + assessmentId + " Not Found");
        }

        assessmentScoreRepository.deleteById(assessmentId);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<HttpStatus> deleteAllAssessments(){
        assessmentScoreRepository.deleteAll();
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}

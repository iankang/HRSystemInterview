package com.kangethe.hrsystem.services;

import com.kangethe.hrsystem.entities.Assessment;
import com.kangethe.hrsystem.entities.AssessmentQuestion;
import com.kangethe.hrsystem.entities.User;
import com.kangethe.hrsystem.exception.NotFoundException;
import com.kangethe.hrsystem.repositories.AssessmentRepository;
import com.kangethe.hrsystem.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AssessmentService {

    private final AssessmentRepository assessmentRepository;
    private final UserRepository userRepository;
    private final AssessmentQuestionsService assessmentQuestionsService;

    public AssessmentService(AssessmentRepository assessmentRepository, UserRepository userRepository, AssessmentQuestionsService assessmentQuestionsService) {
        this.assessmentRepository = assessmentRepository;
        this.userRepository = userRepository;
        this.assessmentQuestionsService = assessmentQuestionsService;
    }

    public ResponseEntity<List<Assessment>> getAllAssessments() {
        return ResponseEntity.ok(assessmentRepository.findAll());
    }

    public ResponseEntity<Assessment> createAssessment(Long userId) {
        Assessment actualAssessment = new Assessment();
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User with id: " + userId + "Doesn't exist"));
        actualAssessment.addUser(user);

        Assessment savedAssessment = assessmentRepository.save(actualAssessment);
        Set<AssessmentQuestion> allQuestions = assessmentQuestionsService.addQuestions(savedAssessment.getId());
        savedAssessment.setQuestions(allQuestions);
        assessmentRepository.save(savedAssessment);
        return ResponseEntity.ok(savedAssessment);
    }

    public ResponseEntity<List<Assessment>> getAssessmentsByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException("User with id: " + userId + " Not Found");
        }
        List<Assessment> assessments = assessmentRepository.findAssessmentsByUsersId(userId);
        return ResponseEntity.ok(assessments);
    }

    //    public ResponseEntity<Assessment> updateAssessment(Assessment assessment, Long assessmentId){
//        Assessment updatingAssessment = assessmentRepository.findById(assessmentId).orElseThrow()
//    }
    public ResponseEntity<HttpStatus> deleteAssessment(Long assessmentId) {
        if (!assessmentRepository.existsById(assessmentId)) {
            throw new NotFoundException("Assessment With Id: " + assessmentId + " Not Found");
        }
        assessmentRepository.deleteById(assessmentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

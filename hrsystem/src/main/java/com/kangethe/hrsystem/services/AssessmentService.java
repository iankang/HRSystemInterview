package com.kangethe.hrsystem.services;

import com.kangethe.hrsystem.entities.Assessment;
import com.kangethe.hrsystem.entities.AssessmentQuestion;
import com.kangethe.hrsystem.entities.User;
import com.kangethe.hrsystem.exception.FinishCurrentAssessmentFirst;
import com.kangethe.hrsystem.exception.NotFoundException;
import com.kangethe.hrsystem.repositories.AssessmentRepository;
import com.kangethe.hrsystem.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AssessmentService {

    private final Logger logger= LoggerFactory.getLogger(AssessmentService.class);
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

            Optional<User> user = userRepository.findById(userId);
            if(user.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            actualAssessment.addUser(user.get());

            Assessment savedAssessment = assessmentRepository.save(actualAssessment);
            List<AssessmentQuestion> allQuestions = assessmentQuestionsService.addQuestions(savedAssessment.getId());
            savedAssessment.setQuestions(allQuestions);
            assessmentRepository.save(savedAssessment);
            return ResponseEntity.ok(savedAssessment);

    }

    public ResponseEntity<List<Assessment>> getAssessmentsByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Assessment> assessments = assessmentRepository.findAssessmentsByUsersId(userId);
        return ResponseEntity.ok(assessments);
    }

    public ResponseEntity<List<AssessmentQuestion>> getAssessmentQuestionById(Long assessmentId){
        List<AssessmentQuestion> assessmentQuestion = assessmentQuestionsService.getQuestionsByAssessmentId(assessmentId);
        return ResponseEntity.ok(assessmentQuestion);
    }

    public ResponseEntity<AssessmentQuestion> answerQuestion(Long assessmentId,Long assessmentQuestionId, Long questionId, int timeTaken){
        AssessmentQuestion assessmentQuestion = assessmentQuestionsService.answerQuestion(assessmentQuestionId, questionId, timeTaken);
        assessmentMetrics(assessmentId);
        return ResponseEntity.ok(assessmentQuestion);
    }

    private void assessmentMetrics(Long assessmentId){
        Assessment assessment = assessmentRepository.findById(assessmentId).orElseThrow(() -> new NotFoundException("AssessmentId with id: "+assessmentId+" Not Found"));
        Long answeredCount = assessmentQuestionsService.getAnsweredQuestionsCount(assessmentId);
        Long totalQuestionCount = assessmentQuestionsService.getCountOfQuestions(assessmentId);
        assessment.setQuestionsAnswered(answeredCount.intValue());
        assessment.setTotalNumberOfQuestions(totalQuestionCount.intValue());
        logger.info("answeredQuestions: "+ answeredCount);
        logger.info("totalQuestions: "+ totalQuestionCount);
        assessmentRepository.save(assessment);
    }
    public ResponseEntity<HttpStatus> deleteAssessment(Long assessmentId) {
        if (!assessmentRepository.existsById(assessmentId)) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Assessment assessment = assessmentRepository.findById(assessmentId).get();
        assessmentRepository.delete(assessment);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

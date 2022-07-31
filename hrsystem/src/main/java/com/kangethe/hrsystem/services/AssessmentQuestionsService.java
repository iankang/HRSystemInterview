package com.kangethe.hrsystem.services;

import com.kangethe.hrsystem.entities.Assessment;
import com.kangethe.hrsystem.entities.AssessmentQuestion;
import com.kangethe.hrsystem.entities.TopicQuestions;
import com.kangethe.hrsystem.repositories.AssessmentQuestionRepository;
import com.kangethe.hrsystem.repositories.AssessmentRepository;
import com.kangethe.hrsystem.repositories.TopicsQuestionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AssessmentQuestionsService {

    private AssessmentQuestionRepository assessmentQuestionRepository;
    private AssessmentRepository assessmentRepository;
    private TopicsQuestionRepository topicsQuestionRepository;

    public AssessmentQuestionsService(AssessmentQuestionRepository assessmentQuestionRepository, AssessmentRepository assessmentRepository, TopicsQuestionRepository topicsQuestionRepository) {
        this.assessmentQuestionRepository = assessmentQuestionRepository;
        this.assessmentRepository = assessmentRepository;
        this.topicsQuestionRepository = topicsQuestionRepository;
    }

    public Set<AssessmentQuestion> addQuestions(Long assessmentID){
        Set<AssessmentQuestion> assessmentQuestions = new HashSet<>();
        Assessment assessment = assessmentRepository.findById(assessmentID).orElseThrow(() -> new NoSuchElementException("Assessment with id: "+assessmentID+" Not Found"));
        for(TopicQuestions question : getAllQuestions()){
            AssessmentQuestion assessmentQuestion = new AssessmentQuestion();
            assessmentQuestion.setAssessment(assessment);
            assessmentQuestion.setQuestionAsked(question);

            assessmentQuestions.add(assessmentQuestion);
        }

        return assessmentQuestions;
    }

    public List<TopicQuestions> getAllQuestions(){
        List<TopicQuestions> allquestions = topicsQuestionRepository.findAll();
        Collections.shuffle(allquestions);
        if(allquestions.size()>10) {
            return allquestions.subList(0, 10);
        } else {
            return allquestions;
        }
    }
}

package com.kangethe.hrsystem.services;

import com.kangethe.hrsystem.entities.Answers;
import com.kangethe.hrsystem.entities.Assessment;
import com.kangethe.hrsystem.entities.AssessmentQuestion;
import com.kangethe.hrsystem.entities.TopicQuestions;
import com.kangethe.hrsystem.exception.NotFoundException;
import com.kangethe.hrsystem.repositories.AnswersRepository;
import com.kangethe.hrsystem.repositories.AssessmentQuestionRepository;
import com.kangethe.hrsystem.repositories.AssessmentRepository;
import com.kangethe.hrsystem.repositories.TopicsQuestionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AssessmentQuestionsService {

    Logger logger = LoggerFactory.getLogger(AssessmentQuestionsService.class);
    private AssessmentQuestionRepository assessmentQuestionRepository;
    private AssessmentRepository assessmentRepository;
    private TopicsQuestionRepository topicsQuestionRepository;
    private AnswersRepository answersRepository;

    public AssessmentQuestionsService(AssessmentQuestionRepository assessmentQuestionRepository, AssessmentRepository assessmentRepository, TopicsQuestionRepository topicsQuestionRepository, AnswersRepository answersRepository) {
        this.assessmentQuestionRepository = assessmentQuestionRepository;
        this.assessmentRepository = assessmentRepository;
        this.topicsQuestionRepository = topicsQuestionRepository;
        this.answersRepository = answersRepository;
    }

    public List<AssessmentQuestion> addQuestions(Long assessmentID){
        List<AssessmentQuestion> assessmentQuestions = new ArrayList<>();
        Assessment assessment = assessmentRepository.findById(assessmentID).orElseThrow(() -> new NoSuchElementException("Assessment with id: "+assessmentID+" Not Found"));
        for(TopicQuestions question : getAllQuestions()){
            AssessmentQuestion assessmentQuestion = new AssessmentQuestion();

            assessmentQuestion.setAssessment(assessment);
            assessmentQuestion.setQuestionAsked(question);
            assessmentQuestions.add(assessmentQuestion);
        }

        return assessmentQuestions;
    }

    public List<AssessmentQuestion> getQuestionsByAssessmentId(Long assessmentId){
        return  assessmentQuestionRepository.findAssessmentQuestionsByAssessmentId(assessmentId);
    }

    public AssessmentQuestion getAssessmentQuestionById(Long assessmentId){
        return assessmentQuestionRepository.findById(assessmentId).orElseThrow(()-> new NotFoundException("Assessment Question with id: "+ assessmentId+ " Not found."));
    }

    public AssessmentQuestion answerQuestion(Long assessmentQuestionId, Long answerGivenId,int timeTaken){


        AssessmentQuestion storedAssessmentQuestion = getAssessmentQuestionById(assessmentQuestionId);
        Answers answerGiven = answersRepository.findById(answerGivenId)
                .orElseThrow(() -> new NotFoundException("Answer with id: "+ answerGivenId+" Not Found"));

        storedAssessmentQuestion.setAnswerGiven(answerGiven);
        storedAssessmentQuestion.setTimeTaken(timeTaken);
        return assessmentQuestionRepository.save(storedAssessmentQuestion);
    }

    public Long getAnsweredQuestionsCount(Long assessmentId){
        return assessmentQuestionRepository.countByAnswerGivenNotNullAndAssessmentId(assessmentId);
    }

    public Long getCountOfQuestions(Long assessmentId){
        return assessmentQuestionRepository.countAssessmentQuestionsByAssessmentId(assessmentId);
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

package com.kangethe.hrsystem.services;

import com.kangethe.hrsystem.entities.Answers;
import com.kangethe.hrsystem.exception.NotFoundException;
import com.kangethe.hrsystem.repositories.AnswersRepository;
import com.kangethe.hrsystem.repositories.TopicsQuestionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {

    private final AnswersRepository answersRepository;
    private final TopicsQuestionRepository topicsQuestionRepository;

    public AnswerService(AnswersRepository answersRepository, TopicsQuestionRepository topicsQuestionRepository) {
        this.answersRepository = answersRepository;
        this.topicsQuestionRepository = topicsQuestionRepository;
    }

    public ResponseEntity<Answers> createAnswer(
            Long questionId,
            Answers answerBody
    ) {
        Answers answer = topicsQuestionRepository.findById(questionId).map(topicQuestions -> {
                    answerBody.setQuestion(topicQuestions);
                    return answersRepository.save(answerBody);
                })
                .orElseThrow(() -> new NotFoundException("Question with id: " + questionId + " Not Foud"));
        return ResponseEntity.ok(answer);
    }

    public ResponseEntity<List<Answers>> getAllAnswers(Long questionId) {
        if (!topicsQuestionRepository.existsById(questionId)) {
            throw new NotFoundException("Question with id: " + questionId + " Not Found");
        }
        List<Answers> answersList = answersRepository.findByQuestionId(questionId);
        return ResponseEntity.ok(answersList);
    }

    public ResponseEntity<Answers> getAnswerById(Long answerId) {
        Answers answers = answersRepository.findById(answerId).orElseThrow(() -> new NotFoundException("Answer with id: " + answerId + " Not Found"));
        return ResponseEntity.ok(answers);
    }

    public ResponseEntity<Answers> updateAnswer(Long answerId, Answers answers) {
        Answers answerLocal = answersRepository.findById(answerId).orElseThrow(() -> new NotFoundException("Answer with id: " + answerId + " Not Found"));
        answerLocal.setAnswer(answers.getAnswer());
        return ResponseEntity.ok(answersRepository.save(answerLocal));
    }

    public ResponseEntity<HttpStatus> deleteAnswer(Long answerId){
        if(!answersRepository.existsById(answerId)){
            throw new NotFoundException("Answer with id: "+ answerId+" Not Found.");
        }
        answersRepository.deleteById(answerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

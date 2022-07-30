package com.kangethe.hrsystem.services;

import com.kangethe.hrsystem.entities.TopicQuestions;
import com.kangethe.hrsystem.entities.Topics;
import com.kangethe.hrsystem.exception.NotFoundException;
import com.kangethe.hrsystem.repositories.TopicsQuestionRepository;
import com.kangethe.hrsystem.repositories.TopicsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicQuestionsService {

    private final TopicsQuestionRepository topicsQuestionRepository;
    private final TopicsRepository topicsRepository;

    public TopicQuestionsService(TopicsQuestionRepository topicsQuestionRepository, TopicsRepository topicsRepository) {
        this.topicsQuestionRepository = topicsQuestionRepository;
        this.topicsRepository = topicsRepository;
    }

    public ResponseEntity<TopicQuestions> createTopicQuestion(Long topicId, TopicQuestions topicQuestionBody) {

        TopicQuestions topicQuestion = topicsRepository.findById(topicId).map(topics -> {

            topicQuestionBody.setTopic(topics);
            return topicsQuestionRepository.save(topicQuestionBody);
        }).orElseThrow(() -> new NotFoundException("topic with id " + topicId + " not found"));

        return ResponseEntity.ok(topicQuestion);
    }

    public ResponseEntity<List<TopicQuestions>> getAllQuestionsByTopicId(Long topicId) {
        if (!topicsRepository.existsById(topicId)) {
            throw new NotFoundException("Not Found topic with id: " + topicId);
        }
        List<TopicQuestions> questions = topicsQuestionRepository.findByTopicId(topicId);
        return ResponseEntity.ok(questions);
    }

    public ResponseEntity<TopicQuestions> getTopicQuestionByQuestionId(Long questionId) {
        TopicQuestions topicQuestions = topicsQuestionRepository.findById(questionId).orElseThrow(() -> new NotFoundException("topic with id: " + questionId + " not found"));
        return ResponseEntity.ok(topicQuestions);
    }

    public ResponseEntity<TopicQuestions> updateQuestion(Long questionId, TopicQuestions updatedQuestion) {
        TopicQuestions topicQuestions = topicsQuestionRepository.findById(questionId).orElseThrow(() -> new NotFoundException("question with id: " + questionId + "Not Found"));

        topicQuestions.setTopicQuestion(updatedQuestion.getTopicQuestion());

        return new ResponseEntity<>(topicQuestions, HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> deleteQuestion(Long questionId) {
        if (!topicsQuestionRepository.existsById(questionId)) {
            throw new NotFoundException("Question with id: " + questionId + "Not Found ");
        }
        topicsQuestionRepository.deleteById(questionId);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }

}

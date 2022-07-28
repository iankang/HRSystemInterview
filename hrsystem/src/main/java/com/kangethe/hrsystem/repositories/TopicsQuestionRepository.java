package com.kangethe.hrsystem.repositories;

import com.kangethe.hrsystem.entities.TopicQuestions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TopicsQuestionRepository extends JpaRepository<TopicQuestions,Long> {
    Optional<List<TopicQuestions>> findByTopicQuestionId(Long topicId);
    Optional<TopicQuestions> findByIdAndTopicId(Long id, Long topicId);
}

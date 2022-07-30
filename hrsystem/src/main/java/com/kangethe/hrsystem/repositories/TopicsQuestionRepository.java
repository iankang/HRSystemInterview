package com.kangethe.hrsystem.repositories;

import com.kangethe.hrsystem.entities.TopicQuestions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TopicsQuestionRepository extends JpaRepository<TopicQuestions,Long> {
}

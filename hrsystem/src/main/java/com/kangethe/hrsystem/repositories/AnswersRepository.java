package com.kangethe.hrsystem.repositories;

import com.kangethe.hrsystem.entities.Answers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswersRepository extends JpaRepository<Answers,Long> {

    List<Answers> findByQuestionId(Long questionId);
}

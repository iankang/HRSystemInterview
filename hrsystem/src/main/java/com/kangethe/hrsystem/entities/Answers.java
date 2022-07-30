package com.kangethe.hrsystem.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "policy_answers")
@Getter
@Setter
@ToString
public class Answers {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String answer;
    private Boolean isCorrect;

    @ManyToOne
    @JoinColumn(name = "question_id",nullable = false)
    private TopicQuestions question;
}

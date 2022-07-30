package com.kangethe.hrsystem.entities;

import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "policy_answers")
@Getter
@Setter
@NoArgsConstructor
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

    public Answers(String answer, Boolean isCorrect) {
        this.answer = answer;
        this.isCorrect = isCorrect;
    }
}

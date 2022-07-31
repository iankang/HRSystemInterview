package com.kangethe.hrsystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

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

    @OneToOne(mappedBy = "answerGiven")
    @JsonIgnore
    private AssessmentQuestion assessmentQuestion;

    public Answers(String answer, Boolean isCorrect) {
        this.answer = answer;
        this.isCorrect = isCorrect;
    }
}

package com.kangethe.hrsystem.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "assessment_question")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class AssessmentQuestion extends AuditModel{

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assessment_id", nullable = false)
    @JsonIgnore
    private Assessment assessment;


    @OneToOne(cascade = CascadeType.DETACH)
    @JoinTable(name = "assessment_question_topic_question",
            joinColumns =
                    { @JoinColumn(name = "assessment_question", referencedColumnName = "id") },
            inverseJoinColumns =
                    { @JoinColumn(name = "questions_asked_id", referencedColumnName = "id") })
    private TopicQuestions questionAsked;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinTable(name = "assessment_question_answer_given",
            joinColumns =
                    { @JoinColumn(name = "assessment_question_id", referencedColumnName = "id") },
            inverseJoinColumns =
                    { @JoinColumn(name = "answer_given_id", referencedColumnName = "id") })
    private Answers answerGiven;

    private int timeTaken;

}

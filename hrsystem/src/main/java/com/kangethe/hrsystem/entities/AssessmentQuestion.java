package com.kangethe.hrsystem.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "assessment_question")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class AssessmentQuestion extends AuditModel {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assessment_id", nullable = false)
    @JsonIgnore
    private Assessment assessment;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "question_asked_id", nullable = false)
    private TopicQuestions questionAsked;

    @OneToOne(mappedBy = "assessmentQuestion")
    private Answers answerGiven;

    private int timeTaken;

}

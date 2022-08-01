package com.kangethe.hrsystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "topic_questions")
@Getter
@Setter
@ToString
public class TopicQuestions {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String topicQuestion;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Topics topic;

    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Answers> answers;

    @OneToOne(mappedBy = "questionAsked")
    @JsonIgnore
    private AssessmentQuestion assessmentQuestion;

    public TopicQuestions() {
    }

    public TopicQuestions(String topicQuestion) {
        this.topicQuestion = topicQuestion;
    }

    public TopicQuestions(String topicQuestion, Topics topic) {
        this.topicQuestion = topicQuestion;
        this.topic = topic;

    }

}

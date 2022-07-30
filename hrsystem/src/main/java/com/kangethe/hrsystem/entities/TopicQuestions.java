package com.kangethe.hrsystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

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
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private Topics topic;


    public TopicQuestions() {
    }

    public TopicQuestions(String topicQuestion, Topics topic) {
        this.topicQuestion = topicQuestion;
        this.topic = topic;

    }

}

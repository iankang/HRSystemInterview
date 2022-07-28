package com.kangethe.hrsystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "topic_questions")
public class TopicQuestions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String topicQuestion;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "question_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private Topics topic;
    private Boolean isCorrect;

    public TopicQuestions() {
    }

    public TopicQuestions(String topicQuestion, Topics topic, Boolean isCorrect) {
        this.topicQuestion = topicQuestion;
        this.topic = topic;
        this.isCorrect = isCorrect;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopicQuestion() {
        return topicQuestion;
    }

    public void setTopicQuestion(String topicQuestion) {
        this.topicQuestion = topicQuestion;
    }

    public Topics getTopic() {
        return topic;
    }

    public void setTopic(Topics topic) {
        this.topic = topic;
    }

    public Boolean getCorrect() {
        return isCorrect;
    }

    public void setCorrect(Boolean correct) {
        isCorrect = correct;
    }

    @Override
    public String toString() {
        return "TopicQuestions{" +
                "id=" + id +
                ", topicQuestion='" + topicQuestion + '\'' +
                ", topic=" + topic +
                ", isCorrect=" + isCorrect +
                '}';
    }
}

package com.kangethe.hrsystem.entities;

import javax.persistence.*;

@Entity
@Table(name = "topics")
public class Topics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String topicName;

    public Topics() {
    }

    public Topics(String topicName) {

        this.topicName = topicName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    @Override
    public String toString() {
        return "Topics{" +
                "id=" + id +
                ", topicName='" + topicName + '\'' +
                '}';
    }
}

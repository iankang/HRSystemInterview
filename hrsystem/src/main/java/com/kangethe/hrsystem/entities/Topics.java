package com.kangethe.hrsystem.entities;

import javax.persistence.*;

@Entity
@Table(name = "topics")
public class Topics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long topic_id;
    private String topicName;

    public Topics() {
    }

    public Topics(String topicName) {

        this.topicName = topicName;
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
                "id=" + topic_id +
                ", topicName='" + topicName + '\'' +
                '}';
    }
}

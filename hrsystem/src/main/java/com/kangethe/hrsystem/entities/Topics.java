package com.kangethe.hrsystem.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "topics")
@Getter
@Setter
public class Topics {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long topicId;
    private String topicName;

    public Topics() {
    }

    public Topics(String topicName) {

        this.topicName = topicName;
    }

}

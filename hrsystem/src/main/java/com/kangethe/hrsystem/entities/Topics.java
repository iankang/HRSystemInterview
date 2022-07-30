package com.kangethe.hrsystem.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "topics")
@Getter
@Setter
public class Topics {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String topicName;

    public Topics() {
    }

    public Topics(String topicName) {

        this.topicName = topicName;
    }

}

package com.kangethe.hrsystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "assessment_tbl")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Assessment extends AuditModel {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "assessment_users", joinColumns = {@JoinColumn(name = "assesment_id")}, inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private Set<User> users = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "assessment")
    @JsonIgnore
    private List<AssessmentQuestion> questions = new ArrayList<>();

    private Boolean isCompleted = false;
    private int questionsAnswered = 0;
    private int totalNumberOfQuestions = 0;

    public void addUser(User user) {
        this.users.add(user);
        user.getAssessments().add(this);
    }

    public void removeUser(Long userId) {
        User user = this.users.stream().filter(t -> t.getId() == userId).findFirst().orElse(null);
        if (user != null) {
            this.users.remove(user);
            user.getAssessments().remove(this);
        }
    }

}

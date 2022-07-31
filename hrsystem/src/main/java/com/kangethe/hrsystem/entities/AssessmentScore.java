package com.kangethe.hrsystem.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "assessment_score")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class AssessmentScore {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, unique = true)
    private Scores scores;

    private Long lowerBound;
    private Long scoreUpperBound;

    public AssessmentScore(Scores scores,Long lowerBound, Long scoreUpperBound) {
        this.scores = scores;
        this.scoreUpperBound = scoreUpperBound;
        this.lowerBound = lowerBound;
    }
}

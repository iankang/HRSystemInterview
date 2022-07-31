package com.kangethe.hrsystem.entities;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "assessment_question")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class AssessmentQuestion extends AuditModel{

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

}

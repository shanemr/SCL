package com.vaadin.tutorial.crm.backend.entity;

import javax.persistence.*;

@Entity
public class QuestionAnswers extends AbstractEntity implements Cloneable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "questions_id")
    private Questions question;
}

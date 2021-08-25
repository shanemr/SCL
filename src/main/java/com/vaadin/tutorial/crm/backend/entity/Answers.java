package com.vaadin.tutorial.crm.backend.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Answers extends AbstractEntity implements Cloneable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "questions_id")
    private Questions question;

    @NotNull
    private String answer = "";

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @OneToMany(mappedBy = "answers", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private List<UserAnswers> userAnswers;


    public Questions getQuestion() {
        return question;
    }

    public void setQuestion(Questions question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<UserAnswers> getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(List<UserAnswers> userAnswers) {
        this.userAnswers = userAnswers;
    }
}

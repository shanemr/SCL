package com.vaadin.tutorial.crm.backend.entity;

import javax.persistence.*;

@Entity
public class UserAnswers extends AbstractEntity implements Cloneable {
    // Mapping answers to patient

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answers_id", nullable = true)
    private Answers answers;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "patient_id", nullable = true)
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "questions_id", nullable = true)
    private Questions question;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "questionnaire_id", nullable = true)
    private Questionnaire questionnaire;

    public UserAnswers(){

    }

    public Answers getAnswers() {
        return answers;
    }

    public void setAnswers(Answers answers) {
        this.answers = answers;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Questions getQuestion() {
        return question;
    }

    public void setQuestion(Questions question) {
        this.question = question;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }


}

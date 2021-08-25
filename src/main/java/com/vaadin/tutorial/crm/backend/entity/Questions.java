package com.vaadin.tutorial.crm.backend.entity;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Questions extends AbstractEntity implements Cloneable{


    private String question = "";


    // Maps questions to questionnaire.
    @ManyToOne
    @JoinColumn(name = "questionnaire_id")
    private Questionnaire questionnaire;

    // Maps answers to question.
    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    private List<UserAnswers> userAnswers;

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }

    public List<UserAnswers> getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(List<UserAnswers> userAnswers) {
        this.userAnswers = userAnswers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
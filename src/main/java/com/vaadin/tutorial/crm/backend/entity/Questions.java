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

    // Map possible question answers to question.
    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER)
    private List<Answers> answers = new LinkedList<>();

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }

    public List<Answers> getQuestionnaireAnswers() {
        return answers;
    }

    public void setQuestionnaireAnswers(List<Answers> answers) {
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}

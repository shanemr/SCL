package com.vaadin.tutorial.crm.backend.entity;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Questions extends AbstractEntity implements Cloneable{

    // Maps questions to questionnaire.
    @ManyToOne
    @JoinColumn(name = "questionnaire_id")
    private Questionnaire questionnaire;

    // Map possible question answers to question.
    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER)
    private List<QuestionAnswers> questionAnswers = new LinkedList<>();

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }

    public List<QuestionAnswers> getQuestionnaireAnswers() {
        return questionAnswers;
    }

    public void setQuestionnaireAnswers(List<QuestionAnswers> questionAnswers) {
        this.questionAnswers = questionAnswers;
    }
}

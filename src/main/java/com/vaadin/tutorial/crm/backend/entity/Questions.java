package com.vaadin.tutorial.crm.backend.entity;

import javax.persistence.Entity;

@Entity
public class Questions extends AbstractEntity implements Cloneable{


    private String question = "";



    // Maps answers to question.
    /*@OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    private List<UserAnswers> userAnswers;*/


    /*public List<UserAnswers> getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(List<UserAnswers> userAnswers) {
        this.userAnswers = userAnswers;
    }*/

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
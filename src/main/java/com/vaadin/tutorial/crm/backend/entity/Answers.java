package com.vaadin.tutorial.crm.backend.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Answers extends AbstractEntity implements Cloneable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @NotNull
    private String answer = "";

    @NotNull
    private int answerVal;


    /*@OneToMany(mappedBy = "answers", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private List<UserAnswers> userAnswers;*/



    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /*public List<UserAnswers> getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(List<UserAnswers> userAnswers) {
        this.userAnswers = userAnswers;
    }*/

    public void setAnswerVal(int answerVal){
        this.answerVal = answerVal;
    }
   public int getAnswerVal(){
        return answerVal;
   }


}

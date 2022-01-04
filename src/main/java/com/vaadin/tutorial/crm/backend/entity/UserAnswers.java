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

    private int answerOne;
    private int answerTwo;
    private int answerThree;
    private int answerFour;
    private int answerFive;
    private int answerSix;
    private int answerSeven;
    private int answerEight;
    private int answerNine;
    private int answerTen;
    private int answerEleven;
    private int answerTwelve;
    private int answerThirteen;
    private int answerFourteen;
    private int answerFifteen;
    private int answerSixteen;
    private int answerSeventeen;
    private int answerEighteen;
    private int answerNineteen;
    private int answerTwenty;
    private int answerTwentyOne;
    private int answerTwentyTwo;
    private int answerTwentyThree;
    private int answerTwentyFour;
    private int answerTwentyFive;
    private int answerTwentySix;
    private int answerTwentySeven;
    private int answerTwentyEight;
    private int answerTwentyNine;
    private int answerThirty;
    private int answerThirtyOne;


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

    // Getters and setters for answers
    public int getAnswerOne() {
        return answerOne;
    }

    public void setAnswerOne(int answerOne) {
        this.answerOne = answerOne;
    }

    public int getAnswerTwo() {
        return answerTwo;
    }

    public void setAnswerTwo(int answerTwo) {
        this.answerTwo = answerTwo;
    }

    public int getAnswerThree() {
        return answerThree;
    }

    public void setAnswerThree(int answerThree) {
        this.answerThree = answerThree;
    }

    public int getAnswerFour() {
        return answerFour;
    }

    public void setAnswerFour(int answerFour) {
        this.answerFour = answerFour;
    }

    public int getAnswerFive() {
        return answerFive;
    }

    public void setAnswerFive(int answerFive) {
        this.answerFive = answerFive;
    }

    public int getAnswerSix() {
        return answerSix;
    }

    public void setAnswerSix(int answerSix) {
        this.answerSix = answerSix;
    }

    public int getAnswerSeven() {
        return answerSeven;
    }

    public void setAnswerSeven(int answerSeven) {
        this.answerSeven = answerSeven;
    }

    public int getAnswerEight() {
        return answerEight;
    }

    public void setAnswerEight(int answerEight) {
        this.answerEight = answerEight;
    }

    public int getAnswerNine() {
        return answerNine;
    }

    public void setAnswerNine(int answerNine) {
        this.answerNine = answerNine;
    }

    public int getAnswerTen() {
        return answerTen;
    }

    public void setAnswerTen(int answerTen) {
        this.answerTen = answerTen;
    }

    public int getAnswerEleven() {
        return answerEleven;
    }

    public void setAnswerEleven(int answerEleven) {
        this.answerEleven = answerEleven;
    }

    public int getAnswerTwelve() {
        return answerTwelve;
    }

    public void setAnswerTwelve(int answerTwelve) {
        this.answerTwelve = answerTwelve;
    }

    public int getAnswerThirteen() {
        return answerThirteen;
    }

    public void setAnswerThirteen(int answerThirteen) {
        this.answerThirteen = answerThirteen;
    }

    public int getAnswerFourteen() {
        return answerFourteen;
    }

    public void setAnswerFourteen(int answerFourteen) {
        this.answerFourteen = answerFourteen;
    }

    public int getAnswerFifteen() {
        return answerFifteen;
    }

    public void setAnswerFifteen(int answerFifteen) {
        this.answerFifteen = answerFifteen;
    }

    public int getAnswerSixteen() {
        return answerSixteen;
    }

    public void setAnswerSixteen(int answerSixteen) {
        this.answerSixteen = answerSixteen;
    }

    public int getAnswerSeventeen() {
        return answerSeventeen;
    }

    public void setAnswerSeventeen(int answerSeventeen) {
        this.answerSeventeen = answerSeventeen;
    }

    public int getAnswerEighteen() {
        return answerEighteen;
    }

    public void setAnswerEighteen(int answerEighteen) {
        this.answerEighteen = answerEighteen;
    }

    public int getAnswerNineteen() {
        return answerNineteen;
    }

    public void setAnswerNineteen(int answerNineteen) {
        this.answerNineteen = answerNineteen;
    }

    public int getAnswerTwenty() {
        return answerTwenty;
    }

    public void setAnswerTwenty(int answerTwenty) {
        this.answerTwenty = answerTwenty;
    }

    public int getAnswerTwentyOne() {
        return answerTwentyOne;
    }

    public void setAnswerTwentyOne(int answerTwentyOne) {
        this.answerTwentyOne = answerTwentyOne;
    }

    public int getAnswerTwentyTwo() {
        return answerTwentyTwo;
    }

    public void setAnswerTwentyTwo(int answerTwentyTwo) {
        this.answerTwentyTwo = answerTwentyTwo;
    }

    public int getAnswerTwentyThree() {
        return answerTwentyThree;
    }

    public void setAnswerTwentyThree(int answerTwentyThree) {
        this.answerTwentyThree = answerTwentyThree;
    }

    public int getAnswerTwentyFour() {
        return answerTwentyFour;
    }

    public void setAnswerTwentyFour(int answerTwentyFour) {
        this.answerTwentyFour = answerTwentyFour;
    }

    public int getAnswerTwentyFive() {
        return answerTwentyFive;
    }

    public void setAnswerTwentyFive(int answerTwentyFive) {
        this.answerTwentyFive = answerTwentyFive;
    }

    public int getAnswerTwentySix() {
        return answerTwentySix;
    }

    public void setAnswerTwentySix(int answerTwentySix) {
        this.answerTwentySix = answerTwentySix;
    }

    public int getAnswerTwentySeven() {
        return answerTwentySeven;
    }

    public void setAnswerTwentySeven(int answerTwentySeven) {
        this.answerTwentySeven = answerTwentySeven;
    }

    public int getAnswerTwentyEight() {
        return answerTwentyEight;
    }

    public void setAnswerTwentyEight(int answerTwentyEight) {
        this.answerTwentyEight = answerTwentyEight;
    }

    public int getAnswerTwentyNine() {
        return answerTwentyNine;
    }

    public void setAnswerTwentyNine(int answerTwentyNine) {
        this.answerTwentyNine = answerTwentyNine;
    }

    public int getAnswerThirty() {
        return answerThirty;
    }

    public void setAnswerThirty(int answerThirty) {
        this.answerThirty = answerThirty;
    }

    public int getAnswerThirtyOne() {
        return answerThirtyOne;
    }

    public void setAnswerThirtyOne(int answerThirtyOne) {
        this.answerThirtyOne = answerThirtyOne;
    }
}

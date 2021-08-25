package com.vaadin.tutorial.crm.backend.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Patient extends AbstractEntity implements Cloneable {


    private String patient = "";

    @NotNull
    @NotEmpty
    private String firstName = "";

    @NotNull
    @NotEmpty
    private String lastName = "";

    @NotNull
    private String password = "";

    @NotNull
    private String userName = "";

    // Mapping userAnswers to Patient.
    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<UserAnswers> userAnswers = new LinkedList<>();

    // Map possible question answers to question.

    // Mapping Patient to Questionaire.
    @ManyToOne
    @JoinColumn(name = "questionnaire_id")
    private Questionnaire questionnaire;

    @NotNull
    @NotEmpty
    // Patient Role
    private String roles = "ROLE_USER";

    // Keep track if user is valid
    private boolean enabled = true;

    @Email
    @NotNull
    @NotEmpty
    private String email = "";

    public Patient() {
    }

    public Patient(String firstName, String lastName, String userName, String email, String password, String roles, Boolean enabled ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.roles = roles;
        this.enabled = enabled;

    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

   public void setPassword(String password){
        this.password = password;
   }

   public String getPassword(){
        return password;
   }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFullName(){
        return firstName + " " + lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    public List<UserAnswers> getAnswers() {
        return userAnswers;
    }

    public void setAnswers(List<UserAnswers> answers) {
        this.userAnswers = answers;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }



    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

}
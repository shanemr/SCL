package com.vaadin.tutorial.crm.backend.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Patient extends AbstractEntity implements Cloneable {


    @NotNull
    @NotEmpty
    private String firstName = "";

    @NotNull
    @NotEmpty
    private String lastName = "";

    @NotNull

    private String password = "";


    // Mapping userAnswers to Patient.
    @ManyToOne
    @JoinColumn(name = "userAnswers_id")
    private UserAnswers userAnswers;

    // Mapping Patient to Questionnaire.
    @OneToOne
    private Questionnaire questionnaire;


    @Email
    @NotNull
    @NotEmpty
    private String email = "";

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




    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

}
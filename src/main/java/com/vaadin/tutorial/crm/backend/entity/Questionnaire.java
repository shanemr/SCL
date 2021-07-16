package com.vaadin.tutorial.crm.backend.entity;


import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Questionnaire extends AbstractEntity implements Cloneable{
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long id;

        private String date ="07/15/2021";


        // Mapping questionnaire to questions
        @OneToMany(mappedBy = "questionnaire", fetch = FetchType.EAGER)
        private List<Questions> questions = new LinkedList<>();

        public Questionnaire(){

        }

        public List<Questions> getQuestions() {
                return questions;
        }

        public void setQuestions(List<Questions> questions) {
                this.questions = questions;
        }


        public String getDate() {
                return date;
        }

        public void setDate(String date) {
                this.date = date;
        }
}

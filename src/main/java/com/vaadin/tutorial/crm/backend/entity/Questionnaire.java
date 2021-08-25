package com.vaadin.tutorial.crm.backend.entity;


import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Questionnaire extends AbstractEntity implements Cloneable{
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long id;

        private Date date;

        @OneToOne(mappedBy = "questionnaire")
        private UserAnswers userAnswers;

        public Questionnaire(){

        }


        public Date getDate() {
                return date;
        }

        public void setDate(Date date) {
                this.date = date;
        }
}
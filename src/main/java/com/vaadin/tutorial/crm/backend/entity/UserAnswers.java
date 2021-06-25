package com.vaadin.tutorial.crm.backend.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class UserAnswers extends AbstractEntity implements Cloneable {
    // Mapping answers to patient
    @ManyToOne
    private Patient patient;
}

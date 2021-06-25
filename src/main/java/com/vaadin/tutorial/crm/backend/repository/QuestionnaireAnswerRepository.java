package com.vaadin.tutorial.crm.backend.repository;

import com.vaadin.tutorial.crm.backend.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionnaireAnswerRepository  extends JpaRepository<Patient, Long> {
}

package com.vaadin.tutorial.crm.backend.service;

import com.vaadin.tutorial.crm.backend.entity.Questionnaire;
import com.vaadin.tutorial.crm.backend.repository.AnswerRepository;
import com.vaadin.tutorial.crm.backend.repository.QuestionnaireRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class QuestionnaireService {
    private static final Logger LOGGER = Logger.getLogger(ContactService.class.getName());
    private final QuestionnaireRepository questionnaireRepository;


    public QuestionnaireService(QuestionnaireRepository questionnaireRepository) {
        this.questionnaireRepository = questionnaireRepository;
    }


    public List<Questionnaire> findAll() {
        return questionnaireRepository.findAll();
    }

    public List<Questionnaire> findAll(String stringFilter) {


        if (stringFilter == null || stringFilter.isEmpty()) {
            return questionnaireRepository.findAll();
        } else {
            return null;
        }
    }

    public long count() {
        return questionnaireRepository.count();
    }


    public void save(Questionnaire questionnaire) {
        if (questionnaire == null) {
            LOGGER.log(Level.SEVERE,
                    "Questionnaire is null. Are you sure you have connected your form to the application?");
            return;
        }
        questionnaireRepository.save(questionnaire);
    }
}

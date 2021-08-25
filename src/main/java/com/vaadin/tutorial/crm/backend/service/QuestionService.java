package com.vaadin.tutorial.crm.backend.service;

import com.vaadin.tutorial.crm.backend.entity.Questions;
import com.vaadin.tutorial.crm.backend.repository.QuestionsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class QuestionService {
    private static final Logger LOGGER = Logger.getLogger(ContactService.class.getName());
    private QuestionsRepository questionsRepository;
    private List<String> questionsList = new ArrayList<>();

    public QuestionService(QuestionsRepository questionsRepository) {
        this.questionsRepository = questionsRepository;
    }

    public List<Questions> findAll() {
        return questionsRepository.findAll();
    }

    public List<String> getQuestions(QuestionService questionService){
        // Clearing list of previous answers
        questionsList.clear();
        for( int i = 0; i < questionService.count(); i++) {
            questionsList.add(questionService.findAll().get(i).getQuestion());
        }
        return questionsList;
    }

    public List<Questions> findAll(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return questionsRepository.findAll();
        } else {
            return questionsRepository.search(stringFilter);
        }
    }

    public long count() {
        return questionsRepository.count();
    }

    public void delete(Questions question) {
        questionsRepository.delete(question);
    }

    public void save(Questions question) {
        if (question == null) {
            LOGGER.log(Level.SEVERE,
                    "Contact is null. Are you sure you have connected your form to the application?");
            return;
        }
        questionsRepository.save(question);
    }


}

package com.vaadin.tutorial.crm.backend.service;

import com.vaadin.tutorial.crm.backend.entity.Questions;
import com.vaadin.tutorial.crm.backend.repository.QuestionsRepository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class QuestionService {
    private static final Logger LOGGER = Logger.getLogger(ContactService.class.getName());
    private QuestionsRepository questionsRepository;

    public QuestionService(QuestionsRepository questionsRepository) {
        this.questionsRepository = questionsRepository;
    }

    public List<Questions> findAll() {
        return questionsRepository.findAll();
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

    @PostConstruct
    public void populateTestData() {

        if (questionsRepository.count() == 0) {
            Random r = new Random(0);
            questionsRepository.saveAll(
                    Stream.of("Gabrielle Patel", "Brian Robinson", "Eduardo Haugen",
                            "Koen Johansen", "Alejandro Macdonald", "Angel Karlsson", "Yahir Gustavsson", "Haiden Svensson",
                            "Emily Stewart", "Corinne Davis", "Ryann Davis", "Yurem Jackson", "Kelly Gustavsson",
                            "Eileen Walker", "Katelyn Martin", "Israel Carlsson", "Quinn Hansson", "Makena Smith",
                            "Danielle Watson", "Leland Harris", "Gunner Karlsen", "Jamar Olsson", "Lara Martin",
                            "Ann Andersson", "Remington Andersson", "Rene Carlsson", "Elvis Olsen", "Solomon Olsen",
                            "Jaydan Jackson", "Bernard Nilsen")
                            .map(name -> {
                                String[] split = name.split(" ");
                                Questions question = new Questions();
                                question.setQuestion(split[0]);
                                return question;
                            }).collect(Collectors.toList()));
        }
    }
}

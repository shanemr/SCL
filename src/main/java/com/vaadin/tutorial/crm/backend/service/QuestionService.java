package com.vaadin.tutorial.crm.backend.service;

import com.vaadin.tutorial.crm.backend.entity.Questions;
import com.vaadin.tutorial.crm.backend.repository.QuestionsRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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

    @PostConstruct
    public void populateTestData() {

        if (questionsRepository.count() == 0) {
            Random r = new Random(0);
            questionsRepository.saveAll(
                    Stream.of("I have cravings for alcohol or drugs", "I am easily distracted", "People usually have to repeat words to me",
                            "I have blurred vision", "I have chronic pain", "I need to repeat acts and thoughts", "I have problems concentrating",
                            "I have difficulty feeling things I touch", "I feel sad or blue", "I do not initiate actions", "I feel anxious",
                            "I have problems sitting still", "I have problems recognizing emotional expressions", "I have low motivation",
                            "I have migraine headaches", "I have problems multi-tasking", "I have recurrent thoughts about myself",
                            "I often get stuck thinking about one thing", "I have problems with social skills", "I have problems recognizing things I touch",
                            "I have low self esteem", "I have problems planning and sequencing", "I have short-term memory problems",
                            "I have pain in many parts of my body", "I have problems finding the right word", "I feel fearful for no reason",
                            "I drink to much alcohol", "I smoke too much(tobacco or other substance)", "I feel tense or on edge",
                            "I have ringing in my ears")
                            .map(name -> {
                                String[] split = name.split(" ");
                                Questions question = new Questions();
                                question.setQuestion(split[8]);
                                return question;
                            }).collect(Collectors.toList()));
        }
    }
}

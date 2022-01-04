package com.vaadin.tutorial.crm.backend.service;

import com.vaadin.tutorial.crm.backend.entity.Answers;
import com.vaadin.tutorial.crm.backend.repository.AnswerRepository;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service

public class AnswerService  {
    private static final Logger LOGGER = Logger.getLogger(AnswerService.class.getName());
    private AnswerRepository answerRepository;
    private List<String> list = new ArrayList<>();

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public List<Answers> findAll() {
        return answerRepository.findAll();
    }

    public int getAnswerVal(String answer){
        return answerRepository.getAnswerVal(answer);
    }


    public List<String> getAnswers(AnswerService answerService){
        // Clearing list of previous answers
        list.clear();
        for( int i = 0; i < answerService.count(); i++) {
            list.add(answerService.findAll().get(i).getAnswer());
        }
        return list;
    }


    public List<Answers> findAll(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return answerRepository.findAll();
        } else {
            return answerRepository.search(stringFilter);
        }
    }

    public long count() {
        return answerRepository.count();
    }

    public void save(Answers answer) {
        if (answer == null) {
            LOGGER.log(Level.SEVERE,
                    "Contact is null. Are you sure you have connected your form to the application?");
            return;
        }
        answerRepository.save(answer);
    }


    @PostConstruct
    public void populateTestData() {

        if (answerRepository.count() == 0) {
            answerRepository.saveAll(
                    Stream.of("Never","Rarely", "Sometimes", "Often",
                            "Always")
                            .map(answer -> {
                                String[] split = answer.split(" ");
                                Answers answers = new Answers();
                                answers.setAnswer(split[0]);
                                return answers;
                            }).collect(Collectors.toList()));
        }
    }
}

package com.vaadin.tutorial.crm.backend.service;

import com.vaadin.tutorial.crm.backend.entity.Answers;
import com.vaadin.tutorial.crm.backend.entity.UserAnswers;
import com.vaadin.tutorial.crm.backend.repository.AnswerRepository;
import com.vaadin.tutorial.crm.backend.repository.UserAnswersRepository;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service

public class UserAnswerService  {
    private static final Logger LOGGER = Logger.getLogger(AnswerService.class.getName());
    private UserAnswersRepository userAnswersRepository;
    private List<String> list = new ArrayList<>();
    private List<Answers> realList = new ArrayList<>();

    public UserAnswerService(UserAnswersRepository userAnswersRepository) {
        this.userAnswersRepository = userAnswersRepository;
    }

    public List<UserAnswers> findAll() {
        return userAnswersRepository.findAll();
    }


    public List<String> getAnswers(AnswerService answerService){
        // Clearing list of previous answers
        list.clear();
        for( int i = 0; i < answerService.count(); i++) {
            list.add(answerService.findAll().get(i).getAnswer());
        }
        return list;
    }

//    public List<Answers> getRealAnswers(AnswerService answerService){
//        // Clearing list of previous answers
//        realList.clear();
//        for( int i = 0; i < answerService.count(); i++) {
//            realList.add(answerService.findAll().get(i).getAnswer());
//        }
//        return realList;
//    }

    public List<UserAnswers> findAll(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return userAnswersRepository.findAll();
        } else {
            return null;
        }
    }

    public long count() {
        return userAnswersRepository.count();
    }

    public void save(UserAnswers userAnswer) {
        if (userAnswer == null) {
            LOGGER.log(Level.SEVERE,
                    "Contact is null. Are you sure you have connected your form to the application?");
            return;
        }
        userAnswersRepository.save(userAnswer);
    }



}

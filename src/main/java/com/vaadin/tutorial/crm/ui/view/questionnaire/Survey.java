package com.vaadin.tutorial.crm.ui.view.questionnaire;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.tutorial.crm.backend.entity.Questions;
import com.vaadin.tutorial.crm.backend.service.AnswerService;
import com.vaadin.tutorial.crm.backend.service.QuestionService;
import com.vaadin.tutorial.crm.ui.view.user.UserSurveyView;

import java.util.ArrayList;
import java.util.List;


@Route(value = "scl", layout = UserSurveyView.class)

public class Survey extends VerticalLayout {
    private AnswerService answerService;
    private QuestionService questionService;
    private TextField filterText = new TextField();
    // Layout of survey
    private FormLayout surveyLayout = new FormLayout();
    // Holds list of questions
    private ListBox<String> ansrList = new ListBox<>();
    private List<String> list = new ArrayList<>();
    // Holds list of questions
    private List<String> questionList = new ArrayList<>();
    // Grid to hold questions
    Grid<String> grid = new Grid<>(String.class);

    // Button to go to next question
    private Button nextBtn = new Button("Next");

    // Question holder
    H2 question = new H2();

    public Survey(AnswerService answerService, QuestionService questionService){

        this.answerService = answerService;
        this.questionService = questionService;

        getAnswers();
        getQuestions();
        surveyLayout.addClassName("survey_layout");

        nextBtn.addClassName("survey_btn_layout");
        question.setText(questionList.get(8));

        surveyLayout.add(question,ansrList, nextBtn);
        ansrList.clear();
        add(surveyLayout);
    }


    private void getAnswers(){
        ansrList.setItems(answerService.getAnswers(answerService));
    }

    private void getQuestions(){
        questionList.addAll(questionService.getQuestions(questionService));
    }

}

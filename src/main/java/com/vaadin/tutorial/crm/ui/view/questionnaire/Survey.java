package com.vaadin.tutorial.crm.ui.view.questionnaire;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.tutorial.crm.backend.repository.AnswerRepository;
import com.vaadin.tutorial.crm.backend.service.AnswerService;
import com.vaadin.tutorial.crm.ui.UserSurveyView;
import java.util.ArrayList;
import java.util.List;


@Route(value = "scl", layout = UserSurveyView.class)

public class Survey extends VerticalLayout {
    private AnswerService answerService;
    private TextField filterText = new TextField();
    private FormLayout surveyLayout = new FormLayout();
    private ListBox<String> ansrList = new ListBox<>();
    private List<String> list = new ArrayList<>();
    private Button nextBtn = new Button("Next");


    public Survey(AnswerService answerService){

        this.answerService = answerService;

        getAnswers();

        surveyLayout.addClassName("survey_layout");

        nextBtn.addClassName("survey_btn_layout");

        surveyLayout.add(ansrList, nextBtn);
        ansrList.clear();
        add(surveyLayout);
    }


    private void getAnswers(){
        ansrList.setItems(answerService.getAnswers(answerService));
    }

}

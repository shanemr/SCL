package com.vaadin.tutorial.crm.ui.view.questionnaire;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.tutorial.crm.backend.entity.Patient;
import com.vaadin.tutorial.crm.backend.entity.Questionnaire;
import com.vaadin.tutorial.crm.backend.entity.UserAnswers;
import com.vaadin.tutorial.crm.backend.service.*;
import com.vaadin.tutorial.crm.security.PatientDetails;
import com.vaadin.tutorial.crm.ui.view.user.UserSurveyView;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


@Route(value = "scl", layout = UserSurveyView.class)

public class Survey extends VerticalLayout {
    private final AnswerService answerService;
    private QuestionService questionService;
    private QuestionnaireService questionnaireService;
    private ContactService contactService;
    private UserAnswerService userAnswerService;
    private TextField filterText = new TextField();
    // Layout of survey
    private FormLayout surveyLayout = new FormLayout();
    // Holds list of answers to display
    private ListBox<String> ansrList = new ListBox<>();
    // Holds user selected answers
    private List<String> answers = new LinkedList<>();

    // Holds list of questions
    private List<String> questionList = new ArrayList<>();
    // Grid to hold questions
    Grid<String> grid = new Grid<>(String.class);
    // Button to go to next question
    private Button nextBtn = new Button("Next");
    // Button to save survey
    private Button saveBtn = new Button("Save");
    // int to hold question number
    private int count = 0;
    // New Date for survey
    private Date date = new Date();
    // Holds patient information
    private Patient user;
    // Creating new survey
    Questionnaire survey = new Questionnaire();

    // List to hold user answers
    List<UserAnswers> userAnswer = new LinkedList<>();

    UserAnswers an = new UserAnswers();

    // Question holder
    H2 question = new H2();


    String toGet = "";

    public Survey(AnswerService answerService, UserAnswerService userAnswerService, QuestionService questionService, QuestionnaireService questionnaireService, ContactService contactService){
        this.answerService = answerService;
        this.questionService = questionService;
        this.questionnaireService= questionnaireService;
        this.contactService = contactService;
        this.userAnswerService = userAnswerService;

        // Set the date of the survey
        survey.setDate(date);


        // Getting patient currently logged in
         PatientDetails patient = getPatient();
         // Setting the patient to currently logged in patient
         user = patient.getPatient();

        // Getting questionnaire answers
        getAnswers();
        // Getting question for questionnaire
        getQuestions();
        surveyLayout.addClassName("survey_layout");

        // Adding class name to next button
        nextBtn.addClassName("survey_btn_layout");
        // Calling function to get next question
        nextBtn.addClickListener(click -> nextQuestion());
        // Calling function to save questionnaire
        saveBtn.addClickListener(click -> saveQuestionnaire());




        an.setAnswers(answerService.findAll().get(0));
        an.setPatient(user);
        an.setQuestion(questionService.findAll().get(0));
        userAnswerService.save(an);


        question.setText(questionList.get(count));
        surveyLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1)
        );
        surveyLayout.setColspan(question, 4);
        surveyLayout.add(question,ansrList, nextBtn);

        ansrList.clear();
        add(surveyLayout);
    }


    private void getAnswers(){ ansrList.setItems(answerService.getAnswers(answerService));
    }

    private void getQuestions(){
        questionList.addAll(questionService.getQuestions(questionService));
    }

    // Moves on to the next question
    private void nextQuestion(){
        // Display finish button
        if(count == questionList.size() - 2){
            System.out.println("inside if");
            nextBtn.removeClassName("survey_btn_layout");
            nextBtn.addClassName("btn_hide");
            surveyLayout.add(saveBtn);
        }
        // Set next question value
        if(count != questionList.size() - 1){
            question.setText(questionList.get(++count));
            //Add selected answer to list
            addAnswer();
        }

    }

    // Adds selected answer to list of answers
    private void addAnswer(){

        ansrList.addValueChangeListener(event -> {
            toGet = event.getValue();
            System.out.println(toGet);
        });

        answers.add(toGet);
        System.out.println(answers);
    }
    private Patient getPatientInfo(String patient){
        return contactService.findPatient(patient);
    }



    private void saveQuestionnaire(){
        questionnaireService.save(survey);
        an.setQuestionnaire(survey);
    }

    private PatientDetails getPatient(){
        Object patient =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (PatientDetails) patient;
    }



}

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
    // New UseAnswer entry
    UserAnswers an = new UserAnswers();

    // Question holder
    H2 question = new H2();

    // Used to hold the selected answer
    String selectedAnswer = "";

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
        // Prime listener for first selection
        if(answers.isEmpty()){
            System.out.println("inside empty");
            addAnswer();
        }
        // Calling function to get next question
        nextBtn.addClickListener(click -> nextQuestion());
        // Calling function to save questionnaire
        saveBtn.addClickListener(click -> saveQuestionnaire());



        // Getting answers to select from
        //an.setAnswers(answerService.findAll().get(0));
        //an.setQuestionnaire(survey);
        // Setting patient
        an.setPatient(user);
        an.setQuestion(questionService.findAll().get(0));
        userAnswerService.save(an);

        // Setting current question
        question.setText(questionList.get(count));

        surveyLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1)
        );
        surveyLayout.setColspan(question, 4);
        // Setting layout to have question, answers, and navigation button
        surveyLayout.add(question,ansrList, nextBtn);
        // Adding layout
        add(surveyLayout);

    }


    private void getAnswers(){ ansrList.setItems(answerService.getAnswers(answerService));
    }

    private void getQuestions(){
        questionList.addAll(questionService.getQuestions(questionService));
    }

    // Moves to the next question
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
            ansrList.clear();
        }

    }

    // Adds selected answer to list of answers
    private void addAnswer(){
        ansrList.addValueChangeListener(event -> {
            selectedAnswer = event.getValue();
            System.out.println("Value of selected answer is " + answerService.getAnswerVal(selectedAnswer));
            System.out.println("Value changed to " + selectedAnswer);
        });

        answers.add(selectedAnswer);
        System.out.println(answers);
    }
    private Patient getPatientInfo(String patient){
        return contactService.findPatient(patient);
    }



    private void saveQuestionnaire(){
        System.out.println(survey.getDate());
        questionnaireService.save(survey);

    }

    private PatientDetails getPatient(){
        Object patient =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (PatientDetails) patient;
    }


    private void addAnswers(LinkedList<UserAnswers> l){
        an.setAnswerOne(5);
        an.setAnswerTwo(5);
        an.setAnswerThree(5);
        an.setAnswerFour(5);
        an.setAnswerFive(5);
        an.setAnswerSix(5);
        an.setAnswerSeven(5);
        an.setAnswerEight(5);
        an.setAnswerNine(5);
        an.setAnswerTen(5);
        an.setAnswerEleven(5);
        an.setAnswerTwelve(5);
        an.setAnswerThirteen(5);
        an.setAnswerFourteen(5);
        an.setAnswerFifteen(5);
        an.setAnswerSixteen(5);
        an.setAnswerSeventeen(5);
        an.setAnswerEighteen(5);
        an.setAnswerNineteen(5);
        an.setAnswerTwenty(5);
        an.setAnswerTwentyOne(5);
        an.setAnswerTwentyTwo(5);
        an.setAnswerTwentyThree(5);
        an.setAnswerTwentyFour(5);
        an.setAnswerTwentyFive(5);
        an.setAnswerTwentySix(5);
        an.setAnswerTwentySeven(5);
        an.setAnswerTwentyEight(5);
        an.setAnswerTwentyNine(5);
        an.setAnswerThirty(5);
        an.setAnswerThirtyOne(5);
    }


}

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

    // pop up
    private Pop popup = new Pop();
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
    UserAnswers userAn = new UserAnswers();

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
        // Adding class name to save button
        saveBtn.addClassName("save_button");
        // Prime listener for first selection
        if(answers.isEmpty()){
            addAnswer();
            // Remove empty primer from front of list
            answers.remove(0);
        }
        // Calling function to get next question
        nextBtn.addClickListener(click -> nextQuestion());
        // Calling function to save questionnaire
        saveBtn.addClickListener(click -> saveQuestionnaire());

        // Setting patient
        userAn.setPatient(user);

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
            nextBtn.removeClassName("survey_btn_layout");
            nextBtn.addClassName("btn_hide");
            surveyLayout.add(saveBtn);
        }
        // Set next question value
        if(count != questionList.size() - 1){

            if(addAnswer()){
                popup.setB(false);
                question.setText(questionList.get(++count));
             } else{
                popup.setB(true);
                popup.getPopupContent();
            }
            //Add selected answer to list
            //addAnswer();
            ansrList.clear();
        }

    }

    // Adds selected answer to list of answers
    private boolean addAnswer(){
        ansrList.addValueChangeListener(event -> {
            selectedAnswer = event.getValue();
            //System.out.println("Value of selected answer is " + answerService.getAnswerVal(selectedAnswer));
            System.out.println("Value changed to " + selectedAnswer);
        });
        if(answers.isEmpty()){
            System.out.println("list is empty");
            answers.add(0, selectedAnswer);
        } else{
            if(selectedAnswer != null){
                System.out.println("Selected answer is " + selectedAnswer);
                answers.add(selectedAnswer);
                System.out.println(answers);
                return true;
            }
            else{
                return false;
            }
        }
        return false;
    }



    private void saveQuestionnaire(){
        System.out.println(survey.getDate());
        addAnswer();
        saveAnswers(answers);
        questionnaireService.save(survey);
        userAnswerService.save(userAn);

    }

    private PatientDetails getPatient(){
        Object patient =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (PatientDetails) patient;
    }

    // Sets all of user selected answers
    private void saveAnswers(List<String> l){
        userAn.setAnswerOne(answerService.getAnswerVal(l.get(0)));
        userAn.setAnswerTwo(answerService.getAnswerVal(l.get(1)));
        userAn.setAnswerThree(answerService.getAnswerVal(l.get(2)));
        userAn.setAnswerFour(answerService.getAnswerVal(l.get(3)));
        userAn.setAnswerFive(answerService.getAnswerVal(l.get(4)));
        userAn.setAnswerSix(answerService.getAnswerVal(l.get(5)));
        userAn.setAnswerSeven(answerService.getAnswerVal(l.get(6)));
        userAn.setAnswerEight(answerService.getAnswerVal(l.get(7)));
        userAn.setAnswerNine(answerService.getAnswerVal(l.get(8)));
        userAn.setAnswerTen(answerService.getAnswerVal(l.get(9)));
        userAn.setAnswerEleven(answerService.getAnswerVal(l.get(10)));
        userAn.setAnswerTwelve(answerService.getAnswerVal(l.get(11)));
        userAn.setAnswerThirteen(answerService.getAnswerVal(l.get(12)));
        userAn.setAnswerFourteen(answerService.getAnswerVal(l.get(13)));
        userAn.setAnswerFifteen(answerService.getAnswerVal(l.get(14)));
        userAn.setAnswerSixteen(answerService.getAnswerVal(l.get(15)));
        userAn.setAnswerSeventeen(answerService.getAnswerVal(l.get(16)));
        userAn.setAnswerEighteen(answerService.getAnswerVal(l.get(17)));
        userAn.setAnswerNineteen(answerService.getAnswerVal(l.get(18)));
        userAn.setAnswerTwenty(answerService.getAnswerVal(l.get(19)));
        userAn.setAnswerTwentyOne(answerService.getAnswerVal(l.get(20)));
        userAn.setAnswerTwentyTwo(answerService.getAnswerVal(l.get(21)));
        userAn.setAnswerTwentyThree(answerService.getAnswerVal(l.get(22)));
        userAn.setAnswerTwentyFour(answerService.getAnswerVal(l.get(23)));
        userAn.setAnswerTwentyFive(answerService.getAnswerVal(l.get(24)));
        userAn.setAnswerTwentySix(answerService.getAnswerVal(l.get(25)));
        userAn.setAnswerTwentySeven(answerService.getAnswerVal(l.get(26)));
        userAn.setAnswerTwentyEight(answerService.getAnswerVal(l.get(27)));
        userAn.setAnswerTwentyNine(answerService.getAnswerVal(l.get(28)));
        userAn.setAnswerThirty(answerService.getAnswerVal(l.get(29)));
        userAn.setAnswerThirtyOne(answerService.getAnswerVal(l.get(30)));
    }


}

package com.vaadin.tutorial.crm.ui.view.questionnaire;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.tutorial.crm.backend.entity.*;
import com.vaadin.tutorial.crm.backend.repository.QuestionsRepository;
import com.vaadin.tutorial.crm.backend.service.*;
import com.vaadin.tutorial.crm.security.PatientDetails;
import com.vaadin.tutorial.crm.ui.view.list.ContactForm;
import com.vaadin.tutorial.crm.ui.view.user.UserSurveyView;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.*;


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
    // Holds list of answers
    private ListBox<Answers> ansrList = new ListBox<>();
    // Displays list of answers
    private ListBox<String> ansrToShow = new ListBox<>();
    private List<String> list = new ArrayList<>();
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
    // Hods patient information
    private Patient user;
    // Creating new survey
    Questionnaire survey = new Questionnaire();
    Answers answers = new Answers();

    List<UserAnswers> userAnswer = new LinkedList<>();

    UserAnswers an = new UserAnswers();

    // Question holder
    H2 question = new H2();

    public Survey(AnswerService answerService, UserAnswerService userAnswerService, QuestionService questionService, QuestionnaireService questionnaireService, ContactService contactService){
        this.answerService = answerService;
        this.questionService = questionService;
        this.questionnaireService= questionnaireService;
        this.contactService = contactService;
        this.userAnswerService = userAnswerService;

        // Set the date of the survey
        survey.setDate(date);
        questionnaireService.save(survey);

       // answerService.save(answers);




         PatientDetails patient = getPatient();
         user = patient.getPatient();
         System.out.println("PATIENT NAME " + getPatientInfo(user.getFirstName()));
         answers.setPatient(getPatientInfo(user.getFirstName()));

        getAnswers();
        //setAnswers();
        getQuestions();
        surveyLayout.addClassName("survey_layout");

        nextBtn.addClassName("survey_btn_layout");
        nextBtn.addClickListener(click -> nextQuestion());

        ansrList.addValueChangeListener(event -> {
            Notification.show("Listbox selection was changed to " + event.getValue());
        });

        /*userAnswer.add(new UserAnswers());
        userAnswer.get(0).setAnswers(answerService.findAll().get(0));
        userAnswer.get(0).setQuestionnaire(survey);
        userAnswer.get(0).setPatient(user);
        userAnswer.get(0).setQuestion(questionService.findAll().get(0));
        userAnswerService.save(userAnswer.get(0));*/


        an.setAnswers(answerService.findAll().get(0));
        an.setQuestionnaire(survey);
        an.setPatient(user);
        an.setQuestion(questionService.findAll().get(0));
        userAnswerService.save(an);


        question.setText(questionList.get(count));

        surveyLayout.add(question,ansrList, nextBtn, saveBtn);
        ansrList.clear();
        add(surveyLayout);
    }


    private void getAnswers(){ ansrList.setItems(answerService.findAll());
    }

    private void getQuestions(){
        questionList.addAll(questionService.getQuestions(questionService));
    }

    private void nextQuestion(){
        question.setText(questionList.get(count++));
    }

    private Patient getPatientInfo(String patient){
        return contactService.findPatient(patient);
    }





    /*private Answers getAnswer(){
        Answers selectedAnswer = new Answers();
      selectedAnswer = ansrList.addValueChangeListener(event -> {
            event.getValue();
        });
        return selectedAnswer;
    }*/

    private void saveQuestionnaire(){

    }

    private PatientDetails getPatient(){
        Object patient =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (PatientDetails) patient;
    }



}

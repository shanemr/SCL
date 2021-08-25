package com.vaadin.tutorial.crm.ui.view.user;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.model.Navigator;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.tutorial.crm.security.PatientDetails;
import com.vaadin.tutorial.crm.ui.view.chart.SclBarChart;
import com.vaadin.tutorial.crm.ui.view.chart.SclPlotChart;
import com.vaadin.tutorial.crm.ui.view.list.SurveyResultsView;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;



@Route(value="home", layout = UserMainView.class)
@Secured("ROLE_USER")
public class UserHomeView extends HorizontalLayout {

        H1 userName = new H1();
        Button takeSurvey = new Button("Take Questionaire");
        // Creating bar chart object
        SclBarChart chart = new SclBarChart();
        // Creating plot chart
        SclPlotChart pChart = new SclPlotChart();
        // Creating result grid object
        SurveyResultsView results;



    public UserHomeView(){



        // Classname for user home view
        addClassName("user-home-view");

        results = new SurveyResultsView();

        // Class names

        takeSurvey.addClassName("surveyBtn");

        Button button = new Button("Click Me!");

        // Handle the events with an anonymous class

        takeSurvey.addClickListener(click -> sclView());


        //prevResults.addClassName("prevBtn");
        //prevResults.addClickListener(click -> addContact());
        results.addClassName("test-results");

        // Setting view size of window
        setSizeFull();
        // Div to hold chart and past survey results
        Div home = new Div(configVert(), results);
        // Classname for div home
        home.addClassName("home-div");
        // Setting home div to size of window
        home.setSizeFull();
        // adding home div to vertical layout

        add(home);
        //results.closeSurveys();
    }


    // Configure the vertical layout of user home page
    public VerticalLayout configVert(){
        getPatient();
        VerticalLayout layout = new VerticalLayout();
        layout.addClassName("home-layout");
        layout.add(userName, takeSurvey, chart, pChart);
        return layout;
    }

    void addContact() {
        results.getSurGrid().asSingleSelect().clear();
        results.showSurveys();
    }



    private void getPatient(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userName = new H1("Hello, " + ((PatientDetails)principal).getFirstName() + " " + ((PatientDetails)principal).getLastName());
        userName.addClassName("userName");
    }

    public void sclView(){
        takeSurvey.addClickListener(e ->
                takeSurvey.getUI().ifPresent(ui ->
                        ui.navigate("scl"))
        );
    }



}

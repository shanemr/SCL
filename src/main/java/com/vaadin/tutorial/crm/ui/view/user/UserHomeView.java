package com.vaadin.tutorial.crm.ui.view.user;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.tutorial.crm.ui.view.chart.SclBarChart;
import com.vaadin.tutorial.crm.ui.view.chart.SclPlotChart;
import com.vaadin.tutorial.crm.ui.view.list.SurveyResultsView;

@Route(value="home", layout = UserMainView.class)
public class UserHomeView extends HorizontalLayout {

        H1 userName = new H1("Hello, User");
        Button takeSurvey = new Button("Take Survey");
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
        userName.addClassName("userName");
        takeSurvey.addClassName("surveyBtn");
        //prevResults.addClassName("prevBtn");
        //prevResults.addClickListener(click -> addContact());
        results.addClassName("test-results");



        // Setting view size of window
        setSizeFull();
        // Div to hold chart and past survey results
        Div home = new Div(configVert(), results);
        // Classnmae for div home
        home.addClassName("home-div");
        // Setting home div to size of window
        home.setSizeFull();
        // adding home div to vertical layout

        add(home);
        //results.closeSurveys();
    }


    // Configure the vertical layout of user home page
    public VerticalLayout configVert(){
        VerticalLayout layout = new VerticalLayout();
        layout.addClassName("home-layout");
        layout.add(userName, takeSurvey, chart, pChart);
        return layout;
    }

    void addContact() {
        results.getSurGrid().asSingleSelect().clear();
        results.showSurveys();
    }

}

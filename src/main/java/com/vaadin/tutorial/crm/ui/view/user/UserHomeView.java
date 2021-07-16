package com.vaadin.tutorial.crm.ui.view.user;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.tutorial.crm.ui.view.chart.SclChart;
import com.vaadin.tutorial.crm.ui.view.list.SurveyResultsView;

@Route(value="home", layout = UserMainView.class)
public class UserHomeView extends VerticalLayout {
        H1 userName = new H1("Hello, User");
        SplitLayout innerLayout = new SplitLayout();
        SplitLayout layout = new SplitLayout();
        Button takeSurvey = new Button("Take Survey");
        Button prevResults = new Button("Previous Results");
        SclChart chart = new SclChart();
        SurveyResultsView results = new SurveyResultsView();

    public UserHomeView(){
        // Classname for user home view
        addClassName("user-home-view");
        userName.addClassName("userName");
        takeSurvey.addClassName("surveyBtn");
        prevResults.addClassName("prevBtn");

        prevResults.addClickListener(click -> addContact());

        Div home = new Div(configVert(), results);
        addClassName("home-div");
        home.setSizeFull();
        add(home);
        results.closeSurveys();
    }


    // Configure the vertical layout of user home page
    public VerticalLayout configVert(){
        VerticalLayout layout = new VerticalLayout();
        layout.addClassName("home-layout");
        layout.add(userName, takeSurvey, chart, prevResults);
        return layout;
    }

    void addContact() {
        results.getSurGrid().asSingleSelect().clear();
        results.showSurveys();
    }

}

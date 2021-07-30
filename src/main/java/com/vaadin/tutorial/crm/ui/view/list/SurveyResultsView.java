package com.vaadin.tutorial.crm.ui.view.list;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.tutorial.crm.backend.entity.Questionnaire;


public class SurveyResultsView extends VerticalLayout {

    Button viewBtn = new Button("View Survey");
    //Button close = new Button("Close");
    private Grid<Questionnaire> surGrid = new Grid(Questionnaire.class);

    public SurveyResultsView(){
        // Class name for survey results view
        addClassName("survey-results");
        configGrid();
        add(createButtonsLayout(), surGrid);
    }

    public void configGrid(){
        surGrid.addClassName("results-grid");
        surGrid.setSizeFull();
        surGrid.setColumns("id", "date");
        surGrid.getColumns().forEach(col -> col.setAutoWidth(true));

    }

    public Grid getSurGrid() {
        return surGrid;
    }

    public void setSurGrid(Grid surGrid) {
        this.surGrid = surGrid;
    }

    public void showSurveys() {
            this.setVisible(true);
            addClassName("editing");
        }

//    public void closeSurveys() {
//        this.setVisible(false);
//        removeClassName("editing");
//    }

    private HorizontalLayout createButtonsLayout() {
        viewBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        //close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        //close.addClickShortcut(Key.ESCAPE);
        //close.addClickListener(event -> fireEvent(new CloseEvent(this)));

//        close.addClickListener(event -> {
//            this.setVisible(false);
//        });

        return new HorizontalLayout(viewBtn);
    }



}

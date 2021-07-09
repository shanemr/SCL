package com.vaadin.tutorial.crm.ui.view.list;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.tutorial.crm.backend.entity.Answers;
import com.vaadin.tutorial.crm.backend.entity.Patient;
import com.vaadin.tutorial.crm.backend.service.AnswerService;
import com.vaadin.tutorial.crm.backend.service.ContactService;
import com.vaadin.tutorial.crm.ui.UserSurveyView;

@Route(value = "test", layout = UserSurveyView.class)
public class TestView extends VerticalLayout {
    private AnswerService answerService;
    private Grid<Answers> grid = new Grid<>(Answers.class);
    private TextField filterText = new TextField();
    private ContactForm form;


    public TestView(AnswerService answerService) {
        this.answerService = answerService;
        addClassName("list-view");
        setSizeFull();
        configureGrid();

        form = new ContactForm();
        form.addListener(ContactForm.CloseEvent.class, e -> closeEditor());


        Div content = new Div(grid, form);
        content.addClassName("content");
        content.setSizeFull();

        add(content);
        updateList();
        closeEditor();
    }

    private void configureGrid() {
        grid.addClassName("survey_grid");


        grid.setColumns("answer");
        grid.setMaxWidth("25%");
        grid.setHeightByRows(true);
        //grid.se
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.asSingleSelect().addValueChangeListener(event ->
                editContact(event.getValue()));

    }



    public void editContact(Answers answers) {
        if (answers == null) {
            closeEditor();
        } else {
            grid.select(answers);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        form.setContact(null);
        form.setVisible(false);
        removeClassName("editing");
    }



    /*void addContact() {
        grid.asSingleSelect().clear();
        editContact(new Patient());
    }*/

    private void updateList() {
        grid.setItems(answerService.findAll(filterText.getValue()));
    }
}

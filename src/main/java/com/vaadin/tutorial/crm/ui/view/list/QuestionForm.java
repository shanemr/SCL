package com.vaadin.tutorial.crm.ui.view.list;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.tutorial.crm.backend.entity.Questions;
import com.vaadin.tutorial.crm.ui.view.adim.MainLayout;

@Route(value = "sclForm", layout = MainLayout.class)
@PageTitle("SCL | QEEG")
public class QuestionForm extends VerticalLayout {
        private FormLayout layout = new FormLayout();
        private ListBox<String> answers = new ListBox<>();
        private Button nextBtn = new Button("Next");
        private Grid<Questions> sclGrid = new Grid<>(Questions.class);

        public QuestionForm(){
                setSizeFull();
                configGrid();

                Div content = new Div(sclGrid);
                layout.add(answerList());
                layout.addClassName("scl_layout");
                add(layout, nextBtn);

        }


        private void configGrid(){
                sclGrid.addClassName("scl-grid");
                sclGrid.setSizeFull();

                sclGrid.setColumns("question");


        }


        private ListBox<String> answerList(){
        answers.setItems("Never", "Rarely", "Sometimes", "Often", "Always");
        return answers;
    }

}

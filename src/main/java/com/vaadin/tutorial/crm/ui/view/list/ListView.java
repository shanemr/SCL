package com.vaadin.tutorial.crm.ui.view.list;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.tutorial.crm.backend.entity.Patient;
import com.vaadin.tutorial.crm.backend.service.ContactService;
import com.vaadin.tutorial.crm.ui.view.adim.MainLayout;

@Route(value = "", layout = MainLayout.class)
@PageTitle("Patients | QEEG JMA")
public class ListView extends VerticalLayout {

    private ContactService contactService;
    private Grid<Patient> grid = new Grid<>(Patient.class);
    private TextField filterText = new TextField();
    private ContactForm form;
    private PatientData patientData;


    public ListView(ContactService contactService) {
        this.contactService = contactService;
        addClassName("list-view");
        setSizeFull();
        configureGrid();

        form = new ContactForm();
        form.addListener(ContactForm.SaveEvent.class, this::saveContact);
        form.addListener(ContactForm.DeleteEvent.class, this::deleteContact);
        form.addListener(ContactForm.CloseEvent.class, e -> closeEditor());

        patientData = new PatientData();
        patientData.addListener(PatientData.CloseEvent.class, e -> closePatientSurvey());


        Div content = new Div(grid, form, patientData);
        content.addClassName("content");
        content.setSizeFull();

        add(getToolbar(), content);
        updateList();
        closeEditor();
        closePatientSurvey();
    }

    private void configureGrid() {
        grid.addClassName("contact-grid");
        grid.setSizeFull();
        grid.setColumns("firstName", "lastName", "email");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event ->
                patientSurvey(event.getValue()));
    }

    private HorizontalLayout getToolbar(){
        // Modifying text field
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addContactButton = new Button("Add patient");
        addContactButton.addClickListener(click -> addContact());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addContactButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    public void patientSurvey(Patient patient) {
        if (patient == null) {
            closeEditor();
        } else {
            patientData.setPatient(patient);
            patientData.setVisible(true);
            addClassName("editing");
        }
    }

    public void closePatientSurvey(){
        patientData.setPatient(null);
        patientData.setVisible(false);
        removeClassName("editing");
    }



    public void editContact(Patient patient) {
        if (patient == null) {
            closeEditor();
        } else {
            form.setContact(patient);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        form.setContact(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void saveContact(ContactForm.SaveEvent event) {
        contactService.save(event.getContact());
        updateList();
        closeEditor();
    }

    private void deleteContact(ContactForm.DeleteEvent event) {
        contactService.delete(event.getContact());
        updateList();
        closeEditor();
    }

    void addContact() {
        grid.asSingleSelect().clear();
        editContact(new Patient());
    }



    private void updateList() {
        grid.setItems(contactService.findAll(filterText.getValue()));
    }

}
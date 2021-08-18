package com.vaadin.tutorial.crm.ui.view.list;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;
import com.vaadin.tutorial.crm.backend.entity.Patient;
import com.vaadin.tutorial.crm.ui.view.chart.SclBarChart;
import com.vaadin.tutorial.crm.ui.view.chart.SclPlotChart;

public class PatientData extends VerticalLayout {
    // Patient information
    private Patient patient;
    H1 name = new H1("");
    // Binds patient data to survey
    Binder<Patient> binder = new BeanValidationBinder<>(Patient.class);
    // Bar chart of most recent survey
    private SclBarChart barChart = new SclBarChart();
    // Plot chart of all surveys
    private SclPlotChart plotChart = new SclPlotChart();
    // Button to close Chart
    private Button closeChart = new Button("Close");

    // Constructor
    public PatientData(){
        add(name,configVert());
    }

    public static abstract class patientInfoEvent extends ComponentEvent<PatientData> {
        private Patient patient;

        protected patientInfoEvent(PatientData source, Patient patient) {
            super(source, false);
            this.patient = patient;
        }

        public Patient getPatient() {
            return patient;
        }
    }



    // Configure the vertical layout of user home page
    public VerticalLayout configVert(){
        VerticalLayout layout = new VerticalLayout();
        layout.addClassName("patient-data-layout");

        closeChart.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        closeChart.addClickListener(event -> fireEvent(new CloseEvent(this)));


        layout.add(closeChart,barChart, plotChart);
        return layout;
    }

    public static class CloseEvent extends patientInfoEvent {
        CloseEvent(PatientData source) {
            super(source, null);
        }
    }

    public void setPatient(Patient patient){
        this.patient = patient;
    }

    public Patient getPatient() {
        return patient;
    }

    public H1 getName() {
        return name;
    }

    public void setName(H1 name) {
        this.name = name;
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}




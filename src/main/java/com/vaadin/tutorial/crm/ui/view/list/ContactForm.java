package com.vaadin.tutorial.crm.ui.view.list;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;
import com.vaadin.tutorial.crm.backend.entity.Patient;

public class ContactForm extends FormLayout {

  private Patient patient;

  TextField firstName = new TextField("First name");
  TextField lastName = new TextField("Last name");
  EmailField email = new EmailField("Email");
  PasswordField password = new PasswordField("Password");
  Binder<Patient> binder = new BeanValidationBinder<>(Patient.class);



  Button save = new Button("Save");
  Button delete = new Button("Delete");
  Button close = new Button("Cancel");

  public ContactForm() {
    addClassName("contact-form");

    // Matches fields in contact with contact form.
    binder.bindInstanceFields(this);

    addClassName("contact-form");
    add(firstName,
        lastName,
        email,
        password,
        createButtonsLayout()); 
  }

  private HorizontalLayout createButtonsLayout() {
    save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
    close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

    save.addClickShortcut(Key.ENTER);
    close.addClickShortcut(Key.ESCAPE);

    save.addClickListener(event -> validateAndSave());
    delete.addClickListener(event -> fireEvent(new DeleteEvent(this, patient)));
    close.addClickListener(event -> fireEvent(new CloseEvent(this)));


    binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));

    return new HorizontalLayout(save, delete, close); 
  }



  private void validateAndSave() {
    try {
      binder.writeBean(patient);
      fireEvent(new SaveEvent(this, patient));
    } catch (ValidationException e) {
      e.printStackTrace();
    }
  }

  public void setContact(Patient patient){
    this.patient = patient;
    binder.readBean(patient);
  }
  public static abstract class ContactFormEvent extends ComponentEvent<ContactForm> {
    private Patient patient;

    protected ContactFormEvent(ContactForm source, Patient patient) {
      super(source, false);
      this.patient = patient;
    }

    public Patient getContact() {
      return patient;
    }
  }

  public static class SaveEvent extends ContactFormEvent {
    SaveEvent(ContactForm source, Patient patient) {
      super(source, patient);
    }
  }

  public static class DeleteEvent extends ContactFormEvent {
    DeleteEvent(ContactForm source, Patient patient) {
      super(source, patient);
    }

  }

  public static class CloseEvent extends ContactFormEvent {
    CloseEvent(ContactForm source) {
      super(source, null);
    }
  }

  public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                ComponentEventListener<T> listener) {
    return getEventBus().addListener(eventType, listener);
  }

}
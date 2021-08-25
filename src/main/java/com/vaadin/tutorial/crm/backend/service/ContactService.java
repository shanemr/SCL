package com.vaadin.tutorial.crm.backend.service;

import com.vaadin.tutorial.crm.backend.entity.Patient;
import com.vaadin.tutorial.crm.backend.repository.ContactRepository;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ContactService {
    private static final Logger LOGGER = Logger.getLogger(ContactService.class.getName());
    private ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<Patient> findAll() {
        return contactRepository.findAll();
    }

    public List<Patient> findAll(String stringFilter) {


        if (stringFilter == null || stringFilter.isEmpty()) {
            return contactRepository.findAll();
        } else {
            return contactRepository.search(stringFilter);
        }
    }

    public Patient findPatient(String patient){
        return contactRepository.searchPatient(patient);
    }


    public long count() {
        return contactRepository.count();
    }

    public void delete(Patient patient) {
        contactRepository.delete(patient);
    }

    public void save(Patient patient) {
        if (patient == null) {
            LOGGER.log(Level.SEVERE,
                    "Contact is null. Are you sure you have connected your form to the application?");
            return;
        }
        contactRepository.save(patient);
    }
    @PostConstruct
    public void populateTestData() {

        if (contactRepository.count() == 0) {
            Random r = new Random(0);
            contactRepository.saveAll(
                    Stream.of("user user", "Gabrielle Patel", "Brian Robinson", "Eduardo Haugen",
                            "Koen Johansen", "Alejandro Macdonald", "Angel Karlsson", "Yahir Gustavsson", "Haiden Svensson",
                            "Emily Stewart", "Corinne Davis", "Ryann Davis", "Yurem Jackson", "Kelly Gustavsson",
                            "Eileen Walker", "Katelyn Martin", "Israel Carlsson", "Quinn Hansson", "Makena Smith",
                            "Danielle Watson", "Leland Harris", "Gunner Karlsen", "Jamar Olsson", "Lara Martin",
                            "Ann Andersson", "Remington Andersson", "Rene Carlsson", "Elvis Olsen", "Solomon Olsen",
                            "Jaydan Jackson", "Bernard Nilsen")
                            .map(name -> {
                                String[] split = name.split(" ");
                                Patient patient = new Patient();
                                patient.setFirstName(split[0]);
                                patient.setLastName(split[1]);
                                String email = (patient.getFirstName() + "." + patient.getLastName() + "@" + "something" + ".com").toLowerCase();
                                patient.setEmail(email);
                                patient.setUserName(patient.getFirstName());
                                String password = "user";
                                patient.setPassword(password);
                                patient.setRoles("USER");
                                patient.setEnabled(true);
                                return patient;
                            }).collect(Collectors.toList()));
        }
    }
}
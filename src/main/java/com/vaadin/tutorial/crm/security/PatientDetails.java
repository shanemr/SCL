package com.vaadin.tutorial.crm.security;

import com.vaadin.tutorial.crm.backend.entity.Patient;
import com.vaadin.tutorial.crm.backend.entity.Questionnaire;
import com.vaadin.tutorial.crm.ui.view.questionnaire.Survey;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.Collection;

public class PatientDetails implements UserDetails {

    private Patient patient;

    public PatientDetails(Patient patient) {
        this.patient = patient;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(patient.getRoles());
        System.out.println(patient.getRoles() + " Role");
        return Arrays.asList(authority);
    }

    @Override
    public String getPassword() {
        return new BCryptPasswordEncoder().encode(patient.getPassword());
    }

    @Override
    public String getUsername() {
        System.out.println(patient.getRoles() + " Role");
        System.out.println(patient.getUserName());
        return patient.getUserName();
    }

    public String getFirstName(){
        return patient.getFirstName();
    }

    public String getLastName(){
        return patient.getLastName();
    }

    public Patient getPatient() {
        return patient;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}

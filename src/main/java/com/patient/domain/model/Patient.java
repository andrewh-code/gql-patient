package com.patient.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.patient.exceptions.PatientValidationException;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

//@Builder(builderMethodName = "patientBuilder")
@Entity
@Table(name="patient")
@JsonIgnoreProperties("docs")
public class Patient {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "patients", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Doc> docs = new HashSet<>();

    @Column(name="firstname")
    private String firstName;
    @Column(name="lastname")
    private String lastName;
    @Column(name="email")
    private String email;
    @Column(name="phone")
    private String phone;
    @Column(name="dob")
    private Date dob;
    // don't know exact validation for health card so just validate it's not blank and no numbers
    @Column(name="healthcard")
    private String healthCard;

    @Builder
    public Patient(Long id, Set<Doc> docs, String firstName, String lastName, String email, String phone, Date dob, String healthCard) throws PatientValidationException {
        this.id = id;
        this.docs = docs;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.healthCard = healthCard;
        validateHealthCard();
    }

    public Patient(){}

    public Set<Doc> getDocs() {
        return docs;
    }

    public void setDocs(Set<Doc> docs) {
        this.docs = docs;
    }

    public void addDocs(Doc doc){
        docs.add(doc);
        doc.getPatients().add(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getHealthCard() {
        return healthCard;
    }

    public void setHealthCard(String healthCard) throws PatientValidationException {
        this.healthCard = healthCard;
        validateHealthCard();
    }

    private void validateHealthCard() throws PatientValidationException {
        if (StringUtils.isBlank(this.healthCard) || this.healthCard.matches(".*\\d.*")){
            throw new PatientValidationException("Patient has invalid health card number");
        }
    }
}

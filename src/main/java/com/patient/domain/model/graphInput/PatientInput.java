package com.patient.domain.model.graphInput;

import com.patient.domain.model.Patient;
import com.patient.exceptions.PatientValidationException;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
public class PatientInput {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Date dob;
    private String healthCard;

    @Builder
    public PatientInput(String firstName, String lastName, String email, String phone, Date dob, String healthCard) throws PatientValidationException {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.healthCard = healthCard;
        validateHealthCard();
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDob(Date dob) {
        this.dob = dob;
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

package com.patient.domain.model.graphInput;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Builder(builderMethodName = "patientInputBuilder")
@Getter
@Setter
public class PatientInput {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Date dob;

}

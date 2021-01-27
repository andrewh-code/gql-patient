package com.patient.rest.view;

import com.patient.domain.model.Patient;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class PatientView {

    private Long id;
    private Set<Long> doctorIds;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Date dob;

    public PatientView(){}
    public PatientView(Patient patient){
        this.id = patient.getId();
        this.firstName = patient.getFirstName();
        this.lastName = patient.getLastName();
        this.email = patient.getEmail();
        this.phone = patient.getPhone();
        this.dob = patient.getDob();
    }

    public PatientView(Long id, Set<Long> doctorIds, String firstName, String lastName, String email, String phone, Date dob) {
        this.id = id;
        this.doctorIds = doctorIds;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
    }
}

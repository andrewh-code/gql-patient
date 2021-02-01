package com.patient.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AllPatientInfo {

    // this is going to act as a view model for GraphQL
    private Patient patient;
    private Insurance insurance;
    private String test;

    public AllPatientInfo(){}

    public AllPatientInfo(Patient patient, Insurance insurance, String test) {
        this.insurance = insurance;
        this.test = test;
    }
}

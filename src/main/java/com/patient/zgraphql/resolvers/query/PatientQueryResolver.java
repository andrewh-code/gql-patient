package com.patient.zgraphql.resolvers.query;

import com.patient.domain.model.Patient;
import com.patient.repository.PatientRepo;
import com.patient.service.graphql.PatientService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PatientQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private PatientService patientService;

    public List<Patient> retrieveAllPatientsInfo() {
        List<Patient> patients = patientService.retrieveAllPatientsInfo();

        return patients;
    }

    public Patient retrievePatientInfoById(Long Id) {

        if (Id == null){
            // throw graphql exception
        }
        Patient patient = patientService.retrievePatientInfoById(Id);

        return patient;
    }

}

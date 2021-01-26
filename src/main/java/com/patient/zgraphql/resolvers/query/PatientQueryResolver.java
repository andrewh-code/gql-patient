package com.patient.zgraphql.resolvers.query;

import com.patient.domain.model.Patient;
import com.patient.repository.PatientRepo;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PatientQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private PatientRepo patientRepo;

    public List<Patient> retrieveAllPatientsInfo() {
        List<Patient> patients = patientRepo.findAll();

        return patients;
    }

    public Patient retrievePatientInfoById(Long id) {

        Patient patient = patientRepo.findById(id).orElse(null);
        return patient;
    }

}

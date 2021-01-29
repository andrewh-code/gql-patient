package com.patient.graphql.resolvers.query;

import com.patient.domain.model.Patient;
import com.patient.repository.PatientRepo;
import com.patient.service.graphql.PatientService;
import graphql.GraphQLException;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PatientQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private PatientService patientService;

    public List<Patient> retrieveAllPatientsInfo() throws GraphQLException{
        try {
            List<Patient> patients = patientService.retrieveAllPatientsInfo();
            return patients;
        } catch (Exception e){
            throw new GraphQLException(e.getMessage());
        }
    }

    public Patient retrievePatientInfoById(Long Id) throws GraphQLException{

        if (Id == null){
            throw new GraphQLException("input ID is either blank or null");
        }
        Patient patient;
        try {
            patient = patientService.retrievePatientInfoById(Id);
        } catch (Exception e){
            throw new GraphQLException(e.getMessage());
        }
        return patient;
    }


    // for testing
    // how do you inject it while testing?
    public void setPatientService(PatientService patientService){
        this.patientService = patientService;
    }
}

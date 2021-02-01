package com.patient.graphql.resolvers.query;

import com.patient.domain.model.AllPatientInfo;
import com.patient.domain.model.Patient;
import com.patient.service.PatientService;
import com.patient.service.graphql.PatientServiceImpl;
import graphql.GraphQLException;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PatientQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private PatientService patientService;

    public List<Patient> retrievePatientsInfo() throws GraphQLException{
        try {
            List<Patient> patients = patientService.retrievePatientsInfo();
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

    public AllPatientInfo retrieveAllPatientInfo(Long id) throws GraphQLException {

        AllPatientInfo info = new AllPatientInfo();
        Patient patient = patientService.retrievePatientInfoById(id);
        info.setPatient(patient);
        info.setTest("testtesttest");

        return info;
    }


    // for testing
    // how do you inject it while testing?
    public void setPatientService(PatientServiceImpl patientService){
        this.patientService = patientService;
    }
}

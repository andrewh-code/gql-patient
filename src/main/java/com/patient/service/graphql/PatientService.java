package com.patient.service.graphql;

import com.patient.domain.model.Doc;
import com.patient.domain.model.Patient;
import com.patient.repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PatientService {

    @Autowired
    private PatientRepo patientRepo;

    public List<Patient> retrieveAllPatientsInfo(){
        List<Patient> patients = patientRepo.findAll();
        return patients;
    }

    public Patient retrievePatientInfoById(Long Id){
        Patient patient;
        try {
            patient = patientRepo.findById(Id).orElse(null);
        } catch (NoSuchElementException | NullPointerException e){
            throw e;
        }
        return patient;
    }
}

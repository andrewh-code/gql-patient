package com.patient.service.graphql;

import com.patient.domain.model.Doc;
import com.patient.domain.model.Patient;
import com.patient.domain.model.graphInput.PatientInput;
import com.patient.repository.PatientRepo;
import com.patient.service.PatientService;
import graphql.GraphQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private DocServiceImpl docService;


    public List<Patient> retrieveAllPatientsInfo() throws Exception{
        List<Patient> patients = patientRepo.findAll();
        return patients;
    }

    public Patient retrievePatientInfoById(Long Id){
        Patient patient;
        try {
            patient = patientRepo.findById(Id).get();
        } catch (NoSuchElementException | NullPointerException e){
            throw e;
        }
        return patient;
    }

    public Patient savePatient(PatientInput patientInput) throws GraphQLException{

        try {
            Patient newPatient = Patient.builder()
                    .firstName(patientInput.getFirstName())
                    .lastName(patientInput.getLastName())
                    .email(patientInput.getEmail())
                    .phone(patientInput.getPhone())
                    .dob(patientInput.getDob())
                    .healthCard(patientInput.getHealthCard())
                    .build();
            return patientRepo.save(newPatient);
        } catch (Exception e) {
            throw new GraphQLException(e);
        }
    }

    public void savePatient(Patient patient) throws GraphQLException {
        try {
            patientRepo.save(patient);
        } catch (Exception e) {
            throw new GraphQLException(e);
        }
    }

    public Patient addDoctorToPatient(Long patientId, Long doctorId) throws GraphQLException {

        Patient patient = retrievePatientInfoById(patientId);
        Doc doc = docService.retrieveDoctorById(doctorId);
        patient.addDocs(doc);

        docService.saveDoctor(doc);
        savePatient(patient);
        return patient;
    }

    //for testing
    public void setPatientRepo(PatientRepo patientRepo){
        this.patientRepo = patientRepo;
    }
    public void setDocService(DocServiceImpl docService){
        this.docService = docService;
    }
}

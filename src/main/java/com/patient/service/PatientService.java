package com.patient.service;

import com.patient.domain.model.Patient;
import com.patient.domain.model.graphInput.PatientInput;
import graphql.GraphQLException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PatientService {

    List<Patient> retrievePatientsInfo() throws Exception;

    Patient retrievePatientInfoById(Long Id);

    Patient savePatient(PatientInput patientInput) throws GraphQLException;

    void savePatient(Patient patient) throws GraphQLException;

    Patient addDoctorToPatient(Long patientId, Long doctorId) throws GraphQLException;
}

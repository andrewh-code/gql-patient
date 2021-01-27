package com.patient.graphql.resolvers.mutation;

import com.patient.domain.model.Doc;
import com.patient.domain.model.Patient;
import com.patient.domain.model.graphInput.PatientInput;
import com.patient.repository.PatientRepo;
import com.patient.service.graphql.DocService;
import com.patient.service.graphql.PatientService;
import graphql.GraphQLException;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.tools.DocumentationTool;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class PatientMutationResolver implements GraphQLMutationResolver{

    @Autowired
    private PatientService patientService;

    @Autowired
    private DocService docService;

    private static final String DOB_FORMAT = "mm-dd-YYYY";

    public Patient createNewPatient(PatientInput patientInput) {
        Patient patient = patientService.savePatient(patientInput);
        return patient;
    }

    public Patient addDoctorToPatient(Long patientId, Long doctorId) throws GraphQLException{

        //retrieve patient, retrieve doctor, add doctor
        if (patientId == null || doctorId == null){
            throw new GraphQLException("please verify that the patientId and the doctorId are not empty");
        }

        Patient patient = patientService.addDoctorToPatient(patientId, doctorId);

        return patient;
    }

}

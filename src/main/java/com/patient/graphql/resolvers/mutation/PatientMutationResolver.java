package com.patient.graphql.resolvers.mutation;

import com.patient.domain.model.Patient;
import com.patient.domain.model.graphInput.PatientInput;
import com.patient.service.DocService;
import com.patient.service.PatientService;
import com.patient.service.graphql.DocServiceImpl;
import com.patient.service.graphql.PatientServiceImpl;
import graphql.GraphQLException;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientMutationResolver implements GraphQLMutationResolver{

    @Autowired
    private PatientService patientService;

    @Autowired
    private DocService docService;

    private static final String DOB_FORMAT = "mm-dd-YYYY";

    public Patient createNewPatient(PatientInput patientInput) throws GraphQLException{
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

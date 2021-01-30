package com.patient.service.graphql;

import com.patient.domain.model.Patient;
import com.patient.repository.DocRepo;
import com.patient.repository.PatientRepo;
import org.easymock.EasyMock;
import org.easymock.EasyMockExtension;
import org.easymock.Mock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(EasyMockExtension.class)
public class TestPatientServiceImpl {

    @Mock
    private DocRepo docRepo;

    @Mock
    private PatientRepo patientRepo;

    @Mock
    private DocServiceImpl docService;

    @Test
    public void test_retrieveAllPatientsInfo_success() throws Exception {
        Patient patient1 = Patient.builder()
                .firstName("1")
                .lastName("patient")
                .email("1@patient.ca")
                .dob(new Date())
                .phone("123-456-7890")
                .build();
        Patient patient2 = Patient.builder()
                .firstName("2")
                .lastName("patient")
                .email("2@patient.ca")
                .dob(new Date())
                .phone("123-456-7890")
                .build();

        List<Patient> patients = Arrays.asList(patient1, patient2);

        EasyMock.expect(patientRepo.findAll()).andReturn(patients);
        EasyMock.replay(patientRepo);

        PatientServiceImpl service = new PatientServiceImpl();
        service.setPatientRepo(patientRepo);

        service.retrieveAllPatientsInfo();


        EasyMock.verify(patientRepo);
    }

    @Test
    public void test_savePatient_success() throws Exception {
        Patient newPatient = Patient.builder()
                .firstName("test")
                .lastName("patient")
                .email("test@patient.ca")
                .dob(new Date())
                .phone("123-456-7890")
                .build();

        PatientServiceImpl service = new PatientServiceImpl();
        service.setPatientRepo(patientRepo);
        service.setDocService(docService);

        EasyMock.expect(patientRepo.save(newPatient)).andReturn(newPatient);
        EasyMock.replay(patientRepo);


        service.savePatient(newPatient);

        EasyMock.verify(patientRepo);
    }
}

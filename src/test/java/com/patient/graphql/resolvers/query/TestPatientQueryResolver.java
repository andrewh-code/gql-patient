package com.patient.graphql.resolvers.query;

import com.patient.domain.model.Patient;
import com.patient.service.graphql.PatientServiceImpl;
import graphql.GraphQLException;
import org.easymock.EasyMock;
import org.easymock.EasyMockExtension;
import org.easymock.Mock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@ExtendWith(EasyMockExtension.class)
public class TestPatientQueryResolver {

    @Mock
    private PatientServiceImpl patientService;

    @Test
    public void test_retrieveAllPatientsInfo_success() throws Exception {

        Patient p1 = Patient.builder()
                .id(1L)
                .firstName("1")
                .lastName("lastname")
                .build();
        Patient p2 = Patient.builder()
                .id(1L)
                .firstName("1")
                .lastName("lastname")
                .build();
        List<Patient> patients = Arrays.asList(p1, p2);


        EasyMock.expect(patientService.retrievePatientsInfo()).andReturn(patients);
        EasyMock.replay(patientService);

        PatientQueryResolver resolver = new PatientQueryResolver();
        resolver.setPatientService(patientService);

        List<Patient> output = resolver.retrievePatientsInfo();

        EasyMock.verify(patientService);
    }

    @Test
    public void test_retrieveAllPatientsInfo_fail_throws() throws Exception {
        PatientQueryResolver resolver = new PatientQueryResolver();
        resolver.setPatientService(patientService);

        EasyMock.expect(patientService.retrievePatientsInfo()).andThrow(new Exception("error"));
        EasyMock.replay(patientService);

        try {
            resolver.retrievePatientsInfo();
            Assertions.fail("expected to throw");
        } catch (GraphQLException e){
            Assertions.assertEquals("error", e.getMessage());
        }
    }

    @Test
    public void test_retrievePatientInfoById_fail_npe() throws Exception {

        PatientQueryResolver resolver = new PatientQueryResolver();

        try {
            resolver.retrievePatientInfoById(null);
            Assertions.fail("expected to throw");
        } catch (Exception e){
            Assertions.assertEquals("input ID is either blank or null", e.getMessage());
        }
    }

    @Test
    public void test_retrievePatientInfoById_success() throws Exception {
        Patient p1 = Patient.builder()
                .id(1L)
                .firstName("1")
                .lastName("lastname")
                .build();

        PatientQueryResolver resolver = new PatientQueryResolver();
        resolver.setPatientService(patientService);

        EasyMock.expect(patientService.retrievePatientInfoById(1L)).andReturn(p1);
        EasyMock.replay(patientService);

        Patient patient = resolver.retrievePatientInfoById(1L);

        EasyMock.verify(patientService);

    }

    @Test
    public void test_retrievePatientInfoById_fail_throws_patient_retrieval() throws Exception {
        Patient p1 = Patient.builder()
                .id(1L)
                .firstName("1")
                .lastName("lastname")
                .build();

        PatientQueryResolver resolver = new PatientQueryResolver();
        resolver.setPatientService(patientService);

        EasyMock.expect(patientService.retrievePatientInfoById(1L))
                .andThrow(new NoSuchElementException("error"));
        EasyMock.replay(patientService);

        try {
            Patient patient = resolver.retrievePatientInfoById(1L);
            Assertions.fail("expected to throw");
        } catch (GraphQLException e){
            Assertions.assertEquals("error", e.getMessage());
        }

        EasyMock.verify(patientService);

    }
}

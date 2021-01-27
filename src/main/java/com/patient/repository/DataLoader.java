package com.patient.repository;

import com.patient.domain.model.Doc;
import com.patient.domain.model.Patient;
import com.patient.domain.model.Title;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class DataLoader {

    @Autowired
    private DocRepo docRepo;
    @Autowired
    private PatientRepo patientRepo;

    @PostConstruct
    public void loadData(){
        List<Doc> docs = loadDoctors();
        loadPatients(docs.get(0));
        Patient patient = loadNewPatient();
        updateNewPatient(patient, docs.get(1));
    }

    private List<Doc> loadDoctors(){

        // empty doctor, patient gets assigned the empty doctor when they're first created
        Doc pr1 = Doc.docBuilder()
                .title(Title.MD).firstName("no").lastName("doc").specialty("general physician")
                .build();

        Doc pr2 = Doc.docBuilder()
                .title(Title.DC).firstName("chiro").lastName("two").specialty("functional movement")
                .build();

        Doc pr3 = Doc.docBuilder()
                .title(Title.PT).firstName("physio").lastName("three").specialty("sports physiotherapy")
                .build();

//        docRepo.save(pr1);
        docRepo.save(pr2);
        docRepo.save(pr3);

        return Arrays.asList(pr1, pr2, pr3);
    }

    private void loadPatients(Doc doc){
        Patient p1 = Patient.patientBuilder()
                .docs(new HashSet<>())
                .firstName("patient")
                .lastName("one")
                .email("p1@email.com")
                .phone("213-456-7890")
                .dob(new Date())
                .build();
        Patient p2 = Patient.patientBuilder()
                .docs(new HashSet<>())
                .firstName("patient")
                .lastName("two")
                .email("p2@email.com")
                .phone("321-456-7890")
                .dob(new Date())
                .build();

        p1.addDocs(doc);
        patientRepo.save(p1);
        patientRepo.save(p2);
    }

    private Patient loadNewPatient(){
        Patient p3 = Patient.patientBuilder()
                .firstName("patient")
                .lastName("three")
                .email("p3@email.com")
                .phone("213-456-7890")
                .dob(new Date())
                .build();

        return patientRepo.save(p3);
    }

    private void updateNewPatient(Patient patient, Doc doc){

        Optional<Patient> optionalP3 = patientRepo.findById(patient.getId());
        Patient p3 = optionalP3.orElse(null);
        p3.addDocs(doc);

        docRepo.save(doc);
        patientRepo.save(p3);
    }







}

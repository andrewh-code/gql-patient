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
        loadNewPatient();
        updateNewPatient(docs.get(1));
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

        p1.addDocs(doc);
        patientRepo.save(p1);
    }

    private void loadNewPatient(){
        Patient p4 = Patient.patientBuilder()
                .firstName("patient")
                .lastName("four")
                .email("p4@email.com")
                .phone("213-456-7890")
                .dob(new Date())
                .build();

        patientRepo.save(p4);
    }

    private void updateNewPatient(Doc doc){

        Optional<Patient> optionalP4 = patientRepo.findById(3L);
        Patient p4 = optionalP4.orElse(null);
        p4.addDocs(doc);

        patientRepo.save(p4);
    }







}

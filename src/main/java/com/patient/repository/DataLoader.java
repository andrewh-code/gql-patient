package com.patient.repository;

import com.patient.domain.model.*;
import com.patient.exceptions.PatientValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class DataLoader {

    @Autowired
    private DocRepo docRepo;
    @Autowired
    private PatientRepo patientRepo;
    @Autowired
    private AppointmentRepo appointmentRepo;

    @PostConstruct
    public void loadData() throws PatientValidationException{
        List<Doc> docs = loadDoctors();
        loadPatients(docs.get(0));
        Patient patient = loadNewPatient();
        updateNewPatient(patient, docs.get(1));
        createAppointment(patient, docs.get(1), 5, 8, 30);
        createAppointment(patient, docs.get(2), 5, 9, 0);
        createAppointment(patient, docs.get(1), 10, 10, 0);
        createAppointment(patient, docs.get(2), 20, 11, 0);
        createAppointment(patient, docs.get(1), -10, 11, 30);
        updateAppointment(1, AppointmentStatus.ATTENDED);
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

    private void loadPatients(Doc doc) throws PatientValidationException  {
        Patient p1 = Patient.builder()
                .docs(new HashSet<>())
                .firstName("patient")
                .lastName("one")
                .email("p1@email.com")
                .phone("213-456-7890")
                .dob(new Date())
                .healthCard("healthcard")
                .build();
        Patient p2 = Patient.builder()
                .docs(new HashSet<>())
                .firstName("patient")
                .lastName("two")
                .email("p2@email.com")
                .phone("321-456-7890")
                .dob(new Date())
                .healthCard("healthcard")
                .build();

        p1.addDocs(doc);
        patientRepo.save(p1);
        patientRepo.save(p2);
    }

    private Patient loadNewPatient() throws PatientValidationException{
        Patient p3 = Patient.builder()
                .firstName("patient")
                .lastName("three")
                .email("p3@email.com")
                .phone("213-456-7890")
                .dob(new Date())
                .healthCard("healthcard")
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


    private void createAppointment(Patient patient, Doc doc, int daysFromNow, int hour, int minutes) {

        ZonedDateTime today = ZonedDateTime.now();
        int month = today.getMonthValue();
        int day = today.getDayOfYear() + daysFromNow;
        int year = today.getYear();

        ZonedDateTime scheduled = ZonedDateTime.of(LocalDate.ofYearDay(year, day),
                LocalTime.of(hour, minutes),
                ZoneId.systemDefault());
        ZonedDateTime scheduledEnd = scheduled.plus(30, ChronoUnit.MINUTES);

        Appointment appointment = Appointment.appointmentBuilder()
                .docId(doc.getId())
                .patientId(patient.getId())
                .scheduledDate(scheduled)
                .scheduledEnd(scheduledEnd)
                .appointmentStatus(AppointmentStatus.UPCOMING)
                .build();

        appointmentRepo.save(appointment);
    }

    private void updateAppointment(long appointmentId, AppointmentStatus status) {
        // retrieve appointment, update it, update appointment results
        Appointment appointment = appointmentRepo.findById(appointmentId).get();
        String notes = "patient is fine";
        appointment.setNotes(notes);
        appointmentRepo.save(appointment);
    }

}

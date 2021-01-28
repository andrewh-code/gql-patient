package com.patient.rest.controller;

import com.patient.domain.model.Appointment;
import com.patient.domain.model.Doc;
import com.patient.domain.model.Patient;
import com.patient.repository.AppointmentRepo;
import com.patient.repository.PatientRepo;
import com.patient.rest.response.AppErrorResponse;
import com.patient.rest.response.AppResponse;
import com.patient.rest.view.PatientView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class PatientController {

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private AppointmentRepo appointmentRepo;

    @GetMapping("/patients")
    public ResponseEntity<AppResponse> patients(){

        List<Patient> allPatients = patientRepo.findAll();
        return new ResponseEntity<AppResponse>(new AppResponse(allPatients, 200), HttpStatus.OK);
    }

    @GetMapping("/patients/{patientId}")
    public ResponseEntity<AppResponse> patientId(@PathVariable Long patientId) {
        log.info("hit /patients/id api...");
        Optional<Patient> Patient = patientRepo.findById(patientId);
        Patient retrievedPatient = Patient.orElse(null);

        Set<Doc> docs = retrievedPatient.getDocs() != null ? retrievedPatient.getDocs() : new HashSet<Doc>();
        // patient may not have a doctor(s) associted with them yet
        Set<Long> docIds = docs.stream()
                .map(d -> d.getId()).collect(Collectors.toSet());

        PatientView patientView = new PatientView(retrievedPatient);
        patientView.setDoctorIds(docIds);
        return new ResponseEntity<AppResponse>(new AppResponse(patientView, 200), HttpStatus.OK);
    }

    @GetMapping("/patients/{patientId}/appointments")
    public ResponseEntity<AppResponse> patientsAppointments(@PathVariable Long patientId) {
        log.info("hit /patients/id/appointments api...");

        if (patientId == null){
            return new ResponseEntity<AppResponse>(new AppErrorResponse("patientId is null or blank"), HttpStatus.BAD_REQUEST);
        }
        try {
            if (!patientRepo.findById(patientId).isPresent()){
                throw new Exception ("patient not found");
            }
            List<Appointment> appointments = appointmentRepo.findAllByPatientId(patientId);

            return new ResponseEntity<AppResponse>(new AppResponse(appointments, 200), HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<AppResponse>(new AppErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}

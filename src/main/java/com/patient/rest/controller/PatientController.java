package com.patient.rest.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.patient.domain.model.Appointment;
import com.patient.domain.model.Doc;
import com.patient.domain.model.Patient;
import com.patient.exceptions.PatientValidationException;
import com.patient.repository.AppointmentRepo;
import com.patient.repository.PatientRepo;
import com.patient.rest.response.AppErrorResponse;
import com.patient.rest.response.AppResponse;
import com.patient.rest.view.PatientView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    /**
     * retrieve all patients in the network/database
     * @return
     */
    @GetMapping("/patients")
    @ResponseBody
    public AppResponse patients(){

        List<Patient> allPatients = patientRepo.findAll();
        return new AppResponse(allPatients, HttpStatus.OK);
    }

    /**
     * retrieve a specific patient based off of their ID
     * @param patientId
     * @return
     */
    @GetMapping("/patients/{patientId}")
    @ResponseBody
    public AppResponse patientId(@PathVariable Long patientId) {
        log.info("hit /patients/id api...");
        Optional<Patient> Patient = patientRepo.findById(patientId);
        Patient retrievedPatient = Patient.orElse(null);

        Set<Doc> docs = retrievedPatient.getDocs() != null ? retrievedPatient.getDocs() : new HashSet<Doc>();
        // patient may not have a doctor(s) associted with them yet
        Set<Long> docIds = docs.stream()
                .map(d -> d.getId()).collect(Collectors.toSet());

        PatientView patientView = new PatientView(retrievedPatient);
        patientView.setDoctorIds(docIds);
        return new AppResponse(patientView, HttpStatus.OK);
    }

    /**
     * retrieve a specific patient's appointments. Their entire appointment history
     * @param patientId
     * @return
     */
    @GetMapping("/patients/{patientId}/appointments")
    @ResponseBody
    public AppResponse patientsAppointments(@PathVariable Long patientId) {
        log.info("hit /patients/id/appointments api...");

        if (patientId == null){
            return new AppResponse("patientId is null or blank", HttpStatus.BAD_REQUEST);
        }
        try {
            if (!patientRepo.findById(patientId).isPresent()){
                throw new Exception ("patient not found");
            }
            List<Appointment> appointments = appointmentRepo.findAllByPatientId(patientId);

            return new AppResponse(appointments, HttpStatus.OK);

        } catch (Exception e){
            return new AppResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/patients")
    @ResponseBody
    @ExceptionHandler(PatientValidationException.class)
    public AppResponse createNewPatient(@RequestBody Patient newPatient){

        // assume valid values?
        try {
            newPatient = patientRepo.save(newPatient);
            return new AppResponse(newPatient, HttpStatus.OK);
        } catch (Exception e){
            return new AppResponse("unable to create new patient", HttpStatus.BAD_REQUEST);
        }
    }

}

package com.patient.rest.controller;

import com.patient.domain.model.Appointment;
import com.patient.repository.AppointmentRepo;
import com.patient.rest.response.AppResponse;
import com.sun.javaws.security.AppPolicy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

// use this for appointmentsa nd appointment results
@Slf4j
@RestController
public class AppointmentsController {

    @Autowired
    private AppointmentRepo appointmentRepo;

    /**
     * retrieve all appointemtns created. Includes appoitnemtns in the past
     * shows ALL appointments between all patietns and all doctors
     * @return
     */
    @GetMapping("/appointments")
    public ResponseEntity<AppResponse> retrieveAllAppointments() {
        log.info("hit the /appointments api endpoint");
        List<Appointment> appointments = appointmentRepo.findAll();

        AppResponse response = new AppResponse(appointments, 200);
        return new ResponseEntity<AppResponse>(response, HttpStatus.OK);
    }

    /**
     * retrieve information for a specific appointment
     * @param appointmentId
     * @return
     */
    @GetMapping("/appointments/{appointmentId}")
    public ResponseEntity<AppResponse> retrieveAllAppointments(@PathVariable Long appointmentId) {
        log.info("hit the /appointments api endpoint");
        Appointment appointment = appointmentRepo.findById(appointmentId).get();

        AppResponse response = new AppResponse(appointment, 200);
        return new ResponseEntity<AppResponse>(response, HttpStatus.OK);
    }


    /**
     * Retrieve appointments based off a start and end date
     * too lazy to do qp, use path variable
     * @param strStartDate
     * @param strEndDate
     * @return
     */
    @GetMapping("/appointments/{strStartDate}/{strEndDate}")
    public ResponseEntity<AppResponse> retrieveAppointmentsDateRange(@PathVariable String strStartDate,
                                                                     @PathVariable String strEndDate) {

        AppResponse response = new AppResponse();
        // check to see if strings can be converted to date

        try {
            LocalDate localStartDate = LocalDate.parse(strStartDate);
            LocalDate localEndDate = LocalDate.parse(strEndDate);

            Date startDate = Date.from(localStartDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date endDate = Date.from(localEndDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

            List<Appointment> filteredAppointments = appointmentRepo.queryBetweenDateRange(startDate, endDate);

            return new ResponseEntity<AppResponse>(new AppResponse(filteredAppointments, 200), HttpStatus.OK);
        } catch (Exception e){
            response.setResult(e.getMessage());
            response.setStatus(400);
            return new ResponseEntity<AppResponse>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/appointments")

    public ResponseEntity<AppResponse> createAppointment(@RequestBody Appointment newAppointment){

        try {
            newAppointment = appointmentRepo.save(newAppointment);
            return new ResponseEntity<AppResponse>(new AppResponse(newAppointment, 200), HttpStatus.OK);
        } catch (Exception e){
            AppResponse response = new AppResponse();
            response.setResult(e.getMessage());
            response.setStatus(400);
            return new ResponseEntity<AppResponse>(response, HttpStatus.BAD_REQUEST);
        }
    }
}

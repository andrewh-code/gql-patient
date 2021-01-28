package com.patient.rest.controller;

import com.patient.domain.model.Appointment;
import com.patient.domain.model.Doc;
import com.patient.repository.AppointmentRepo;
import com.patient.repository.DocRepo;
import com.patient.rest.response.AppErrorResponse;
import com.patient.rest.response.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
public class DocController {

    @Autowired
    private DocRepo docRepo;

    @Autowired
    private AppointmentRepo appointmentRepo;

    @GetMapping("/doctors")
    public ResponseEntity<AppResponse> retrievePractitioner(){

        // retrieve from database
        List<Doc> allDocs =  docRepo.findAll();
        return new ResponseEntity<AppResponse>(new AppResponse(allDocs, 200), HttpStatus.OK);
    }

    @GetMapping("/doctors/{id}")
    public ResponseEntity<AppResponse> retrieveDocById(@PathVariable Long id){

        Doc doc;
        try {
            Optional<Doc> optDoc = docRepo.findById(id);
            if (optDoc.isPresent()){
                return new ResponseEntity<AppResponse>(new AppResponse(optDoc.get(), 200), HttpStatus.OK);
            } else {
                return new ResponseEntity<AppResponse>(new AppResponse("doctor with id " + id.toString() + " not found", 404), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            return new ResponseEntity<AppResponse>(new AppErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/doctors/{id}/appointments")
    public ResponseEntity<AppResponse> retrieveDocsAppointments(@PathVariable Long id){

        Doc doc;
        try {
            Optional<Doc> optDoc = docRepo.findById(id);
            if (!optDoc.isPresent()){
                return new ResponseEntity<AppResponse>(new AppResponse("doctor with id " + id.toString() + " not found", 404), HttpStatus.NOT_FOUND);
            }
            List<Appointment> docAppointments = appointmentRepo.findAllByDocId(id);
            return new ResponseEntity<AppResponse>(new AppResponse(docAppointments, 200), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<AppResponse>(new AppErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}

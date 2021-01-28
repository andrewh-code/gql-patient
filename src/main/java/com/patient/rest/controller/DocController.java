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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class DocController {

    @Autowired
    private DocRepo docRepo;

    @Autowired
    private AppointmentRepo appointmentRepo;

    /**
     * retrieve all doctors in the network
     * @return
     */
    @GetMapping("/doctors")
    public ResponseEntity<AppResponse> retrieveDoc(){

        // retrieve from database
        List<Doc> allDocs =  docRepo.findAll();
        return new ResponseEntity<AppResponse>(new AppResponse(allDocs, 200), HttpStatus.OK);
    }
    @PostMapping("/doctors")
    public ResponseEntity<AppResponse> createDoc(@RequestBody Doc newDoc){

        // assume valid values
        try {
            Doc returnedNewDoc = docRepo.save(newDoc);
            return new ResponseEntity<AppResponse>(new AppResponse(returnedNewDoc, 200), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<AppResponse>(new AppResponse("Unable to save doctor", 500), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * retrieve specific doctor based off of their ID
     * @param id
     * @return
     */
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

    /**
     * retrieve all appointments for a specific doctor
     * @param id
     * @return
     */
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

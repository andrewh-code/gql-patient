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
    @ResponseBody
    public AppResponse retrieveDoc(){

        // retrieve from database
        List<Doc> allDocs =  docRepo.findAll();
        return new AppResponse(allDocs,  HttpStatus.OK);
    }
    @PostMapping("/doctors")
    @ResponseBody
    public AppResponse createDoc(@RequestBody Doc newDoc){

        // assume valid values
        try {
            Doc returnedNewDoc = docRepo.save(newDoc);
            return new AppResponse(returnedNewDoc, HttpStatus.OK);
        } catch (Exception e){
            return new AppResponse("Unable to save doctor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * retrieve specific doctor based off of their ID
     * @param id
     * @return
     */
    @GetMapping("/doctors/{id}")
    @ResponseBody
    public AppResponse retrieveDocById(@PathVariable Long id){

        Doc doc;
        try {
            Optional<Doc> optDoc = docRepo.findById(id);
            if (optDoc.isPresent()){
                return new AppResponse(optDoc.get(),  HttpStatus.OK);
            } else {
                return new AppResponse("doctor with id " + id.toString() + " not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            return new AppResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * retrieve all appointments for a specific doctor
     * @param id
     * @return
     */
    @GetMapping("/doctors/{id}/appointments")
    @ResponseBody
    public AppResponse retrieveDocsAppointments(@PathVariable Long id){

        Doc doc;
        try {
            Optional<Doc> optDoc = docRepo.findById(id);
            if (!optDoc.isPresent()){
                return new AppResponse("doctor with id " + id.toString() + " not found", HttpStatus.NOT_FOUND);
            }
            List<Appointment> docAppointments = appointmentRepo.findAllByDocId(id);
            return new AppResponse(docAppointments, HttpStatus.OK);
        } catch (Exception e){
            return new AppResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/doctors/{id}")
    @ResponseBody
    public AppResponse removeDoc(@PathVariable Long id){

        try {
            docRepo.deleteById(id);
            return new AppResponse("doctor with id: " + id + " successfully removed", HttpStatus.OK);
        } catch (Exception e) {
            return new AppResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

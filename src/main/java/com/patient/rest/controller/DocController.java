package com.patient.rest.controller;

import com.patient.domain.model.Doc;
import com.patient.repository.DocRepo;
import com.patient.rest.response.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class DocController {

    @Autowired
    private DocRepo docRepo;

    @RequestMapping("/doctors")
    public ResponseEntity<AppResponse> retrievePractitioner(){

        // retrieve from database
        List<Doc> allDocs =  docRepo.findAll();
        return new ResponseEntity<AppResponse>(new AppResponse(allDocs, 200), HttpStatus.OK);
    }
}

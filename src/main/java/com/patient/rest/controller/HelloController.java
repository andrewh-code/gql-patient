package com.patient.rest.controller;

import com.patient.rest.response.AppResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {

    @RequestMapping("/")
    @ResponseBody
    public AppResponse hello(){

        Map<String, String> map = new HashMap<String, String>();
        String out = "hello world";
        return new AppResponse(out, HttpStatus.OK);
    }
}

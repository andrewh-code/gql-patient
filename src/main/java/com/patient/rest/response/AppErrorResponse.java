package com.patient.rest.response;


import org.springframework.http.HttpStatus;

public class AppErrorResponse extends AppResponse{

    public AppErrorResponse(Object result){
        super(result, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

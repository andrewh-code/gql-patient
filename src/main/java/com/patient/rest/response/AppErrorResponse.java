package com.patient.rest.response;


public class AppErrorResponse extends AppResponse{

    public AppErrorResponse(Object result){
        super(result, 500);
    }

}

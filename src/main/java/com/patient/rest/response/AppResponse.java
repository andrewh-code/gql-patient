package com.patient.rest.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

@Getter
@Setter
public class AppResponse {

    private Object result;
    private int status;
    private LocalDate responseDate;


    public AppResponse(Object result, HttpStatus status) {
        this.result = result;
        this.status = status.value();
        this.responseDate = LocalDate.now();
    }
    public AppResponse(){
        this.responseDate = LocalDate.now();
    }
}

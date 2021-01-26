package com.patient.zrest.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppResponse {

    private Object result;
    private int status;

    public AppResponse(Object result, int status) {
        this.result = result;
        this.status = status;
    }
}

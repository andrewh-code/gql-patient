package com.patient.exceptions;

public class PatientAppException extends Exception {

    public PatientAppException() { }

    public PatientAppException(String message) {
        super(message);
    }

    public PatientAppException(String message, Throwable cause) {
        super(message, cause);
    }

    public PatientAppException(Throwable cause) {
        super(cause);
    }
}

package com.patient.exceptions;

public class PatientValidationException extends Exception {

    public PatientValidationException() { }

    public PatientValidationException(String message) {
        super(message);
    }

    public PatientValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public PatientValidationException(Throwable cause) {
        super(cause);
    }
}

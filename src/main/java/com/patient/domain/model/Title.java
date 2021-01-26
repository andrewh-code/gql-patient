package com.patient.domain.model;

public enum Title {
    MD("Medical Doctor"),
    PT("Physiotherapist"),
    DC("Chiropractor"),
    MT("Massage Therapist");

    public final String longForm;

    private Title(String longForm){
        this.longForm = longForm;
    }
}

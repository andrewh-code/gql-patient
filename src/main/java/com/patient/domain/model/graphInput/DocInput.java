package com.patient.domain.model.graphInput;


import com.patient.domain.model.Title;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocInput {

    private String firstName;
    private String lastName;
    private Title title;
    private String Specialty;

    public DocInput(){}

    public DocInput(String firstName, String lastName, Title title, String specialty) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        Specialty = specialty;
    }
}

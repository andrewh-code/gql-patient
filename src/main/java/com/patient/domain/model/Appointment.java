package com.patient.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Builder(builderMethodName = "appointmentBuilder")
@Getter
@Setter
@Entity
@Table(name="appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Long Id;
    @Column(name="practitioner_id")
    private Long docId;
    @Column(name="patient_id")
    private Long patientId;
    @Column(name="scheduled_date")
    private Date scheduledDate;
    @Column(name="attended")
    private Boolean attended;


}

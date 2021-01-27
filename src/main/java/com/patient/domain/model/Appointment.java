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
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    public Long Id;
    @Column(name="doc_id")
    private Long docId;
    @Column(name="patient_id")
    private Long patientId;
    @Column(name="scheduled_date")
    private Date scheduledDate;
    @Column(name="attended")
    private Boolean attended;

    public Appointment(){}

    public Appointment(Long id, Long docId, Long patientId, Date scheduledDate, Boolean attended) {
        Id = id;
        this.docId = docId;
        this.patientId = patientId;
        this.scheduledDate = scheduledDate;
        this.attended = attended;
    }
}

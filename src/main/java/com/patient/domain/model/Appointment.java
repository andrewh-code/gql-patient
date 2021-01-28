package com.patient.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Date;

// lombok stuff
@Builder(builderMethodName = "appointmentBuilder")
@Getter
@Setter
// jpa stuff
@Table(name="appointment")
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="appointment_id")
    public Long id;

    // set this as foreign key
    @Column(name="doc_id")
    private Long docId;
    // set this as foreign key
    @Column(name="patient_id")
    private Long patientId;
    @Column(name="scheduled_date")
    private ZonedDateTime scheduledDate;
    @Column(name="attended")
    private AppointmentStatus appointmentStatus;
    @Column(name="notes")
    private String notes;

    public Appointment(){}

    public Appointment(Long id, Long docId, Long patientId,
                       ZonedDateTime scheduledDate, AppointmentStatus appointmentStatus,
                       String notes) {
        this.id = id;
        this.notes = notes;
        this.docId = docId;
        this.patientId = patientId;
        this.scheduledDate = scheduledDate;
        this.appointmentStatus = appointmentStatus;
    }
}

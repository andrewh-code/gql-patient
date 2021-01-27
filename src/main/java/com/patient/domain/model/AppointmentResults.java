package com.patient.domain.model;

import javax.persistence.*;

@Entity
@Table(name="appointment_results")
public class AppointmentResults {

    /**
     * create one to one mapping between appointment table
     * and appointment_results
     * will always have an appointment, however don't necessarily have
     * appointment results
     */
    @Id
    public Long Id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    // with MapsId, it's not a bi-directional relationship
    public Appointment appointment;

    @Column(name="doc_notes")
    public String docNotes;
}

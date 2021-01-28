package com.patient.repository;

import com.patient.domain.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Long> {

    @Query("select a from Appointment a where a.scheduledDate >= ?1 and a.scheduledDate <= ?2")
    List<Appointment> queryBetweenDateRange(Date startDate, Date endDate);

    @Query("select a from Appointment a where a.patientId = ?1")
    List<Appointment> findAllByPatientId(Long patientId);

    @Query("select a from Appointment a where a.docId = ?1")
    List<Appointment> findAllByDocId(Long docId);
}

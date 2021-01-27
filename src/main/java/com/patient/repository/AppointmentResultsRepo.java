package com.patient.repository;

import com.patient.domain.model.AppointmentResults;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentResultsRepo extends JpaRepository<AppointmentResults, Long> {

}

package com.patient.service;

import com.patient.domain.model.Appointment;
import com.patient.domain.model.AppointmentStatus;
import com.patient.domain.model.graphInput.AppointmentInput;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AppointmentService {

    List<Appointment> retrieveAllAppointments() throws Exception;

    Appointment retrieveAppointmentById(Long id) throws Exception;

    List<Appointment> retrieveAppointmentsByStatus(AppointmentStatus status);

    Appointment createNewAppointment(Appointment appointment) throws Exception;

    Appointment updateAppointment(Appointment appointment, AppointmentInput appointmentInput) throws Exception;

}

package com.patient.service.graphql;

import com.patient.domain.model.Appointment;
import com.patient.domain.model.AppointmentStatus;
import com.patient.domain.model.Doc;
import com.patient.repository.AppointmentRepo;
import com.patient.repository.DocRepo;
import com.patient.repository.PatientRepo;
import graphql.GraphQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private DocRepo docRepo;

    @Autowired
    private AppointmentRepo appointmentRepo;

    public List<Appointment> retrieveAllAppointments() throws Exception {

        return appointmentRepo.findAll();
    }

    public Appointment retrieveAppointmentById(Long id) throws Exception {
        Appointment appointment;
        try {
            appointment = appointmentRepo.findById(id).orElse(null);
            return appointment;
        } catch (NoSuchElementException | NullPointerException e){
            throw e;
        }
    }

    public List<Appointment> retrieveAppointmentsByStatus(AppointmentStatus status){

        try {
            List<Appointment> appointments = appointmentRepo.findAll().stream()
                    .filter(a -> a.getAppointmentStatus() == status)
                    .collect(Collectors.toList());

            return appointments;
        } catch (Exception e){
            throw e;
        }
    }


}

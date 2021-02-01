package com.patient.graphql.resolvers.query;

import com.patient.domain.model.Appointment;
import com.patient.domain.model.AppointmentStatus;
import com.patient.service.AppointmentService;
import com.patient.service.graphql.AppointmentServiceImpl;
import graphql.GraphQLException;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AppointmentQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private AppointmentService appointmentService;

    public List<Appointment> retrieveAllAppointments(int skip, int first){

        try {
            List<Appointment> appointments = appointmentService.retrieveAllAppointments(skip, first);
            return appointments;
        } catch (Exception e){
            throw new GraphQLException(e.getMessage());
        }
    }

    public Appointment retrieveAppointmentById(Long id){

        if (id == null){
            throw new GraphQLException("appointment id is blank or null");
        }

        try {
            Appointment appointment = appointmentService.retrieveAppointmentById(id);
            return appointment;
        } catch (Exception e){
            throw new GraphQLException(e.getMessage());
        }
    }

    public List<Appointment> retrieveAppointmentByStatus(AppointmentStatus status){

        if (status == null) {
            throw new GraphQLException("appointment status cannot be determined");
        }
        try {
            List<Appointment> appointments = appointmentService.retrieveAppointmentsByStatus(status);
            return appointments;
        } catch (Exception e){
            throw new GraphQLException(e.getMessage());
        }
    }

    public List<Appointment> retrievePatientsAppointments(Long patientId, AppointmentStatus status) {
        if (patientId == null) {
            throw new GraphQLException("appointment status cannot be determined");
        }
        try {
            List<Appointment> appointments = appointmentService.retrievePatientsAppointments(patientId, status);
            return appointments;
        } catch (Exception e){
            throw new GraphQLException(e.getMessage());
        }
    }

    public List<Appointment> retrieveDoctorsAppointments(Long docId, AppointmentStatus status) {
        if (docId == null) {
            throw new GraphQLException("appointment status cannot be determined");
        }
        try {
            List<Appointment> appointments = appointmentService.retrieveDoctorsAppointments(docId, status);
            return appointments;
        } catch (Exception e){
            throw new GraphQLException(e.getMessage());
        }
    }
}

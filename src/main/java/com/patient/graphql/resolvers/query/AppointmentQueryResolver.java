package com.patient.graphql.resolvers.query;

import com.patient.domain.model.Appointment;
import com.patient.domain.model.AppointmentStatus;
import com.patient.service.graphql.AppointmentServiceImpl;
import graphql.GraphQLException;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AppointmentQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private AppointmentServiceImpl appointmentServiceImpl;

    public List<Appointment> retrieveAllAppointments(){

        try {
            List<Appointment> appointments = appointmentServiceImpl.retrieveAllAppointments();
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
            Appointment appointment = appointmentServiceImpl.retrieveAppointmentById(id);
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
            List<Appointment> appointments = appointmentServiceImpl.retrieveAppointmentsByStatus(status);
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
            List<Appointment> appointments = appointmentServiceImpl.retrievePatientsAppointments(patientId, status);
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
            List<Appointment> appointments = appointmentServiceImpl.retrieveDoctorsAppointments(docId, status);
            return appointments;
        } catch (Exception e){
            throw new GraphQLException(e.getMessage());
        }
    }
}

package com.patient.service.graphql;

import com.patient.domain.model.Appointment;
import com.patient.domain.model.AppointmentStatus;
import com.patient.domain.model.graphInput.AppointmentInput;
import com.patient.repository.AppointmentRepo;
import com.patient.repository.DocRepo;
import com.patient.repository.PatientRepo;
import com.patient.service.AppointmentService;
import graphql.GraphQLException;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

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
            // we can create a custom query for this isntead of filtering
            List<Appointment> appointments = appointmentRepo.findAll().stream()
                    .filter(a -> a.getAppointmentStatus() == status)
                    .collect(Collectors.toList());

            return appointments;
        } catch (Exception e){
            throw e;
        }
    }

    public List<Appointment> retrievePatientsAppointments(Long patientId, AppointmentStatus status){

        try {
            List<Appointment> appointments = new ArrayList<Appointment>();
            if (status == null){
                appointments = appointmentRepo.findAllByPatientId(patientId);
            } else {
                appointments = appointmentRepo.findAllByPatientId(patientId)
                        .stream()
                        .filter(a -> a.getAppointmentStatus() == status)
                        .collect(Collectors.toList());
            }

            return appointments;
        } catch (Exception e){
            throw e;
        }
    }

    public List<Appointment> retrieveDoctorsAppointments(Long docId, AppointmentStatus status){

        try {
            List<Appointment> appointments = new ArrayList<Appointment>();
            if (status == null){
                appointments = appointmentRepo.findAllByDocId(docId);
            } else {
                appointments = appointmentRepo.findAllByDocId(docId)
                        .stream()
                        .filter(a -> a.getAppointmentStatus() == status)
                        .collect(Collectors.toList());
            }
            return appointments;
        } catch (Exception e){
            throw e;
        }
    }


    public Appointment createNewAppointment(Appointment appointment) throws Exception {

        if (isAppointmentConflicting(appointment)){
            throw new Exception("appointment start " + appointment.getScheduledDate() + " conflicts with " +
                    "existing schedule");
        }

        return appointmentRepo.save(appointment);
    }

    public Appointment updateAppointment(Appointment appointment, AppointmentInput appointmentInput) throws Exception {

        String existingNotes = appointment.getNotes();
        ZonedDateTime existingSchedule = appointment.getScheduledDate();

        if (StringUtils.isNotBlank(appointmentInput.getAppointmentStatus().toString()) &&
            appointment.getAppointmentStatus() != appointmentInput.getAppointmentStatus()){
                appointment.setAppointmentStatus(appointmentInput.getAppointmentStatus());
        }

        // update appointment date
        if (appointmentInput.getScheduledDate().isAfter(existingSchedule)){
            appointment.setScheduledDate(appointmentInput.getScheduledDate());
        } else {
            throw new Exception("Cannot re-schedule appointment to an earlier date");
        }

        if (isAppointmentConflicting(appointment)){
            throw new Exception("appointment start " + appointment.getScheduledDate() + " conflicts with " +
                    "existing schedule");
        }

        if (StringUtils.isNotBlank(appointmentInput.getNotes()) &&
                !existingNotes.equals(appointmentInput.getNotes())){
            // add to the notes, do not replace
            existingNotes = existingNotes + "\n" + appointmentInput.getNotes();
            appointment.setNotes(existingNotes);
        }
        // check to see if appointment is conflicting

        return appointmentRepo.save(appointment);
    }

    // won't need to do it based on patient's schedule
    private boolean isAppointmentConflicting(Appointment appointment){
        long docId = appointment.getDocId();
        ZonedDateTime scheduledDate = appointment.getScheduledDate();
        ZonedDateTime endDate = appointment.getScheduledEnd();

        List<Appointment> docAppointments = retrieveDoctorsAppointments(docId, AppointmentStatus.UPCOMING);

        // lets just check to see if the scheduled start is conflicting for now
        boolean conflict = docAppointments.stream()
                .filter(a -> a.getScheduledDate().isEqual(scheduledDate))
                .count() > 0;

        return conflict;
    }


}

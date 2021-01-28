package com.patient.graphql.resolvers.mutation;

import com.patient.domain.model.Appointment;
import com.patient.domain.model.AppointmentStatus;
import com.patient.domain.model.Doc;
import com.patient.domain.model.Patient;
import com.patient.domain.model.graphInput.AppointmentInput;
import com.patient.service.graphql.AppointmentService;
import com.patient.service.graphql.DocService;
import com.patient.service.graphql.PatientService;
import graphql.GraphQLException;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
public class AppointmentMutationResolver implements GraphQLMutationResolver {

    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private DocService docService;
    @Autowired
    private PatientService patientService;

    public Appointment createNewAppointment(AppointmentInput appointmentInput){

        Long patientId = appointmentInput.getPatientId();
        Long docId = appointmentInput.getDocId();
        ZonedDateTime newScheduledApp = appointmentInput.getScheduledDate();

        try {

            if (newScheduledApp.isBefore(ZonedDateTime.now()) || newScheduledApp.isEqual(ZonedDateTime.now())){
                throw new Exception ("Unable to create appointment, please choose a date greater than today");
            }

            Doc existingDoc = docService.retrieveDoctorById(docId);
            Patient existingPatient = patientService.retrievePatientInfoById(patientId);
            if (existingDoc == null || existingPatient == null){
                throw new Exception("unable to find doctor or patient. Please make sure they exist");
            }

            Appointment newAppointment = Appointment.appointmentBuilder()
                    .docId(docId)
                    .patientId(patientId)
                    .scheduledDate(appointmentInput.getScheduledDate())
                    .appointmentStatus(AppointmentStatus.UPCOMING)
                    .build();

            newAppointment = appointmentService.createNewAppointment(newAppointment);
            return newAppointment;

        } catch (Exception e){
            throw new GraphQLException(e.getMessage());
        }
    }

    public Appointment updateAppointment(Long appointmentId, AppointmentInput appointmentInput){

        // check to see if appointment exists
        try {
            Appointment existingAppointment = appointmentService.retrieveAppointmentById(appointmentId);
            if (existingAppointment == null){
                throw new Exception("Unable to find appointment with id: " + appointmentId);
            }

            existingAppointment = appointmentService.updateAppointment(existingAppointment, appointmentInput);

            return existingAppointment;

        } catch (Exception e){
            throw new GraphQLException(e.getMessage());
        }

    }
}

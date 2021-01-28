package com.patient.domain.model.graphInput;

import com.patient.domain.model.AppointmentStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@Builder(builderMethodName = "appointmentInputBuilder")
public class AppointmentInput {

    private Long patientId;
    private Long docId;
    private AppointmentStatus appointmentStatus;
    private ZonedDateTime scheduledDate;
    private String notes;
}

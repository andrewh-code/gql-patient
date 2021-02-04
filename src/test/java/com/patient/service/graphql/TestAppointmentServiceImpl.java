package com.patient.service.graphql;

import com.patient.domain.model.Appointment;
import com.patient.domain.model.AppointmentStatus;
import com.patient.repository.AppointmentRepo;
import com.patient.service.AppointmentService;
import org.easymock.EasyMock;
import org.easymock.EasyMockExtension;
import org.easymock.Mock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(EasyMockExtension.class)
public class TestAppointmentServiceImpl {

    @Mock
    private AppointmentRepo appointmentRepo;


    @Test
    public void test_retrieveAllAppointments_success() throws Exception {

        int skip = 0;
        int first = 3;

        Appointment ap1 = Appointment.appointmentBuilder()
                .docId(1L)
                .patientId(1L)
                .appointmentStatus(AppointmentStatus.UPCOMING)
                .scheduledDate(ZonedDateTime.now())
                .build();
        Appointment ap2 = Appointment.appointmentBuilder()
                .docId(2L)
                .patientId(1L)
                .appointmentStatus(AppointmentStatus.UPCOMING)
                .scheduledDate(ZonedDateTime.now())
                .build();
        Appointment ap3 = Appointment.appointmentBuilder()
                .docId(3L)
                .patientId(1L)
                .appointmentStatus(AppointmentStatus.UPCOMING)
                .scheduledDate(ZonedDateTime.now())
                .build();
        List<Appointment> appList = new ArrayList<Appointment>();
        appList.add(ap1);
        appList.add(ap2);
        appList.add(ap3);

        AppointmentServiceImpl service = new AppointmentServiceImpl();
        service.setAppointmentRepo(appointmentRepo);


        EasyMock.expect(appointmentRepo.findAll()).andReturn(appList);
        EasyMock.replay(appointmentRepo);

        service.retrieveAllAppointments(skip, first);

        EasyMock.verify(appointmentRepo);
    }

}

package com.patient.graphql.resolvers.mutation;

import com.patient.domain.model.Appointment;
import com.patient.service.graphql.AppointmentService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMutationResolver implements GraphQLMutationResolver {

    @Autowired
    private AppointmentService appointmentService;




}

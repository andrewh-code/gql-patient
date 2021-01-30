package com.patient.graphql.resolvers;

import com.patient.domain.model.Insurance;
import com.patient.domain.model.Patient;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class InsuranceResolver implements GraphQLResolver<Patient> {

    private static final ExecutorService threadService = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    public CompletableFuture<Insurance> retrieveInsuranceInfo(Patient patient) {

        return CompletableFuture.supplyAsync(
                () -> {
                    return new Insurance();
            }, threadService
        );
    }

}

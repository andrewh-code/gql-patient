package com.patient.graphql.resolvers;

import com.patient.domain.model.AllPatientInfo;
import com.patient.domain.model.Insurance;
import com.patient.domain.model.Patient;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class InsuranceResolver implements GraphQLResolver<AllPatientInfo> {

    private static final ExecutorService threadService = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

//    public CompletableFuture<Insurance> insurance(Patient patient) {
//
//        Insurance insurance = Insurance.insuranceBuilder()
//                .insuranceCompany("manulife")
//                .insuranceCompanyId(123)
//                .benefitsPlan("basic")
//                .memberId(42)
//                .build();
//
//        return CompletableFuture.supplyAsync(
//                () -> {
//                    return insurance;
//            }, threadService
//        );
//    }

    public Insurance insurance(AllPatientInfo allPatientInfo){

        Insurance insurance = Insurance.insuranceBuilder()
                .insuranceCompany("manulife")
                .insuranceCompanyId(123)
                .benefitsPlan("basic")
                .memberId(42)
                .build();

        return insurance;
    }

}

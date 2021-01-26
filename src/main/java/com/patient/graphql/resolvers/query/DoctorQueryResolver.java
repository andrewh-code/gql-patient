package com.patient.graphql.resolvers.query;

import com.patient.domain.model.Doc;
import com.patient.repository.DocRepo;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DoctorQueryResolver implements GraphQLQueryResolver {

    // put this in its own service
    @Autowired
    private DocRepo docRepo;

    public List<Doc> retrieveAllDoctors(){
        return docRepo.findAll();
    }

    public Doc retrieveDoctorById(Long id) {
        Optional<Doc> foundDoc = Optional.empty();
        foundDoc = docRepo.findById(id);
        return foundDoc.get();
    }


}

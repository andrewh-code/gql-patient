package com.patient.graphql.resolvers.query;

import com.patient.domain.model.Doc;
import com.patient.repository.DocRepo;
import com.patient.service.graphql.DocService;
import graphql.GraphQLException;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DocQueryResolver implements GraphQLQueryResolver {

    // put this in its own service
    @Autowired
    private DocService docService;

    public List<Doc> retrieveAllDoctors(){
        return docService.retrieveAllDoctors();
    }

    public Doc retrieveDoctorById(Long id) throws GraphQLException{

        Doc existingDoc;
        try {
            existingDoc = docService.retrieveDoctorById(id);
            if (existingDoc == null) {
                throw new GraphQLException("unable to find existing doctor");
            }
        } catch (Exception e){
            throw new GraphQLException(e);
        }

        return existingDoc;
    }
}

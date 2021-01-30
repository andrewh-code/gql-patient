package com.patient.graphql.resolvers.query;

import com.patient.domain.model.Doc;
import com.patient.service.graphql.DocServiceImpl;
import graphql.GraphQLException;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DocQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private DocServiceImpl docService;

    public List<Doc> retrieveAllDoctors() throws GraphQLException{
        try {
            return docService.retrieveAllDoctors();
        } catch (Exception e){
            throw new GraphQLException(e.getMessage());
        }
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

    // for testing purposes
    // cannot mock servcice for testing?
    public void setDocService(DocServiceImpl docService){
        this.docService = docService;
    }
}

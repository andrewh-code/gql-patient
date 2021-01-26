package com.patient.zgraphql.resolvers.mutation;

import com.patient.domain.model.Doc;
import com.patient.domain.model.graphInput.DocInput;
import com.patient.repository.DocRepo;
import com.patient.service.graphql.DocService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DocMutationResolver implements GraphQLMutationResolver {

    @Autowired
    private DocRepo docRepo;

    @Autowired
    private DocService docService;

    public Doc createNewDoctor(DocInput docInput) {

        Doc newDoc = docService.createNewDoctor(docInput);
        return newDoc;
    }

    public Doc updateDoctorInfo(Long Id, DocInput docInput){

        Doc existingDoc = docRepo.findById(Id).orElseGet(null);
        if (existingDoc == null){
            // throw exception here
            return new Doc();
        }

        // how do you map?
        if (StringUtils.isNotBlank(docInput.getFirstName()) &&
                !docInput.getFirstName().equals(existingDoc.getFirstName())){
            existingDoc.setFirstName(existingDoc.getFirstName());
        }
        if (StringUtils.isNotBlank(docInput.getLastName()) &&
                !docInput.getLastName().equals(existingDoc.getLastName())){
            existingDoc.setLastName(existingDoc.getLastName());
        }
        if (StringUtils.isNotBlank(docInput.getTitle().toString()) &&
                !docInput.getTitle().equals(existingDoc.getTitle())){
            existingDoc.setTitle(existingDoc.getTitle());
        }
        if (StringUtils.isNotBlank(docInput.getSpecialty()) &&
                !docInput.getSpecialty().equals(existingDoc.getSpecialty())){
            existingDoc.setSpecialty(existingDoc.getSpecialty());
        }
        return existingDoc;
    }
}

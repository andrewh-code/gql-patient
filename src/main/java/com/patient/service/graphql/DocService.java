package com.patient.service.graphql;

import com.patient.domain.model.Doc;
import com.patient.domain.model.graphInput.DocInput;
import com.patient.repository.DocRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocService {

    @Autowired
    private DocRepo docRepo;

    public Doc createNewDoctor(DocInput docInput){
        Doc newDoc = Doc.docBuilder()
                .firstName(docInput.getFirstName())
                .lastName(docInput.getLastName())
                .title(docInput.getTitle())
                .specialty(docInput.getSpecialty())
                .build();
        docRepo.save(newDoc);

        return newDoc;
    }
}

package com.patient.service.graphql;

import com.patient.domain.model.Doc;
import com.patient.domain.model.graphInput.DocInput;
import com.patient.repository.DocRepo;
import com.patient.service.DocService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DocServiceImpl implements DocService {

    @Autowired
    private DocRepo docRepo;

    // turn this into saveDoc function
    public void saveDoctor(Doc newDoc) {
            docRepo.save(newDoc);
    }

    public void updateDoctor(Doc existingDoc, DocInput docInput) {
        // how do you map?
        if (StringUtils.isNotBlank(docInput.getFirstName()) &&
                !docInput.getFirstName().equals(existingDoc.getFirstName())){
            existingDoc.setFirstName(docInput.getFirstName());
        }
        if (StringUtils.isNotBlank(docInput.getLastName()) &&
                !docInput.getLastName().equals(existingDoc.getLastName())){
            existingDoc.setLastName(docInput.getLastName());
        }
        if (docInput.getTitle() != null &&
                !docInput.getTitle().equals(existingDoc.getTitle())){
            existingDoc.setTitle(docInput.getTitle());
        }
        if (StringUtils.isNotBlank(docInput.getSpecialty()) &&
                !docInput.getSpecialty().equals(existingDoc.getSpecialty())){
            existingDoc.setSpecialty(docInput.getSpecialty());
        }

        docRepo.save(existingDoc);
    }

    public Doc retrieveDoctorById(Long id){
        Doc doc;
        try {
            doc = docRepo.findById(id).orElse(null);
        } catch (NoSuchElementException | NullPointerException e){
            throw e;
        }
        return doc;
    }

    public List<Doc> retrieveAllDoctors(){
        return docRepo.findAll();
    }

    public void removeDoc(Long id) {
        docRepo.deleteById(id);
    }

    // testing purposes
    public void setDocRepo(DocRepo docRepo){
        this.docRepo = docRepo;
    }


}

package com.patient.service;

import com.patient.domain.model.Doc;
import com.patient.domain.model.graphInput.DocInput;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DocService {

    void saveDoctor(Doc newDoc);

    void updateDoctor(Doc existingDoc, DocInput docInput);

    Doc retrieveDoctorById(Long id);

    List<Doc> retrieveAllDoctors();

    void removeDoc(Long id);

}

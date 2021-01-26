package com.patient.repository;

import com.patient.domain.model.Doc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocRepo extends JpaRepository<Doc, Long> {

}

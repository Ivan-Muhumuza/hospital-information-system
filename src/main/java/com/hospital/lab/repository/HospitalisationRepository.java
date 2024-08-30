package com.hospital.lab.repository;

import com.hospital.lab.entity.Hospitalisation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface HospitalisationRepository extends MongoRepository<Hospitalisation, String> {
    List<Hospitalisation> findByPatientId(String patientId);

    @Query("{'ward.department.name': ?0}")
    List<Hospitalisation> findByDepartmentName(String departmentName);
}


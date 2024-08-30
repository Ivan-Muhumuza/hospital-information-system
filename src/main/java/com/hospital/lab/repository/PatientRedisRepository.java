package com.hospital.lab.repository;

import com.hospital.lab.entity.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRedisRepository extends CrudRepository<Patient, String> {
}


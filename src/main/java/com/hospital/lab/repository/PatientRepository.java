package com.hospital.lab.repository;

import com.hospital.lab.entity.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface PatientRepository extends MongoRepository<Patient, String> {
    List<Patient> findBySurname(String surname);

    @Query("{'address': {$regex: ?0, $options: 'i'}}")
    List<Patient> findByAddressContaining(String addressPart);

    List<Patient> findByAgeGreaterThanEqual(int age);
}


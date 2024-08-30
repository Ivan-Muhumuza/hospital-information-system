package com.hospital.lab.repository;

import com.hospital.lab.entity.Doctor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface DoctorRepository extends MongoRepository<Doctor, String> {
    List<Doctor> findBySpeciality(String speciality);

    @Query("{'department.name': ?0}")
    List<Doctor> findByDepartmentName(String departmentName);
}


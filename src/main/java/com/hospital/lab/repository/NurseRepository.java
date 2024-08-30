package com.hospital.lab.repository;

import com.hospital.lab.entity.Nurse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface NurseRepository extends MongoRepository<Nurse, String> {
    List<Nurse> findByRotation(String rotation);

    @Query("{'department.name': ?0}")
    List<Nurse> findByDepartmentName(String departmentName);
}


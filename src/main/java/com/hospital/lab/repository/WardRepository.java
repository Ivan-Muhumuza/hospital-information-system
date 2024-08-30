package com.hospital.lab.repository;

import com.hospital.lab.entity.Ward;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface WardRepository extends MongoRepository<Ward, String> {
    List<Ward> findByNumberOfBeds(int numberOfBeds);

    @Query("{'department.name': ?0}")
    List<Ward> findByDepartmentName(String departmentName);
}


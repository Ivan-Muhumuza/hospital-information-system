package com.hospital.lab.repository;

import com.hospital.lab.entity.Department;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface DepartmentRepository extends MongoRepository<Department, String> {
    List<Department> findByName(String name);

    @Query("{'building': ?0}")
    List<Department> findByBuilding(String building);
}


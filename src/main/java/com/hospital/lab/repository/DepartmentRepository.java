package com.hospital.lab.repository;

import com.hospital.lab.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    // Custom query method using method naming convention
    List<Department> findByName(String name);
}


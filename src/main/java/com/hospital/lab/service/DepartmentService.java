package com.hospital.lab.service;

import com.hospital.lab.entity.Department;
import com.hospital.lab.entity.Doctor;
import com.hospital.lab.repository.DepartmentRepository;
import com.hospital.lab.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;


    public Department saveDepartment(Department department) {
        Doctor director = department.getDirector();

        // Reattach the detached Doctor entity to the current session using findById
        if (director != null) {
            // Fetch the persistent Doctor entity from the database
            director = doctorRepository.findById(director.getEmployeeNumber())
                    .orElseThrow(() -> new RuntimeException("Doctor not found"));

            // Set the director to the department
            department.setDirector(director);

            // Set the department reference on the Doctor entity
            director.setDepartment(department);
        }

        // Now save the department
        return departmentRepository.save(department);
    }


    // Get a Department by ID
    public Optional<Department> getDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }

    // Delete a Department by ID
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }

    // Custom Query Method: Find Departments by Name
    public List<Department> findByName(String name) {
        return departmentRepository.findByName(name);
    }
}


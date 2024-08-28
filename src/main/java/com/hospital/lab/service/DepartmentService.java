package com.hospital.lab.service;

import com.hospital.lab.entity.Department;
import com.hospital.lab.entity.Doctor;
import com.hospital.lab.repository.DepartmentRepository;
import com.hospital.lab.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Transactional // Declarative transaction management
    public Department saveDepartment(Department department) {
        Doctor director = department.getDirector();

        if (director != null) {
            director = doctorRepository.findById(director.getEmployeeNumber())
                    .orElseThrow(() -> new RuntimeException("Doctor not found"));
            department.setDirector(director);
            director.setDepartment(department);
        }

        return departmentRepository.save(department);
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "departments", key = "#id")
    public Optional<Department> getDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }

    @Transactional
    @CacheEvict(value = "departments", key = "#id")
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "departmentsByName", key = "#name")
    public List<Department> findByName(String name) {
        return departmentRepository.findByName(name);
    }

    @Transactional
    @CachePut(value = "departments", key = "#department.departmentCode")
    public Department updateDepartment(Department department) {
        return departmentRepository.save(department);
    }
}

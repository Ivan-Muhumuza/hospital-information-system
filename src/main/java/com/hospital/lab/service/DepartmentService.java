package com.hospital.lab.service;

import com.hospital.lab.entity.Department;
import com.hospital.lab.entity.Doctor;
import com.hospital.lab.repository.DepartmentRepository;
import com.hospital.lab.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import java.util.concurrent.TimeUnit;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private RedisService redisService;  // Inject RedisService

    private static final String DEPARTMENT_CACHE_KEY = "departments:";
    private static final String DEPARTMENT_NAME_CACHE_KEY = "departmentsByName:";

    @Transactional
    public Department saveDepartment(Department department) {
        Doctor director = department.getDirector();

        if (director != null) {
            director = doctorRepository.findById(director.getId())
                    .orElseThrow(() -> new RuntimeException("Doctor not found"));
            department.setDirector(director);
            director.setDepartment(department);
        }

        Department savedDepartment = departmentRepository.save(department);

        // Cache the saved department in Redis
        redisService.saveWithExpiry(DEPARTMENT_CACHE_KEY + savedDepartment.getId(), savedDepartment, 10, TimeUnit.MINUTES);

        return savedDepartment;
    }

    @Transactional(readOnly = true)
    public Optional<Department> getDepartmentById(String id) {
        // Check Redis cache first
        Department cachedDepartment = (Department) redisService.get(DEPARTMENT_CACHE_KEY + id);
        if (cachedDepartment != null) {
            return Optional.of(cachedDepartment);
        }

        // Fetch from database if not found in cache
        Optional<Department> department = departmentRepository.findById(id);
        department.ifPresent(dept -> redisService.saveWithExpiry(DEPARTMENT_CACHE_KEY + id, dept, 10, TimeUnit.MINUTES));

        return department;
    }

    @Transactional
    public void deleteDepartment(String id) {
        departmentRepository.deleteById(id);
        // Remove from Redis cache
        redisService.delete(DEPARTMENT_CACHE_KEY + id);
    }

    @Transactional(readOnly = true)
    public List<Department> findByName(String name) {
        // Check Redis cache first
        List<Department> cachedDepartments = (List<Department>) redisService.get(DEPARTMENT_NAME_CACHE_KEY + name);
        if (cachedDepartments != null) {
            return cachedDepartments;
        }

        // Fetch from database if not found in cache
        List<Department> departments = departmentRepository.findByName(name);
        redisService.saveWithExpiry(DEPARTMENT_NAME_CACHE_KEY + name, departments, 10, TimeUnit.MINUTES);

        return departments;
    }

    @Transactional
    public Department updateDepartment(Department department) {
        Department updatedDepartment = departmentRepository.save(department);

        // Update cache in Redis
        redisService.saveWithExpiry(DEPARTMENT_CACHE_KEY + updatedDepartment.getId(), updatedDepartment, 10, TimeUnit.MINUTES);

        return updatedDepartment;
    }
}

package com.hospital.lab.controller;

import com.hospital.lab.entity.Department;
import com.hospital.lab.repository.DepartmentRepository;
import com.hospital.lab.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DepartmentRepository departmentRepository;

    // Create or Update Department
    @PostMapping
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        Department savedDepartment = departmentService.saveDepartment(department);
        return ResponseEntity.ok(savedDepartment);
    }

    // Get Department by ID
    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        Optional<Department> department = departmentService.getDepartmentById(id);
        return department.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable("id") Long id, @RequestBody Department department) {
        // Check if the department exists
        if (!departmentRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        // Set the ID of the department to be updated
        department.setDepartmentCode(id);

        // Update the department
        Department updatedDepartment = departmentService.saveDepartment(department);

        return ResponseEntity.ok(updatedDepartment);
    }


    // Delete Department by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }

    // Custom Query Method: Find by Name
    @GetMapping("/search")
    public List<Department> getDepartmentsByName(@RequestParam String name) {
        return departmentService.findByName(name);
    }
}


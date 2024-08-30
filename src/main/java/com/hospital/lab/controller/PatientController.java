package com.hospital.lab.controller;

import com.hospital.lab.entity.Patient;
import com.hospital.lab.service.PatientRedisService;
import com.hospital.lab.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientRedisService patientRedisService;

    // Save a new patient
    @PostMapping
    public ResponseEntity<Patient> savePatient(@RequestBody Patient patient) {
        Patient savedPatient = patientService.savePatient(patient);
        patientRedisService.setPatient("patient:" + savedPatient.getId(), savedPatient);
        return ResponseEntity.ok(savedPatient);
    }

    // Get a patient by ID
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable String id) {
        Patient patient = patientRedisService.getPatient("patient:" + id);
        if (patient == null) {
            patient = patientService.getPatientById(id).orElse(null);
            if (patient != null) {
                patientRedisService.setPatient("patient:" + id, patient);
            }
        }
        return patient != null ? ResponseEntity.ok(patient) : ResponseEntity.notFound().build();
    }

    // Update a patient by ID
    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable String id, @RequestBody Patient patientDetails) {
        Patient updatedPatient = patientService.updatePatient(id, patientDetails);
        patientRedisService.updatePatient("patient:" + id, updatedPatient); // Update in Redis
        return ResponseEntity.ok(updatedPatient);
    }

    // Delete a patient by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable String id) {
        patientService.deletePatient(id);
        patientRedisService.deletePatient("patient:" + id); // Delete from Redis
        return ResponseEntity.noContent().build();
    }

    // Retrieve all patients
    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = patientRedisService.getAllPatients();
        if (patients.isEmpty()) {
            patients = patientService.getAllPatients(); // Fallback to DB if Redis is empty
            for (Patient patient : patients) {
                patientRedisService.setPatient("patient:" + patient.getId(), patient); // Cache in Redis
            }
        }
        return ResponseEntity.ok(patients);
    }

    // Get a Patient by surname
    @GetMapping("/search/surname/{surname}")
    public ResponseEntity<List<Patient>> getPatientsBySurname(@PathVariable String surname) {
        return ResponseEntity.ok(patientService.findPatientsBySurname(surname));
    }

    // get patients by key word in their address
    @GetMapping("/search/address")
    public ResponseEntity<List<Patient>> getPatientsbyAddressContaining(@RequestParam String addressPart) {
        return ResponseEntity.ok(patientService.findPatientsByAddressContaining(addressPart));
    }

    //  Get patients equal or older than   given  age
    @GetMapping("/search/age-and-older")
    public ResponseEntity<?> getPatientsAgedAtLeast(@RequestParam(required = true) Integer age) {
        if (age == null) {
            return ResponseEntity.badRequest().body("Age parameter is required");
        }
        try {
            List<Patient> patients = patientService.findPatientsAgedAtLeast(age);
            return ResponseEntity.ok(patients);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An error occurred: " + e.getMessage());
        }
    }
}
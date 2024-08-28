package com.hospital.lab.controller;

import com.hospital.lab.entity.Patient;
import com.hospital.lab.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/programmatic")
    public ResponseEntity<Patient> savePatientProgrammatically(@RequestBody Patient patient) {
        Patient savedPatient = patientService.savePatientProgrammatically(patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPatient);
    }

    @PostMapping("/propagation")
    public ResponseEntity<Patient> savePatientWithPropagation(@RequestBody Patient patient) {
        Patient savedPatient = patientService.savePatientWithPropagation(patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPatient);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}


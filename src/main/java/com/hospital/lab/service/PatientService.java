package com.hospital.lab.service;

import com.hospital.lab.entity.Patient;
import com.hospital.lab.exceptions.ResourceNotFoundException;
import com.hospital.lab.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.core.MongoTemplate;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    // Save a new patient or update an existing patient
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    // Get a patient by ID
    public Optional<Patient> getPatientById(String id) {
        return patientRepository.findById(id);
    }

    // Delete a patient by ID
    public void deletePatient(String id) {
        patientRepository.deleteById(id);
    }

    // Update an existing patient
    public Patient updatePatient(String id, Patient patientDetails) {
        return patientRepository.findById(id)
                .map(patient -> {
                    patient.setSurname(patientDetails.getSurname());
                    patient.setName(patientDetails.getName());
                    patient.setAddress(patientDetails.getAddress());
                    patient.setAge(patientDetails.getAge());
                    return patientRepository.save(patient);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id " + id));
    }

    // Retrieve all patients
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public List<Patient> findPatientsBySurname(String surname) {
        return patientRepository.findBySurname(surname);
    }

    public List<Patient> findPatientsByAddressContaining(String addressPart) {
        return patientRepository.findByAddressContaining(addressPart);
    }

    public List<Patient> findPatientsAgedAtLeast(int age) {
        return patientRepository.findByAgeGreaterThanEqual(age);
    }
}

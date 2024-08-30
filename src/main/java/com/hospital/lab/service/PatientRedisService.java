package com.hospital.lab.service;

import com.hospital.lab.entity.Patient;
import com.hospital.lab.repository.PatientRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientRedisService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private PatientRedisRepository patientRedisRepository;

    // Set a patient in Redis
    public void setPatient(String key, Patient patient) {
        redisTemplate.opsForValue().set(key, patient);
    }

    // Get a patient from Redis
    public Patient getPatient(String key) {
        return (Patient) redisTemplate.opsForValue().get(key);
    }

    // Save a patient in Redis
    public void savePatient(Patient patient) {
        patientRedisRepository.save(patient);
    }

    // Find a patient by ID in Redis
    public Patient findPatientById(String id) {
        return patientRedisRepository.findById(id).orElse(null);
    }

    // Update a patient in Redis
    public void updatePatient(String key, Patient patient) {
        redisTemplate.opsForValue().set(key, patient);
    }

    // Delete a patient by ID in Redis
    public void deletePatient(String key) {
        redisTemplate.delete(key);
    }

    // Get all patients
    public List<Patient> getAllPatients() {
        List<Object> patients = redisTemplate.opsForValue().multiGet(redisTemplate.keys("patient:*"));
        return patients.stream().map(p -> (Patient) p).collect(Collectors.toList());
    }
}

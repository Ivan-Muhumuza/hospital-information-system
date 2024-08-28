package com.hospital.lab.service;

import com.hospital.lab.entity.Patient;
import com.hospital.lab.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    public Patient savePatientProgrammatically(Patient patient) {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());

        try {
            // Business logic
            Patient savedPatient = patientRepository.save(patient);
            transactionManager.commit(status);
            return savedPatient;
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public Patient savePatientWithPropagation(Patient patient) {  // method will use propagation and isolation settings
        return patientRepository.save(patient);
    }

    @Transactional(readOnly = true)
    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    @Transactional
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}

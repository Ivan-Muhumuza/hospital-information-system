package com.hospital.lab.service;

import com.hospital.lab.entity.Hospitalisation;
import com.hospital.lab.entity.HospitalisationId;
import com.hospital.lab.entity.Patient;
import com.hospital.lab.entity.Ward;
import com.hospital.lab.repository.HospitalisationRepository;
import com.hospital.lab.repository.PatientRepository;
import com.hospital.lab.repository.WardRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class HospitalisationService {

    @Autowired
    private HospitalisationRepository hospitalisationRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private WardRepository wardRepository;


    @Transactional
    public Hospitalisation createHospitalisation(Hospitalisation hospitalisation) {
        // Ensure Patient exists
        Patient patient = patientRepository.findById(hospitalisation.getPatient().getPatientNumber())
                .orElseThrow(() -> new EntityNotFoundException("Patient not found"));

        // Ensure Ward exists
        Ward ward = wardRepository.findById(hospitalisation.getWard().getId())
                .orElseThrow(() -> new EntityNotFoundException("Ward not found"));

        // Set the found entities to ensure they are managed
        hospitalisation.setPatient(patient);
        hospitalisation.setWard(ward);

        // Create and set the HospitalisationId
        HospitalisationId id = new HospitalisationId();
        id.setPatientNumber(patient.getPatientNumber());
        id.setWardId(ward.getId());
        hospitalisation.setId(id);

        return hospitalisationRepository.save(hospitalisation);
    }

    @Cacheable(value = "hospitalisations", key = "#id")
    public Optional<Hospitalisation> getHospitalisationById(HospitalisationId id) {
        return hospitalisationRepository.findById(id);
    }

    @CacheEvict(value = "hospitalisations", key = "#id")
    public void deleteHospitalisation(HospitalisationId id) {
        hospitalisationRepository.deleteById(id);
    }

    @CachePut(value = "hospitalisations", key = "#hospitalisation.id")
    public Hospitalisation updateHospitalisation(Hospitalisation hospitalisation) {
        return hospitalisationRepository.save(hospitalisation);
    }
}


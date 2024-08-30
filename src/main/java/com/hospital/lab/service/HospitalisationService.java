package com.hospital.lab.service;

import com.hospital.lab.entity.Hospitalisation;
import com.hospital.lab.entity.Patient;
import com.hospital.lab.entity.Ward;
import com.hospital.lab.repository.HospitalisationRepository;
import com.hospital.lab.repository.PatientRepository;
import com.hospital.lab.repository.WardRepository;
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
        Patient patient = patientRepository.findById(hospitalisation.getPatient().getId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Ward ward = wardRepository.findById(hospitalisation.getWard().getId())
                .orElseThrow(() -> new RuntimeException("Ward not found"));

        hospitalisation.setPatient(patient);
        hospitalisation.setWard(ward);

        return hospitalisationRepository.save(hospitalisation);
    }

    @Cacheable(value = "hospitalisations", key = "#id")
    public Optional<Hospitalisation> getHospitalisationById(String id) {
        return hospitalisationRepository.findById(id);
    }

    @CacheEvict(value = "hospitalisations", key = "#id")
    public void deleteHospitalisation(String id) {
        hospitalisationRepository.deleteById(id);
    }

    @CachePut(value = "hospitalisations", key = "#hospitalisation.id")
    public Hospitalisation updateHospitalisation(Hospitalisation hospitalisation) {
        return hospitalisationRepository.save(hospitalisation);
    }
}
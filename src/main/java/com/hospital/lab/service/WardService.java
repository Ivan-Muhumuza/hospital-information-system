package com.hospital.lab.service;

import com.hospital.lab.entity.Ward;
import com.hospital.lab.repository.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class WardService {
    @Autowired
    private WardRepository wardRepository;

    @Transactional
    public Ward saveWard(Ward ward) {
        return wardRepository.save(ward);
    }

    @Transactional(readOnly = true)
    public Optional<Ward> getWardById(String id) {
        return wardRepository.findById(id);
    }

    @Transactional
    public Ward updateWardBeds(String wardId, int numberOfBeds) {
        Ward ward = wardRepository.findById(wardId)
                .orElseThrow(() -> new RuntimeException("Ward not found"));
        ward.setNumberOfBeds(numberOfBeds);
        return wardRepository.save(ward);
    }
}
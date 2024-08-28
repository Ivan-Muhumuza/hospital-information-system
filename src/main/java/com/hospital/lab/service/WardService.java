package com.hospital.lab.service;

import com.hospital.lab.entity.Ward;
import com.hospital.lab.entity.WardId;
import com.hospital.lab.repository.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class WardService {

    @Autowired
    private WardRepository wardRepository;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public Ward saveWard(Ward ward) {
        // Business logic
        return wardRepository.save(ward);
    }

    @Transactional(readOnly = true)
    public Optional<Ward> getWardById(WardId wardId) {
        return wardRepository.findById(wardId);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public Ward updateWardBeds(WardId wardId, int numberOfBeds) {
        Ward ward = wardRepository.findById(wardId)
                .orElseThrow(() -> new RuntimeException("Ward not found"));
        ward.setNumberOfBeds(numberOfBeds);
        return wardRepository.save(ward);
    }
}


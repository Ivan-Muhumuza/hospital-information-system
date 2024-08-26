package com.hospital.lab.repository;

import com.hospital.lab.entity.Hospitalisation;
import com.hospital.lab.entity.HospitalisationId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BedAssignmentRepository extends JpaRepository<Hospitalisation, HospitalisationId> {
}


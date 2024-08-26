package com.hospital.lab.repository;

import com.hospital.lab.entity.BedAssignment;
import com.hospital.lab.entity.BedAssignmentId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BedAssignmentRepository extends JpaRepository<BedAssignment, BedAssignmentId> {
}


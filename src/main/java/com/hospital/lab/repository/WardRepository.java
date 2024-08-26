package com.hospital.lab.repository;

import com.hospital.lab.entity.Ward;
import com.hospital.lab.entity.WardId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WardRepository extends JpaRepository<Ward, WardId> {
}


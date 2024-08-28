package com.hospital.lab.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class HospitalisationId implements Serializable {
    private Long patientNumber;

    @Embedded
    private WardId wardId;

    // Default constructor
    public HospitalisationId() {}

    // Constructor
    public HospitalisationId(Long patientNumber, WardId wardId) {
        this.patientNumber = patientNumber;
        this.wardId = wardId;
    }

    public Long getPatientNumber() {
        return patientNumber;
    }

    public void setPatientNumber(Long patientNumber) {
        this.patientNumber = patientNumber;
    }

    public WardId getWardId() {
        return wardId;
    }

    public void setWardId(WardId wardId) {
        this.wardId = wardId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HospitalisationId that = (HospitalisationId) o;
        return Objects.equals(patientNumber, that.patientNumber) && Objects.equals(wardId, that.wardId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patientNumber, wardId);
    }

//    @Embeddable
//    public static class WardId implements Serializable {
//        @Column(name = "ward_number")
//        private int wardNumber;
//
//        @Column(name = "department_code")
//        private Long departmentCode;
//
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            WardId wardId = (WardId) o;
//            return wardNumber == wardId.wardNumber && Objects.equals(departmentCode, wardId.departmentCode);
//        }
//
//        @Override
//        public int hashCode() {
//            return Objects.hash(wardNumber, departmentCode);
//        }
//    }
//
//    private WardId wardId;

}





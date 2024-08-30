//package com.hospital.lab.entity;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Embeddable;
//import java.io.Serializable;
//import java.util.Objects;
//
//@Embeddable
//public class WardId implements Serializable {
//    @Column(name = "department_code")
//    private Long departmentCode;
//
//    @Column(name = "ward_number")
//    private int wardNumber;
//
//    // Default constructor (required by JPA)
//    public WardId() {
//    }
//
//    // Constructor that accepts departmentCode and wardNumber
//    public WardId(int wardNumber, Long departmentCode) {
//        this.departmentCode = departmentCode;
//        this.wardNumber = wardNumber;
//    }
//
//    // Getters and setters (if needed)
//    public Long getDepartmentCode() {
//        return departmentCode;
//    }
//
//    public void setDepartmentCode(Long departmentCode) {
//        this.departmentCode = departmentCode;
//    }
//
//    public int getWardNumber() {
//        return wardNumber;
//    }
//
//    public void setWardNumber(int wardNumber) {
//        this.wardNumber = wardNumber;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        WardId wardId = (WardId) o;
//        return wardNumber == wardId.wardNumber &&
//                Objects.equals(departmentCode, wardId.departmentCode);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(departmentCode, wardNumber);
//    }
//}
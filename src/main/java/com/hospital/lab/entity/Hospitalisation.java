package com.hospital.lab.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "hospitalisation")
public class Hospitalisation {
    @EmbeddedId
    private HospitalisationId id;

    private String diagnosis;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("patientNumber")
    @JoinColumn(name = "patient_number")
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("wardId")
    @JoinColumns({
            @JoinColumn(name = "ward_number", referencedColumnName = "ward_number"),
            @JoinColumn(name = "department_code", referencedColumnName = "department_code")
    })
    private Ward ward;

    // Default constructor
    public Hospitalisation() {}

    // Constructor with parameters
    public Hospitalisation(Patient patient, Ward ward, String diagnosis) {
        this.patient = patient;
        this.ward = ward;
        this.diagnosis = diagnosis;
        this.id = new HospitalisationId(patient.getPatientNumber(), ward.getId());
    }

    public HospitalisationId getId() {
        return id;
    }

    public void setId(HospitalisationId id) {
        this.id = id;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Ward getWard() {
        return ward;
    }

    public void setWard(Ward ward) {
        this.ward = ward;
    }
}
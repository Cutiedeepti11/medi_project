package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.interfaces.Searchable;

/**
 * Doctor entity representing a medical professional.
 * Implements Searchable for search operations.
 */
public class Doctor extends Person implements Searchable {
    private String specialization;
    private String licenseNumber;
    private double consultationFee;

    public Doctor(String id, String name, String email, String phoneNumber,
                  String specialization, String licenseNumber, double consultationFee) {
        super(id, name, email, phoneNumber);
        this.specialization = specialization;
        this.licenseNumber = licenseNumber;
        this.consultationFee = consultationFee;
    }

    @Override
    public boolean searchById(String id) {
        return this.getId().equals(id);
    }

    @Override
    public boolean searchByName(String name) {
        return this.getName().equalsIgnoreCase(name);
    }

    // Getters and Setters
    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public double getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(double consultationFee) {
        this.consultationFee = consultationFee;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", specialization='" + specialization + '\'' +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", consultationFee=" + consultationFee +
                '}';
    }
}

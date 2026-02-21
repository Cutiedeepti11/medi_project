package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.interfaces.Searchable;

/**
 * Patient entity representing a medical patient.
 * Implements Searchable for search operations.
 */
public class Patient extends Person implements Searchable {
    private String medicalHistory;
    private String bloodGroup;

    public Patient(String id, String name, String email, String phoneNumber,
                   String medicalHistory, String bloodGroup) {
        super(id, name, email, phoneNumber);
        this.medicalHistory = medicalHistory;
        this.bloodGroup = bloodGroup;
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
    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", medicalHistory='" + medicalHistory + '\'' +
                ", bloodGroup='" + bloodGroup + '\'' +
                '}';
    }
}

package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.util.DataStore;

import java.util.List;
import java.util.stream.Collectors;

public class PatientService {

    private final DataStore<Patient> patientStore = new DataStore<>();

    public void addPatient(Patient patient) {
        patientStore.add(patient.getId(), patient);
    }

    public Patient getPatient(String id) {
        return patientStore.get(id);
    }

    public List<Patient> getAllPatients() {
        return patientStore.getAll().stream().toList();
    }

    public List<Patient> searchByName(String name) {
        return patientStore.getAll()
                .stream()
                .filter(p -> p.searchByName(name))
                .collect(Collectors.toList());
    }
}

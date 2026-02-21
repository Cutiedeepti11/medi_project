package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.util.DataStore;

import java.util.List;
import java.util.stream.Collectors;

public class DoctorService {

    private final DataStore<Doctor> doctorStore = new DataStore<>();

    public void addDoctor(Doctor doctor) {
        doctorStore.add(doctor.getId(), doctor);
    }

    public Doctor getDoctor(String id) {
        return doctorStore.get(id);
    }

    public List<Doctor> getAllDoctors() {
        return doctorStore.getAll().stream().toList();
    }

    // Stream example (bonus)
    public List<Doctor> searchByName(String name) {
        return doctorStore.getAll()
                .stream()
                .filter(d -> d.searchByName(name))
                .collect(Collectors.toList());
    }
}

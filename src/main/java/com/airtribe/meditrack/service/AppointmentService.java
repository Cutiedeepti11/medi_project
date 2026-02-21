package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.exception.AppointmentNotFoundException;
import com.airtribe.meditrack.util.DataStore;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class AppointmentService {

    private final DataStore<Appointment> appointmentStore = new DataStore<>();

    public void createAppointment(String id, Doctor doctor, Patient patient, LocalDateTime time) {
        Appointment appointment = new Appointment(id, doctor, patient, time);
        appointmentStore.add(id, appointment);
    }

    public Appointment getAppointment(String id) throws AppointmentNotFoundException {
        Appointment appointment = appointmentStore.get(id);
        if (appointment == null) {
            throw new AppointmentNotFoundException("Appointment not found for ID: " + id);
        }
        return appointment;
    }

    public void cancelAppointment(String id) throws AppointmentNotFoundException {
        Appointment appointment = getAppointment(id);
        appointment.cancel();
    }

    public List<Appointment> getAllAppointments() {
        return appointmentStore.getAll().stream().toList();
    }

    // Bonus: appointments by doctor
    public List<Appointment> getAppointmentsByDoctor(String doctorId) {
        return appointmentStore.getAll()
                .stream()
                .filter(a -> a.getDoctor().getId().equals(doctorId))
                .collect(Collectors.toList());
    }
}

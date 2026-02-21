package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.constants.AppointmentStatus;

import java.time.LocalDateTime;

/**
 * Represents an appointment between a doctor and a patient.
 */
public class Appointment {

    private String appointmentId;
    private Doctor doctor;
    private Patient patient;
    private LocalDateTime appointmentTime;
    private AppointmentStatus status;

    public Appointment(String appointmentId, Doctor doctor, Patient patient,
                       LocalDateTime appointmentTime) {
        this.appointmentId = appointmentId;
        this.doctor = doctor;
        this.patient = patient;
        this.appointmentTime = appointmentTime;
        this.status = AppointmentStatus.PENDING;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void confirm() {
        this.status = AppointmentStatus.CONFIRMED;
    }

    public void complete() {
        this.status = AppointmentStatus.COMPLETED;
    }

    public void cancel() {
        this.status = AppointmentStatus.CANCELLED;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId='" + appointmentId + '\'' +
                ", doctor=" + doctor.getName() +
                ", patient=" + patient.getName() +
                ", time=" + appointmentTime +
                ", status=" + status.getDisplayName() +
                '}';
    }
}

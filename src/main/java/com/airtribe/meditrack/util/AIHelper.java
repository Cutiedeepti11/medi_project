package com.airtribe.meditrack.util;

import com.airtribe.meditrack.constants.Specialization;
import com.airtribe.meditrack.entity.Doctor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Rule-based AI helper for doctor and slot recommendation.
 */
public class AIHelper {

    public static Specialization recommendSpecialization(String symptom) {

        symptom = symptom.toLowerCase();

        if (symptom.contains("chest")) return Specialization.CARDIOLOGY;
        if (symptom.contains("head")) return Specialization.NEUROLOGY;
        if (symptom.contains("bone") || symptom.contains("fracture"))
            return Specialization.ORTHOPEDICS;
        if (symptom.contains("child")) return Specialization.PEDIATRICS;

        return Specialization.GENERAL;
    }

    public static Optional<Doctor> recommendDoctor(
            String symptom, List<Doctor> doctors) {

        Specialization specialization = recommendSpecialization(symptom);

        return doctors.stream()
                .filter(d -> d.getSpecialization() != null &&
                           d.getSpecialization()
                        .equalsIgnoreCase(specialization.getDisplayName()))
                .findFirst();
    }

    public static LocalDateTime suggestAppointmentSlot() {
        return LocalDateTime.now().plusHours(1).withMinute(0).withSecond(0);
    }
}

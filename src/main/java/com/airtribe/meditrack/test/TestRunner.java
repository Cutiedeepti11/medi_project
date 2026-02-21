package com.airtribe.meditrack.test;

import com.airtribe.meditrack.constants.Specialization;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.util.AIHelper;

import java.util.List;
import java.util.Optional;

/**
 * Manual test runner for the MediTrack application.
 * Note: Uses manual tests instead of JUnit for this project.
 */
public class TestRunner {
    public static void main(String[] args) {
        printHeader();
        printManualChecklist();
        runQuickSmokeChecks();
    }

    private static void printHeader() {
        System.out.println("MediTrack Manual Test Runner");
        System.out.println("================================");
    }

    private static void printManualChecklist() {
        System.out.println("\nManual Test Checklist (CLI)");
        System.out.println("--------------------------------");

        printCase("MT-01 Add Doctor", List.of(
                "Menu: 1",
                "Inputs: Name=Dr Heart, Email=heart@gmail.com, Phone=89007123345, Specialization=0 (Cardiology), License=LIC001, Fee=700",
                "Expected: 'Doctor added' with new ID"
        ));

        printCase("MT-02 Add Patient", List.of(
                "Menu: 2",
                "Inputs: Name=Alex, Email=alex@mail.com, Phone=9876543210, History=None, Blood=O+",
                "Expected: 'Patient added' with new ID"
        ));

        printCase("MT-03 Create Appointment", List.of(
                "Precondition: Use IDs from MT-01 and MT-02",
                "Menu: 3",
                "Inputs: Doctor ID, Patient ID",
                "Expected: Appointment created with suggested slot"
        ));

        printCase("MT-04 View Appointments", List.of(
                "Precondition: At least one appointment",
                "Menu: 4",
                "Expected: Appointment list printed"
        ));

        printCase("MT-05 Generate Bill", List.of(
                "Precondition: Valid appointment ID",
                "Menu: 5",
                "Inputs: Appointment ID",
                "Expected: Receipt and summary printed"
        ));

        printCase("MT-06 AI Doctor Recommendation", List.of(
                "Precondition: At least one doctor in memory or in doctors.csv",
                "Menu: 6",
                "Input: 'chest pain'",
                "Expected: Cardiologist recommended",
                "Edge: Input empty -> validation message",
                "Edge: Unknown symptom -> 'No suitable doctor found'"
        ));

        printCase("MT-07 Save Data to CSV", List.of(
                "Menu: 7",
                "Expected: doctors.csv, patients.csv, appointments.csv updated",
                "Note: Re-run app with --loadData to reload"
        ));

        printCase("MT-08 Exit", List.of(
                "Menu: 8",
                "Expected: Program exits"
        ));
    }

    private static void runQuickSmokeChecks() {
        System.out.println("\nQuick Smoke Checks (Non-interactive)");
        System.out.println("--------------------------------");

        Doctor cardio = new Doctor("D1", "Dr Heart", "h@mail.com", "9000000000",
                Specialization.CARDIOLOGY.getDisplayName(), "LIC1", 700.0);
        Doctor neuro = new Doctor("D2", "Dr Head", "n@mail.com", "9111111111",
                Specialization.NEUROLOGY.getDisplayName(), "LIC2", 800.0);
        List<Doctor> doctors = List.of(cardio, neuro);

        assertRecommend("chest pain", doctors, Specialization.CARDIOLOGY.getDisplayName());
        assertRecommend("headache", doctors, Specialization.NEUROLOGY.getDisplayName());
        assertNoRecommend("skin rash", doctors);
    }

    private static void printCase(String id, List<String> steps) {
        System.out.println("\n" + id);
        for (String step : steps) {
            System.out.println("- " + step);
        }
    }

    private static void assertRecommend(String symptom, List<Doctor> doctors, String expectedSpec) {
        Optional<Doctor> result = AIHelper.recommendDoctor(symptom, doctors);
        String outcome = result.isPresent() && expectedSpec.equalsIgnoreCase(result.get().getSpecialization())
                ? "PASS"
                : "FAIL";
        System.out.println("AI recommend for '" + symptom + "' => " + outcome);
    }

    private static void assertNoRecommend(String symptom, List<Doctor> doctors) {
        Optional<Doctor> result = AIHelper.recommendDoctor(symptom, doctors);
        String outcome = result.isEmpty() ? "PASS" : "FAIL";
        System.out.println("AI recommend for '" + symptom + "' => " + outcome);
    }
}

package com.airtribe.meditrack;

import com.airtribe.meditrack.constants.Specialization;
import com.airtribe.meditrack.entity.*;
import com.airtribe.meditrack.exception.AppointmentNotFoundException;
import com.airtribe.meditrack.service.*;
import com.airtribe.meditrack.util.*;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    private static final DoctorService doctorService = new DoctorService();
    private static final PatientService patientService = new PatientService();
    private static final AppointmentService appointmentService = new AppointmentService();

    private static final Scanner sc = new Scanner(System.in);

    private static final String DOCTORS_FILE = "doctors.csv";
    private static final String PATIENTS_FILE = "patients.csv";
    private static final String APPOINTMENTS_FILE = "appointments.csv";

    public static void main(String[] args) {

        if (args.length > 0 && args[0].equalsIgnoreCase("--loadData")) {
            loadAllData();
        }


        while (true) {
            showMenu();
            int choice = sc.nextInt();
            sc.nextLine();

            try {
                switch (choice) {
                    case 1 -> addDoctor();
                    case 2 -> addPatient();
                    case 3 -> createAppointment();
                    case 4 -> viewAppointments();
                    case 5 -> generateBill();
                    case 6 -> aiRecommendation();
                    case 7 -> saveAllData();
                    case 8 -> System.exit(0);
                    default -> System.out.println("Invalid choice");
                }
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n==== MEDITRACK SYSTEM ====");
        System.out.println("1. Add Doctor");
        System.out.println("2. Add Patient");
        System.out.println("3. Create Appointment");
        System.out.println("4. View Appointments");
        System.out.println("5. Generate Bill");
        System.out.println("6. AI Doctor Recommendation");
        System.out.println("7. Save Data to CSV");
        System.out.println("8. Exit");
        System.out.print("Choose: ");
    }

    private static void addDoctor() {
        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Phone: ");
        String phone = sc.nextLine();

        System.out.println("Specializations:");
        for (Specialization s : Specialization.values())
            System.out.println(s.ordinal() + ". " + s.getDisplayName());

        int specChoice = sc.nextInt();
        sc.nextLine();

        String specialization = Specialization.values()[specChoice].getDisplayName();


        System.out.print("License Number: ");
        String license = sc.nextLine();

        System.out.print("Consultation Fee: ");
        double fee = sc.nextDouble();
        sc.nextLine();

        String id = IdGenerator.getInstance().nextId();

        Doctor doctor = new Doctor(id, name, email, phone, specialization, license, fee);
        doctorService.addDoctor(doctor);

        System.out.println("✅ Doctor added with ID: " + id);
    }

    private static void addPatient() {
        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Phone: ");
        String phone = sc.nextLine();

        System.out.print("Medical History: ");
        String history = sc.nextLine();

        System.out.print("Blood Group: ");
        String blood = sc.nextLine();

        String id = IdGenerator.getInstance().nextId();

        Patient patient = new Patient(id, name, email, phone, history, blood);
        patientService.addPatient(patient);

        System.out.println("✅ Patient added with ID: " + id);
    }

    private static void createAppointment() {
        System.out.print("Doctor ID: ");
        String docId = sc.nextLine();

        System.out.print("Patient ID: ");
        String patId = sc.nextLine();

        Doctor doctor = doctorService.getDoctor(docId);
        Patient patient = patientService.getPatient(patId);

        if (doctor == null || patient == null) {
            System.out.println("❌ Doctor or Patient not found");
            return;
        }

        LocalDateTime slot = AIHelper.suggestAppointmentSlot();
        String appointmentId = IdGenerator.getInstance().nextId();

        appointmentService.createAppointment(appointmentId, doctor, patient, slot);

        System.out.println("📅 Appointment created at: " + DateUtil.format(slot));
    }

    private static void viewAppointments() {
        appointmentService.getAllAppointments()
                .forEach(System.out::println);
    }

    private static void generateBill() throws AppointmentNotFoundException {
        System.out.print("Appointment ID: ");
        String id = sc.nextLine();

        Appointment appointment = appointmentService.getAppointment(id);
        Bill bill = new Bill(IdGenerator.getInstance().nextId(), appointment);

        System.out.println("\n" + bill.generateReceipt());
        System.out.println("Summary: " + bill.generateSummary());
    }

    private static void aiRecommendation() {
        System.out.print("Enter symptom: ");
        String symptom = sc.nextLine();

        if (symptom == null || symptom.trim().isEmpty()) {
            System.out.println("❌ Please enter a valid symptom");
            return;
        }

        // Reload doctors from CSV to ensure latest data
        try {
            var doctorRows = CSVUtil.readLines(DOCTORS_FILE);
            var allDoctors = doctorService.getAllDoctors();

            if (doctorRows.isEmpty() && allDoctors.isEmpty()) {
                System.out.println("❌ No doctors available in the system. Please add doctors first.");
                return;
            }

            // Use in-memory doctors if they exist, otherwise load from CSV
            var doctorsToSearch = allDoctors.isEmpty() ?
                loadDoctorsFromCSV(doctorRows) : allDoctors;

            Optional<Doctor> doctor = AIHelper.recommendDoctor(symptom, doctorsToSearch);

            doctor.ifPresentOrElse(
                    d -> {
                        System.out.println("👨‍⚕️ Recommended Doctor: " + d.getName());
                        System.out.println("🏥 Specialization: " + d.getSpecialization());
                        System.out.println("💰 Consultation Fee: Rs " + d.getConsultationFee());
                        System.out.println("⏰ Suggested Slot: " +
                                DateUtil.format(AIHelper.suggestAppointmentSlot()));
                    },
                    () -> System.out.println("⚠️ No suitable doctor found for symptom: " + symptom)
            );
        } catch (Exception e) {
            System.out.println("❌ Error during recommendation: " + e.getMessage());
        }
    }

    private static java.util.List<Doctor> loadDoctorsFromCSV(java.util.List<String[]> rows) {
        java.util.List<Doctor> doctors = new java.util.ArrayList<>();
        for (String[] row : rows) {
            if (row.length >= 7) {
                Doctor d = new Doctor(
                        row[0], row[1], row[2], row[3],
                        row[4],
                        row[5],
                        Double.parseDouble(row[6])
                );
                doctors.add(d);
            }
        }
        return doctors;
    }

    private static void saveAllData() {

        var doctorLines = doctorService.getAllDoctors().stream()
                .map(d -> d.getId() + "," + d.getName() + "," + d.getEmail() + "," +
                        d.getPhoneNumber() + "," + d.getSpecialization() + "," +
                        d.getLicenseNumber() + "," + d.getConsultationFee())
                .collect(java.util.stream.Collectors.toList());


        CSVUtil.writeLines(DOCTORS_FILE, doctorLines);

        var patientLines = patientService.getAllPatients().stream()
                .map(p -> p.getId() + "," + p.getName() + "," + p.getEmail() + "," +
                        p.getPhoneNumber() + "," + p.getMedicalHistory() + "," +
                        p.getBloodGroup())
                .collect(java.util.stream.Collectors.toList());


        CSVUtil.writeLines(PATIENTS_FILE, patientLines);

        var appointmentLines = appointmentService.getAllAppointments().stream()
                .map(a -> a.getAppointmentId() + "," +
                        a.getDoctor().getId() + "," +
                        a.getPatient().getId() + "," +
                        DateUtil.format(a.getAppointmentTime()) + "," +
                        a.getStatus())
                .collect(java.util.stream.Collectors.toList());


        CSVUtil.writeLines(APPOINTMENTS_FILE, appointmentLines);

        System.out.println("💾 Data saved successfully!");
    }

    private static void loadAllData() {
        try {
            var doctorRows = CSVUtil.readLines(DOCTORS_FILE);
            for (String[] row : doctorRows) {
                if (row.length >= 7) {
                    Doctor d = new Doctor(
                            row[0], row[1], row[2], row[3],
                            row[4],
                            row[5],
                            Double.parseDouble(row[6])
                    );
                    doctorService.addDoctor(d);
                }
            }

            var patientRows = CSVUtil.readLines(PATIENTS_FILE);
            for (String[] row : patientRows) {
                if (row.length >= 6) {
                    Patient p = new Patient(
                            row[0], row[1], row[2], row[3], row[4], row[5]
                    );
                    patientService.addPatient(p);
                }
            }

            System.out.println("📂 CSV data loaded (if present)");
        } catch (Exception e) {
            System.out.println("⚠️ Error loading CSV data: " + e.getMessage());
        }
    }
}

package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.constants.Constants;
import com.airtribe.meditrack.interfaces.Payable;

/**
 * Represents a bill for a completed appointment.
 */
public class Bill implements Payable {

    private String billId;
    private Appointment appointment;

    public Bill(String billId, Appointment appointment) {
        this.billId = billId;
        this.appointment = appointment;
    }

    @Override
    public double calculateAmount() {
        double baseAmount = appointment.getDoctor().getConsultationFee();
        double tax = baseAmount * Constants.TAX_RATE;
        return baseAmount + tax;
    }

    @Override
    public String generateReceipt() {
        return "Bill ID: " + billId +
                "\nDoctor: " + appointment.getDoctor().getName() +
                "\nPatient: " + appointment.getPatient().getName() +
                "\nAmount: ₹" + calculateAmount();
    }

    public BillSummary generateSummary() {
        double base = appointment.getDoctor().getConsultationFee();
        double tax = base * Constants.TAX_RATE;
        return new BillSummary(base, tax);
    }
}

package com.airtribe.meditrack.entity;

/**
 * Immutable summary of billing details.
 */
public final class BillSummary {

    private final double baseAmount;
    private final double taxAmount;
    private final double totalAmount;

    public BillSummary(double baseAmount, double taxAmount) {
        this.baseAmount = baseAmount;
        this.taxAmount = taxAmount;
        this.totalAmount = baseAmount + taxAmount;
    }

    public double getBaseAmount() {
        return baseAmount;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    @Override
    public String toString() {
        return "BillSummary{" +
                "baseAmount=" + baseAmount +
                ", taxAmount=" + taxAmount +
                ", totalAmount=" + totalAmount +
                '}';
    }
}

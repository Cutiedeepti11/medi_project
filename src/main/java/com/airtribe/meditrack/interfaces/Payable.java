package com.airtribe.meditrack.interfaces;

/**
 * Interface for payable entities in the system.
 */
public interface Payable {
    double calculateAmount();

    String generateReceipt();
}


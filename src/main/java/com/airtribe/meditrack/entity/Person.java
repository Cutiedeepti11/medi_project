package com.airtribe.meditrack.entity;

/**
 * Base class for all persons in the system.
 * Demonstrates inheritance and encapsulation.
 */
public class Person {
    private String id;
    private String name;
    private String email;
    private String phoneNumber;

    public Person(String id, String name, String email, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }


}
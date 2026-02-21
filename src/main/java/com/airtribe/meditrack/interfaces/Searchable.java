package com.airtribe.meditrack.interfaces;

/**
 * Interface for searchable entities in the system.
 */
public interface Searchable {
    boolean searchById(String id);

    boolean searchByName(String name);

}


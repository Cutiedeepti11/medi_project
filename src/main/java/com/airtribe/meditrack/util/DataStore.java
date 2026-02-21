package com.airtribe.meditrack.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Generic in-memory data storage.
 */
public class DataStore<T> {

    private final Map<String, T> store = new HashMap<>();

    public void add(String id, T entity) {
        store.put(id, entity);
    }

    public T get(String id) {
        return store.get(id);
    }

    public void remove(String id) {
        store.remove(id);
    }

    public Collection<T> getAll() {
        return store.values();
    }

    public boolean exists(String id) {
        return store.containsKey(id);
    }
}

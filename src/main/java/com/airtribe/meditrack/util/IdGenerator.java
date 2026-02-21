package com.airtribe.meditrack.util;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Singleton ID generator.
 */
public class IdGenerator {

    private static final IdGenerator instance = new IdGenerator();
    private final AtomicInteger counter = new AtomicInteger(1);

    private IdGenerator() {}

    public static IdGenerator getInstance() {
        return instance;
    }

    public String nextId() {
        return String.valueOf(counter.getAndIncrement());
    }
}

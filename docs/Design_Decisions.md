# Design Decisions

This document explains key design choices made in MediTrack.

## 1) Why DataStore
`DataStore<T>` provides a small, generic in-memory storage abstraction.
- Keeps add/get/list logic in one place.
- Promotes reuse across Doctor, Patient, and Appointment services.
- Simplifies future swap to database-backed storage.

## 2) Why Enums
Enums like `Specialization` and `AppointmentStatus`:
- Provide a fixed, validated set of values.
- Improve readability vs. raw strings.
- Reduce invalid or misspelled values in the system.

## 3) Why Immutable BillSummary
`BillSummary` is immutable to:
- Guarantee that billing outputs do not change after creation.
- Prevent accidental mutation in reporting or receipts.
- Make objects thread-safe if used in future concurrency.

## 4) Why Singleton (IdGenerator)
`IdGenerator` is a singleton to:
- Ensure unique IDs across the app lifecycle.
- Centralize ID generation logic.
- Avoid accidental duplicate IDs across services.

## 5) Why CSV Persistence
CSV files are used for persistence to:
- Keep the project lightweight without external databases.
- Make data easy to inspect and edit manually.
- Support quick backups and portability.

---

**Version**: 1.0  
**Last Updated**: February 2026

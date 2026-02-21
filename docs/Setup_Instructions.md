# Setup Instructions

This document explains how to install Java, compile the project, and run the MediTrack application.

## 1) Install and Configure Java (JDK)

### Windows
1. Download a JDK (e.g., Temurin 17 or Oracle JDK 17).
2. Run the installer and complete setup.
3. Set environment variables:
   - JAVA_HOME = JDK install path (example: C:\Program Files\Java\jdk-17)
   - Add %JAVA_HOME%\bin to Path
4. Verify:
```powershell
java -version
javac -version
```

Screenshot placeholders (add images to docs/screenshots and update links):
- JDK download page: docs/screenshots/jdk-download.png
- JAVA_HOME setup: docs/screenshots/java-home.png
- Path update: docs/screenshots/path-update.png
- Version check: docs/screenshots/java-version.png

### macOS
1. Install a JDK (Temurin or Oracle) and follow the installer.
2. Verify:
```bash
java -version
javac -version
```

### Linux (Ubuntu/Debian)
```bash
sudo apt update
sudo apt install openjdk-17-jdk
java -version
javac -version
```

## 2) Compile the Project

From the project root:

### PowerShell (Windows)
```powershell
cd "C:\Users\Ritu\OneDrive\Documents\AI First Software Engineering\Java Assignment - Sayantan Brahma\src"
Get-ChildItem -Path main/java -Filter *.java -Recurse | % { $_.FullName } | javac -d out @-
```

### Bash (macOS/Linux)
```bash
cd "<project-root>/src"
javac -d out $(find main/java -name "*.java")
```

## 3) Run the Application

### Normal Run
```powershell
cd "C:\Users\Ritu\OneDrive\Documents\AI First Software Engineering\Java Assignment - Sayantan Brahma\src"
java -cp out com.airtribe.meditrack.Main
```

### Run with CSV Load
```powershell
cd "C:\Users\Ritu\OneDrive\Documents\AI First Software Engineering\Java Assignment - Sayantan Brahma\src"
java -cp out com.airtribe.meditrack.Main --loadData
```

## 4) Data Files

The app reads/writes CSV files in the working directory:
- doctors.csv
- patients.csv
- appointments.csv

If you run from `src`, the files will be created in `src`.

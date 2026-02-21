# JVM Report

This document summarizes JVM concepts relevant to the MediTrack application.

## 1) Class Loader
The JVM uses class loaders to locate and load `.class` files into memory.
- Bootstrap ClassLoader: loads core Java classes (rt.jar or jmods).
- Extension/Platform ClassLoader: loads standard extensions.
- Application ClassLoader: loads classes from the classpath.

## 2) Runtime Data Areas
- Method Area: class metadata, constant pool, static variables.
- Heap: objects and arrays created at runtime.
- Java Stack: method frames, local variables, and return addresses.
- PC Register: current instruction pointer for each thread.
- Native Method Stack: used by JNI calls.

## 3) Execution Engine
The execution engine runs bytecode using:
- Interpreter: executes bytecode line by line.
- JIT Compiler: compiles hot methods into native machine code for speed.
- Garbage Collector: reclaims memory on the heap.

## 4) JIT Compiler vs Interpreter
- Interpreter: fast startup, slower execution for repeated code.
- JIT: slower startup, faster execution after compilation.
The JVM dynamically decides which methods to compile based on runtime profiling.

## 5) JVM Execution Flow
1. `javac` compiles source into bytecode (`.class`).
2. ClassLoader loads classes into the JVM.
3. Bytecode is verified for safety.
4. Execution Engine interprets or JIT-compiles the code.
5. Garbage Collector manages heap memory.

## 6) Write Once, Run Anywhere
Java bytecode runs on any JVM implementation, enabling portability across OSes and hardware.

## 7) Runtime Data Areas Summary (Quick Reference)
- Heap: objects
- Stack: method frames
- Method Area: class metadata
- PC Register: instruction pointer
- Native Stack: JNI calls


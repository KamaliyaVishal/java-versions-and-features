# Java 12

A comprehensive guide to all Java 12 concepts with practical examples for interview preparation.

## Table of Contents

1. [Switch Expressions (Preview)](#1-switch-expressions-preview)
2. [Shenandoah Garbage Collector](#2-shenandoah-garbage-collector)
3. [Microbenchmark Suite](#3-microbenchmark-suite)
4. [CDS Archive Improvements](#4-cds-archive-improvements)
5. [G1 GC Improvements](#5-g1-gc-improvements)
6. [JVM Constants API](#6-jvm-constants-api)
7. [Compact Number Formatting](#7-compact-number-formatting)
8. [String Methods Enhancements](#8-string-methods-enhancements)
9. [Files.mismatch() Method](#9-filesmismatch-method)
10. [Common Interview Questions](#10-common-interview-questions)

---

## 1. Switch Expressions (Preview)

Switch expressions allow switch to be used as an expression that returns a value.

### Traditional Switch Statement

```java
String day = "MONDAY";
int numLetters;

switch (day) {
    case "MONDAY":
    case "FRIDAY":
    case "SUNDAY":
        numLetters = 6;
        break;
    case "TUESDAY":
        numLetters = 7;
        break;
    case "WEDNESDAY":
    case "THURSDAY":
    case "SATURDAY":
        numLetters = 9;
        break;
    default:
        numLetters = -1;
}
```

### Switch Expression (Preview)

```java
// Switch as expression
int numLetters = switch (day) {
    case "MONDAY", "FRIDAY", "SUNDAY" -> 6;
    case "TUESDAY" -> 7;
    case "WEDNESDAY", "THURSDAY", "SATURDAY" -> 9;
    default -> -1;
};

// With arrow syntax (no break needed)
String type = switch (value) {
    case 1 -> "One";
    case 2 -> "Two";
    case 3 -> "Three";
    default -> "Unknown";
};

// Yield keyword for complex cases
int result = switch (day) {
    case "MONDAY", "FRIDAY" -> {
        System.out.println("Weekday");
        yield 6;  // Use yield to return value
    }
    case "WEDNESDAY" -> 9;
    default -> -1;
};
```

### Benefits

- No fall-through (eliminates break bugs)
- Can return values
- More concise syntax
- Exhaustive (must handle all cases or have default)

---

## 2. Shenandoah Garbage Collector

Low-pause-time garbage collector for large heaps.

### Usage

```bash
# Enable Shenandoah GC
java -XX:+UnlockExperimentalVMOptions -XX:+UseShenandoahGC MainClass

# Benefits:
# - Low pause times (independent of heap size)
# - Good for large heaps (multi-gigabyte)
# - Concurrent evacuation (no stop-the-world pauses)
```

---

## 3. Microbenchmark Suite

Java Microbenchmark Harness (JMH) now part of JDK.

### Overview

JMH provides a framework for writing, running, and analyzing nano/micro/milli/macro benchmarks.

### Basic Usage

```java
import org.openjdk.jmh.annotations.*;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class MyBenchmark {
    
    @Benchmark
    public void testMethod() {
        // Code to benchmark
    }
}
```

### Benefits

- Accurate microbenchmarking
- Handles JVM warmup
- Statistical analysis
- Part of JDK (no external dependency)

---

## 4. CDS Archive Improvements

Class-Data Sharing improvements for faster startup.

### Default CDS Archives

Java 12 automatically generates a default CDS archive during JDK build.

```bash
# Use default CDS archive (automatic)
java -Xshare:auto -jar app.jar

# Explicitly use CDS
java -Xshare:on -jar app.jar

# Disable CDS
java -Xshare:off -jar app.jar
```

### Benefits

- Faster application startup
- Reduced memory footprint
- Automatic (no manual setup needed)
- Shared class metadata across JVM instances

### Usage

```bash
# Check CDS status
java -Xshare:auto -version

# Create custom CDS archive
java -Xshare:dump -jar app.jar
```

---

## 5. G1 GC Improvements

Improvements to G1 garbage collector.

### 5.1 Abortable Mixed Collections

G1 can abort mixed collections if they exceed pause time target.

```bash
# Enable abortable mixed collections
java -XX:+UseG1GC \
     -XX:G1MixedGCLiveThresholdPercent=85 \
     -XX:G1HeapWastePercent=5 \
     -jar app.jar
```

### 5.2 Promptly Return Unused Committed Memory

G1 can automatically return unused heap memory to the OS.

```bash
# Enable automatic memory return
java -XX:+UseG1GC \
     -XX:G1PeriodicGCInterval=2000 \
     -XX:MaxGCPauseMillis=200 \
     -jar app.jar
```

### Benefits

- Better pause-time predictability
- Reduced memory footprint
- More efficient memory usage
- Automatic memory management

---

## 6. JVM Constants API

API to model nominal descriptions of key class-file and runtime artifacts, particularly constants loadable from the constant pool.

### Overview

The Constants API provides a way to describe constants that can be loaded from the constant pool, useful for tools and frameworks.

```java
import java.lang.constant.*;
import java.lang.invoke.MethodHandles;

// String constant
StringDesc stringDesc = StringDesc.of("Hello, Java 12!");
String resolved = stringDesc.resolveConstantDesc(MethodHandles.lookup());

// Class constant
ClassDesc classDesc = ClassDesc.of("java.lang.String");
```

**Note:** For detailed documentation and examples, see [JVM Constants API Guide](constants/JVMConstantsAPIGuide.md).

### Use Cases

- Code generation tools
- Bytecode manipulation frameworks
- Dynamic class creation
- Reflection improvements

---

## 7. Compact Number Formatting

Support for compact number formatting, allowing numbers to be formatted in a more human-readable form.

### Basic Usage

```java
import java.text.NumberFormat;
import java.util.Locale;

// Short format
NumberFormat shortFormat = NumberFormat.getCompactNumberInstance(
    Locale.US, NumberFormat.Style.SHORT);
System.out.println(shortFormat.format(1000));  // "1K"
System.out.println(shortFormat.format(1000000));  // "1M"

// Long format
NumberFormat longFormat = NumberFormat.getCompactNumberInstance(
    Locale.US, NumberFormat.Style.LONG);
System.out.println(longFormat.format(1000));  // "1 thousand"
System.out.println(longFormat.format(1000000));  // "1 million"
```

### Benefits

- Human-readable number formatting
- Locale-aware
- Short and long formats
- Useful for UI displays

---

## 8. String Methods Enhancements

New methods added to String class.

### indent()

Adds or removes indentation from each line.

```java
String text = "Line 1\nLine 2\nLine 3";
String indented = text.indent(4);  // Add 4 spaces
String unindented = text.indent(-2);  // Remove 2 spaces
```

### transform()

Applies a function to the string and returns the result.

```java
String text = "Hello, World!";
String upper = text.transform(String::toUpperCase);
String reversed = text.transform(s -> new StringBuilder(s).reverse().toString());
```

### Benefits

- More functional string operations
- Chainable transformations
- Convenient indentation control

---

## 9. Files.mismatch() Method

New utility method to efficiently compare two files for content differences.

### Basic Usage

```java
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

Path file1 = Paths.get("file1.txt");
Path file2 = Paths.get("file2.txt");

long mismatch = Files.mismatch(file1, file2);
if (mismatch == -1) {
    System.out.println("Files are identical");
} else {
    System.out.println("Files differ at byte position: " + mismatch);
}
```

### Benefits

- Efficient file comparison
- Returns first differing byte position
- Returns -1 if files are identical
- Useful for file synchronization and validation

---

## 10. Common Interview Questions

### Q1: What are Switch Expressions and why were they introduced?
**A:** Switch expressions allow switch to return values:
- Eliminates fall-through bugs (no break needed)
- More concise syntax with arrow notation
- Can be used as expressions
- Better readability

### Q2: What's the difference between switch statement and switch expression?
**A:**
- **Statement**: Executes code, no return value
- **Expression**: Returns a value, can be assigned
- Expression requires all cases to return values or have default
- Expression uses arrow syntax (`->`) or `yield` keyword

---

**Last Updated:** 2024  
**Version:** 1.0


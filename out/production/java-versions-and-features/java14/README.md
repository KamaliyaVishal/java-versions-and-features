# Java 14

A comprehensive guide to all Java 14 concepts with practical examples for interview preparation.

## Table of Contents

1. [Switch Expressions (Finalized)](#1-switch-expressions-finalized)
2. [Pattern Matching for instanceof (Preview)](#2-pattern-matching-for-instanceof-preview)
3. [Records (Preview)](#3-records-preview)
4. [Text Blocks (Second Preview)](#4-text-blocks-second-preview)
5. [Helpful NullPointerExceptions](#5-helpful-nullpointerexceptions)
6. [Packaging Tool (jpackage)](#6-packaging-tool-jpackage)
7. [Foreign-Memory Access API (Incubator)](#7-foreign-memory-access-api-incubator)
8. [G1 GC Improvements](#8-g1-gc-improvements)
9. [NUMA-Aware Memory Allocation](#9-numa-aware-memory-allocation)
10. [Common Interview Questions](#10-common-interview-questions)

---

## 1. Switch Expressions (Finalized)

Switch expressions are now a standard feature (no longer preview).

### Final Syntax

```java
// Arrow syntax (preferred)
int numLetters = switch (day) {
    case "MONDAY", "FRIDAY", "SUNDAY" -> 6;
    case "TUESDAY" -> 7;
    case "WEDNESDAY", "THURSDAY", "SATURDAY" -> 9;
    default -> throw new IllegalArgumentException("Invalid day");
};

// Block syntax with yield
int result = switch (value) {
    case 1 -> 10;
    case 2 -> 20;
    case 3 -> {
        System.out.println("Three");
        yield 30;
    }
    default -> 0;
};

// Enums
enum Day { MONDAY, TUESDAY, WEDNESDAY }
String type = switch (day) {
    case MONDAY, FRIDAY -> "Weekday";
    case SATURDAY, SUNDAY -> "Weekend";
    default -> "Unknown";
};
```

---

## 2. Pattern Matching for instanceof (Preview)

Simplified instanceof checks with automatic casting.

### Traditional Way

```java
Object obj = "Hello";

if (obj instanceof String) {
    String str = (String) obj;  // Manual cast
    System.out.println(str.toUpperCase());
}
```

### Pattern Matching (Preview)

```java
Object obj = "Hello";

// Pattern matching - automatic casting
if (obj instanceof String str) {
    // str is automatically cast and available in this scope
    System.out.println(str.toUpperCase());
}

// With negation
if (!(obj instanceof String str)) {
    System.out.println("Not a string");
} else {
    // str is available here too
    System.out.println(str.length());
}

// Complex example
if (obj instanceof String str && str.length() > 5) {
    System.out.println("Long string: " + str);
}
```

### Benefits

- Eliminates manual casting
- Reduces boilerplate
- Prevents cast errors
- Improves readability

---

## 3. Records (Preview)

Immutable data carrier classes.

### Basic Record

```java
// Traditional class
public class Person {
    private final String name;
    private final int age;
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public String getName() { return name; }
    public int getAge() { return age; }
    
    @Override
    public boolean equals(Object o) { ... }
    @Override
    public int hashCode() { ... }
    @Override
    public String toString() { ... }
}

// Record (much simpler!)
public record Person(String name, int age) {}

// Usage
Person person = new Person("John", 30);
System.out.println(person.name());  // Accessor methods
System.out.println(person.age());
System.out.println(person);  // Automatic toString
```

### Record with Methods

```java
public record Person(String name, int age) {
    // Compact constructor (validation)
    public Person {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
    }
    
    // Additional methods
    public boolean isAdult() {
        return age >= 18;
    }
    
    // Static methods
    public static Person of(String name, int age) {
        return new Person(name, age);
    }
}
```

### Record with Validation

```java
public record Email(String address) {
    public Email {
        // Compact constructor - validates before assignment
        if (address == null || !address.contains("@")) {
            throw new IllegalArgumentException("Invalid email");
        }
        // Fields are automatically assigned after this block
    }
}
```

### Record Characteristics

- Immutable by default
- All fields are final
- Automatic equals(), hashCode(), toString()
- Accessor methods (no "get" prefix)
- Can have constructors, methods, static methods
- Cannot extend other classes (implicitly extends Record)
- Can implement interfaces

---

## 4. Text Blocks (Second Preview)

Refinements to text blocks.

```java
// Same as Java 13, with improvements
String html = """
              <html>
                  <body>
                      <p>Hello</p>
                  </body>
              </html>
              """;

// New escape sequences
String text = """
              Escaped: \"quote\"
              Escaped: \\backslash
              """;
```

---

## 5. Helpful NullPointerExceptions

Improved error messages for NullPointerException.

```java
// Before Java 14
int[] arr = null;
arr.length;  // NullPointerException (not helpful)

// Java 14
int[] arr = null;
arr.length;  
// Exception: Cannot read the array length because "arr" is null

// More examples
class Person {
    Address address;
}
class Address {
    String city;
}

Person person = new Person();
person.address.city;  
// Exception: Cannot read field "city" because "person.address" is null
```

### Benefits

- Clear indication of what was null
- Shows the full chain of method calls/field accesses
- Easier debugging
- No code changes needed

---

## 6. Packaging Tool (jpackage)

Tool for creating native application installers and packages.

### Overview

`jpackage` is an incubator tool that packages Java applications into platform-specific formats, making distribution easier.

### Basic Usage

```bash
# Create Windows installer (MSI)
jpackage --input target \
         --name MyApp \
         --main-jar app.jar \
         --main-class com.example.Main \
         --type msi

# Create Linux package (DEB)
jpackage --input target \
         --name MyApp \
         --main-jar app.jar \
         --type deb

# Create Mac installer (DMG)
jpackage --input target \
         --name MyApp \
         --main-jar app.jar \
         --type dmg
```

### Advanced Options

```bash
# With JRE bundling
jpackage --input target \
         --name MyApp \
         --main-jar app.jar \
         --type msi \
         --java-options "-Xmx512m" \
         --app-version 1.0.0 \
         --description "My Application" \
         --vendor "My Company"
```

### Supported Formats

- **Windows**: MSI, EXE
- **Linux**: DEB, RPM
- **macOS**: DMG, PKG

### Benefits

- Native installers for each platform
- Bundles JRE automatically
- Professional application distribution
- No manual JRE installation needed
- Cross-platform support

---

## 7. Foreign-Memory Access API (Incubator)

API for safely accessing memory outside the Java heap.

### Overview

The Foreign-Memory Access API provides a way to access native memory safely and efficiently, useful for interop with native libraries.

### Basic Usage

```java
import jdk.incubator.foreign.*;

// Allocate native memory
try (MemorySegment segment = MemorySegment.allocateNative(100)) {
    // Access memory
    MemoryAccess.setIntAtOffset(segment, 0, 42);
    int value = MemoryAccess.getIntAtOffset(segment, 0);
    System.out.println(value); // 42
}
```

### Features

- Safe memory access
- Automatic resource management
- Type-safe operations
- Useful for native library interop

### Use Cases

- Interfacing with native libraries
- High-performance memory operations
- System-level programming
- Zero-copy operations

**Note:** This is an incubator feature and requires `--add-modules jdk.incubator.foreign` and `--enable-preview` flags.

For detailed documentation and examples, see [Foreign-Memory Access API Guide](foreignmemory/ForeignMemoryAccessAPIGuide.md).

---

## 8. G1 GC Improvements

G1 Garbage Collector enhancements for better performance.

### Parallel Full GC

G1 now uses parallel processing for Full GC operations, reducing pause times.

### Faster Large Object Allocation

Improved allocation performance for large objects in G1.

### Configuration

```bash
# Enable G1 GC
java -XX:+UseG1GC \
     -XX:MaxGCPauseMillis=200 \
     -cp myApp.jar com.example.Main
```

### Benefits

- Reduced Full GC pause times
- Better performance for large objects
- Improved overall GC efficiency
- Better throughput

---

## 9. NUMA-Aware Memory Allocation

Non-Uniform Memory Access improvements for better performance on NUMA systems.

### Overview

NUMA-aware memory allocation improves performance on systems with Non-Uniform Memory Access architecture by allocating memory closer to the CPU that will use it.

### Benefits

- Better memory locality
- Reduced memory access latency
- Improved performance on NUMA systems
- Automatic optimization

### Use Cases

- Multi-socket servers
- High-performance computing
- Large-scale applications
- Memory-intensive workloads

### Configuration

```bash
# Enable NUMA-aware allocation
java -XX:+UseNUMA \
     -cp myApp.jar com.example.Main
```

---

## 10. Common Interview Questions

### Q1: What are Records and when should you use them?
**A:** Records are immutable data carriers:
- Use for: DTOs, value objects, data transfer objects
- Benefits: Less boilerplate, automatic equals/hashCode/toString
- Cannot extend other classes, can implement interfaces

### Q2: How does Pattern Matching for instanceof work?
**A:** Automatically casts and binds variable:
- `if (obj instanceof String str)` binds `str` to the cast value
- Variable is scoped to the if block
- Eliminates manual casting

### Q3: What are the characteristics of Records?
**A:**
- Immutable
- All fields final
- Automatic equals, hashCode, toString
- Accessor methods (fieldName() not getFieldName())
- Can have constructors, methods, static methods
- Cannot extend classes

---

**Last Updated:** 2024  
**Version:** 1.0


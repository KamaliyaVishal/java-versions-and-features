# Java 16

A comprehensive guide to all Java 16 concepts with practical examples for interview preparation.

## Table of Contents

1. [Records (Finalized)](#1-records-finalized)
2. [Pattern Matching for instanceof (Finalized)](#2-pattern-matching-for-instanceof-finalized)
3. [Sealed Classes (Second Preview)](#3-sealed-classes-second-preview)
4. [Stream API Enhancements](#4-stream-api-enhancements)
5. [Packaging Tool (jpackage)](#5-packaging-tool-jpackage)
6. [Vector API (Incubator)](#6-vector-api-incubator)
7. [Foreign Linker API (Incubator)](#7-foreign-linker-api-incubator)
8. [Foreign-Memory Access API (Third Incubator)](#8-foreign-memory-access-api-third-incubator)
9. [Unix-Domain Socket Channels](#9-unix-domain-socket-channels)
10. [ZGC Concurrent Thread Stack Processing](#10-zgc-concurrent-thread-stack-processing)
11. [Elastic Metaspace](#11-elastic-metaspace)
12. [Alpine Linux Port](#12-alpine-linux-port)
13. [Common Interview Questions](#13-common-interview-questions)

---

## 1. Records (Finalized)

Records are now a standard feature of Java.

### Finalized Records

```java
// Records are now standard (no longer preview)
public record Person(String name, int age) {
    // Compact constructor with validation
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

// Usage
Person person = new Person("John", 30);
System.out.println(person.name());  // Accessor
System.out.println(person.age());
System.out.println(person);  // Automatic toString

// Records are immutable
// person.name() = "Jane";  // Compile error
```

### Record Patterns (Future)

```java
// Pattern matching with records (comes later)
// Switch on record
```

---

## 2. Pattern Matching for instanceof (Finalized)

Pattern matching for instanceof is now a standard feature.

### Finalized Syntax

```java
Object obj = "Hello";

// Pattern matching - automatic casting
if (obj instanceof String str) {
    // str is automatically available, no cast needed
    System.out.println(str.toUpperCase());
    System.out.println(str.length());
}

// With complex conditions
if (obj instanceof String str && str.length() > 5) {
    System.out.println("Long string: " + str);
}

// In switch (when used with sealed classes)
sealed interface Animal permits Dog, Cat {}

if (animal instanceof Dog dog) {
    dog.bark();
} else if (animal instanceof Cat cat) {
    cat.meow();
}
```

### Benefits

- Eliminates manual casting
- Reduces boilerplate
- Prevents ClassCastException
- More readable code

---

## 3. Sealed Classes (Second Preview)

Refinements to sealed classes with improved pattern matching support.

### Overview

Sealed classes allow the author of a class or interface to control which code can extend it, enabling exhaustive pattern matching.

### Basic Usage

```java
public sealed class Shape 
    permits Circle, Rectangle, Triangle {
    public abstract double area();
}

public final class Circle extends Shape {
    private final double radius;
    
    public Circle(double radius) {
        this.radius = radius;
    }
    
    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
}

// Pattern matching with sealed classes (using instanceof)
String result;
if (shape instanceof Circle c) {
    result = "Circle with radius " + c.radius();
} else if (shape instanceof Rectangle r) {
    result = "Rectangle " + r.width() + "x" + r.height();
} else if (shape instanceof Triangle t) {
    result = "Triangle with base " + t.base();
}
```

### Key Features

- **Controlled inheritance**: Only permitted classes can extend
- **Exhaustive pattern matching**: Compiler knows all possible types
- **Non-sealed support**: Allows further extension
- **Sealed interfaces**: Can also seal interfaces

### Benefits

- Better API design (restricted inheritance)
- Exhaustive pattern matching
- Improved type safety
- Document intended class hierarchy

**Note:** Requires `--enable-preview` flag. See [SealedClassesDemo.java](sealedclasses/SealedClassesDemo.java) for complete example.

---

## 4. Stream API Enhancements

Java 16 adds the `toList()` method to the Stream API for collecting elements into an unmodifiable list.

### Overview

The `toList()` method is a convenient terminal operation that collects stream elements into an unmodifiable list, replacing the need for `collect(Collectors.toList())`.

### Basic Usage

```java
import java.util.List;
import java.util.stream.Stream;

// Simple usage
List<String> names = Stream.of("Alice", "Bob", "Charlie")
    .filter(name -> name.length() > 3)
    .toList();  // Returns unmodifiable list

// Comparison with traditional approach
// Old way (Java 8-15)
List<String> oldWay = stream.collect(Collectors.toList());

// New way (Java 16+)
List<String> newWay = stream.toList();
```

### Key Characteristics

- **Unmodifiable**: The returned list is unmodifiable (cannot add, remove, or modify)
- **Convenient**: Shorter syntax than `collect(Collectors.toList())`
- **Null-safe**: Handles null elements correctly
- **Type-safe**: Returns `List<T>` directly

### Examples

```java
// Filter and collect
List<Integer> numbers = Stream.of(1, 2, 3, 4, 5)
    .filter(n -> n % 2 == 0)
    .toList();

// Map and collect
List<String> upperCase = Stream.of("hello", "world")
    .map(String::toUpperCase)
    .toList();

// Complex pipeline
List<String> result = Stream.of("apple", "banana", "cherry")
    .filter(s -> s.length() > 5)
    .map(String::toUpperCase)
    .sorted()
    .toList();
```

### Benefits

- Cleaner, more readable code
- Less boilerplate
- Returns unmodifiable list (safer)
- Standard method (no need for Collectors import)

### When to Use

- When you need an unmodifiable list
- For simple stream-to-list conversions
- When you don't need a mutable list

**Note:** For a mutable list, still use `collect(Collectors.toCollection(ArrayList::new))`.

See [StreamToListDemo.java](streams/StreamToListDemo.java) for complete examples.

---

## 5. Packaging Tool (jpackage)

jpackage is now production-ready (no longer incubator).

### Overview

The `jpackage` tool creates native application installers and packages for Java applications, bundling the JRE with the application.

### Basic Usage

```bash
# Create application image (directory)
jpackage --input target \
         --name MyApp \
         --main-jar app.jar \
         --type app-image

# Create platform-specific installer
jpackage --input target \
         --name MyApp \
         --main-jar app.jar \
         --type msi  # Windows MSI installer

# Create with main class
jpackage --input target \
         --name MyApp \
         --main-class com.example.Main \
         --main-jar app.jar \
         --type app-image
```

### Package Types

- **app-image**: Directory containing application and JRE
- **msi**: Windows MSI installer
- **exe**: Windows EXE installer
- **pkg**: macOS package installer
- **dmg**: macOS disk image
- **deb**: Debian package (Linux)
- **rpm**: RPM package (Linux)

### Common Options

```bash
# Basic options
--input <dir>          # Input directory containing JAR files
--name <name>          # Application name
--main-jar <jar>       # Main JAR file
--main-class <class>   # Main class (if not in manifest)
--type <type>          # Package type (app-image, msi, exe, etc.)

# JRE options
--java-options <opts>  # JVM options
--jlink-options <opts> # jlink options

# Application info
--app-version <version> # Application version
--description <desc>    # Application description
--vendor <vendor>       # Vendor name
--copyright <text>      # Copyright text
--license-file <file>   # License file

# Icons and resources
--icon <icon>          # Application icon
--resource-dir <dir>   # Additional resources
```

### Examples

```bash
# Create Windows MSI installer
jpackage --input target \
         --name MyApplication \
         --main-jar myapp.jar \
         --type msi \
         --app-version 1.0.0 \
         --description "My Java Application" \
         --vendor "My Company"

# Create macOS DMG
jpackage --input target \
         --name MyApplication \
         --main-jar myapp.jar \
         --type dmg \
         --icon app.icns

# Create Linux DEB package
jpackage --input target \
         --name myapp \
         --main-jar myapp.jar \
         --type deb \
         --app-version 1.0.0
```

### Benefits

- **Native installers**: Create platform-specific installers
- **Bundled JRE**: Include JRE with application
- **Cross-platform**: Works on Windows, macOS, Linux
- **Production-ready**: No longer experimental
- **User-friendly**: Standard installation experience

### Use Cases

- Desktop applications
- Standalone tools
- Distribution to end users
- Applications requiring bundled JRE
- Cross-platform deployment

### Requirements

- JDK 16 or later
- Platform-specific tools (e.g., WiX for Windows MSI)
- Appropriate permissions for creating installers

---

## 6. Vector API (Incubator)

API for expressing vector computations that compile to optimal vector instructions.

### Overview

The Vector API provides SIMD-style operations for parallel processing of arrays, with hardware-optimized computations.

### Basic Usage

```java
import jdk.incubator.vector.*;

// Define vector species (size)
VectorSpecies<Float> SPECIES = FloatVector.SPECIES_PREFERRED;

// Arrays to process
float[] a = {1.0f, 2.0f, 3.0f, 4.0f};
float[] b = {5.0f, 6.0f, 7.0f, 8.0f};
float[] c = new float[4];

// Load vectors from arrays
FloatVector va = FloatVector.fromArray(SPECIES, a, 0);
FloatVector vb = FloatVector.fromArray(SPECIES, b, 0);

// Perform vector operation (add)
FloatVector vc = va.add(vb);

// Store result back to array
vc.intoArray(c, 0);
// Result: c = [6.0f, 8.0f, 10.0f, 12.0f]
```

### Features

- **Hardware-agnostic**: Works on different platforms
- **Automatic optimization**: Compiles to optimal instructions
- **Type-safe**: Supports int, long, float, double
- **SIMD operations**: Parallel processing of multiple elements

### Use Cases

- Scientific computing
- Machine learning
- Image processing
- Signal processing
- Numerical simulations

### Benefits

- Better performance than scalar operations
- Parallel processing of multiple elements
- Hardware-optimized instructions
- Type-safe and platform-independent

**Note:** Requires `--add-modules jdk.incubator.vector` and `--enable-preview` flags. See [VectorAPIDemo.java](vector/VectorAPIDemo.java) for more details.

---

## 7. Foreign Linker API (Incubator)

API for linking with native code.

```java
// Foundation for foreign function interfaces
// Enables calling native code from Java
```

---

## 8. Foreign-Memory Access API (Third Incubator)

API for accessing memory outside the heap.

```java
import jdk.incubator.foreign.*;

// Access off-heap memory
// Useful for interop with native libraries
```

---

## 9. Unix-Domain Socket Channels

Support for Unix-domain sockets in Java NIO.

### Overview

Unix-domain socket channels provide inter-process communication (IPC) on Unix-like systems using the file system namespace instead of network ports.

### Basic Usage

```java
import java.net.UnixDomainSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.ByteBuffer;
import java.nio.channels.StandardProtocolFamily;

// Server side
ServerSocketChannel server = ServerSocketChannel.open(StandardProtocolFamily.UNIX);
UnixDomainSocketAddress address = UnixDomainSocketAddress.of("/tmp/mysocket");
server.bind(address);

// Client side
SocketChannel client = SocketChannel.open(StandardProtocolFamily.UNIX);
client.connect(address);

// Send data
ByteBuffer buffer = ByteBuffer.wrap("Hello".getBytes());
client.write(buffer);

// Receive data
ByteBuffer readBuffer = ByteBuffer.allocate(1024);
client.read(readBuffer);
```

### Features

- **File-based addressing**: Uses file system paths instead of ports
- **Local communication**: Only works on the same machine
- **Efficient**: Lower overhead than TCP/IP for local communication
- **Secure**: File system permissions control access

### Use Cases

- Inter-process communication on Unix systems
- Docker container communication
- Microservices on same host
- Application-to-daemon communication

### Benefits

- Lower latency than TCP/IP
- Simpler addressing (file paths)
- Better security (file permissions)
- Native Unix support

**Note:** Only works on Unix-like systems (Linux, macOS). See [UnixDomainSocketDemo.java](unixsocket/UnixDomainSocketDemo.java) for complete example.

---

## 10. ZGC Concurrent Thread Stack Processing

ZGC improvements for concurrent processing.

- Better performance
- Lower latency
- Concurrent thread stack scanning

---

## 11. Elastic Metaspace

Improved memory management for class metadata.

### Overview

Elastic Metaspace improves memory management by returning unused class metadata memory to the operating system more promptly, reducing the metaspace footprint.

### Key Features

- **Automatic memory return**: Unused metadata memory is returned to OS promptly
- **Reduced footprint**: Lower memory usage for class metadata
- **Better resource management**: More efficient memory utilization
- **No code changes required**: Automatic improvement

### How It Works

- Metaspace memory is divided into chunks
- When classes are unloaded, chunks are returned to OS
- Reduces long-term metaspace growth
- Better memory efficiency

### Benefits

- Lower memory footprint
- Better resource utilization
- Automatic optimization
- No application code changes needed

### Configuration

```bash
# Metaspace size (if needed)
-XX:MetaspaceSize=256m
-XX:MaxMetaspaceSize=512m
```

### Use Cases

- Applications with dynamic class loading
- Long-running applications
- Applications with many class loaders
- Memory-constrained environments

**Note:** This is an internal JVM improvement and doesn't require code changes.

---

## 12. Alpine Linux Port

Official port to Alpine Linux.

- Smaller Docker images
- Better containerization support
- Musl-based implementation

---

## 13. Common Interview Questions

### Q1: What are the key characteristics of Records?
**A:**
- Immutable by default
- All fields are final
- Automatic equals(), hashCode(), toString()
- Accessor methods (fieldName(), not getFieldName())
- Can have constructors, methods, static methods
- Cannot extend classes (implicitly extends Record)
- Can implement interfaces

### Q2: How does Pattern Matching for instanceof work?
**A:** Automatically casts and binds variable:
- `if (obj instanceof String str)` binds `str` to cast value
- Variable is scoped to the if block
- Eliminates manual casting
- Prevents ClassCastException

### Q3: What's the difference between Records and Classes?
**A:**
- Records: Immutable data carriers, automatic equals/hashCode/toString
- Classes: Full flexibility, mutable or immutable
- Use Records for: DTOs, value objects, data transfer
- Use Classes for: Full object-oriented design

---

**Last Updated:** 2024  
**Version:** 1.0


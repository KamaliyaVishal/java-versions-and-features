# Java 11 (LTS)

A comprehensive guide to all Java 11 concepts with practical examples for interview preparation.

## Table of Contents

1. [HTTP Client (Standardized)](#1-http-client-standardized)
2. [Files Methods Enhancements](#2-files-methods-enhancements)
3. [String Methods Enhancements](#3-string-methods-enhancements)
4. [Optional.isEmpty()](#4-optionalisempty)
5. [Predicate.not()](#5-predicatenot)
6. [Local-Variable Syntax for Lambda Parameters](#6-local-variable-syntax-for-lambda-parameters)
7. [Nest-Based Access Control](#7-nest-based-access-control)
8. [Java Flight Recorder (JFR)](#8-java-flight-recorder-jfr)
9. [Epsilon Garbage Collector](#9-epsilon-garbage-collector)
10. [ZGC (Experimental)](#10-zgc-experimental)
11. [Removed Java EE and CORBA Modules](#11-removed-java-ee-and-corba-modules)
12. [Common Interview Questions](#12-common-interview-questions)

---

## 1. HTTP Client (Standardized)

The HTTP Client API is now a standard feature in Java 11 (previously in Java 9 as incubator).

### Overview

The HTTP Client provides a modern, easy-to-use API for HTTP requests with support for HTTP/2 and WebSocket.

### Basic Usage

```java
import java.net.http.*;

HttpClient client = HttpClient.newHttpClient();
HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("https://api.example.com/data"))
    .build();

HttpResponse<String> response = client.send(request, 
    HttpResponse.BodyHandlers.ofString());
System.out.println(response.body());
```

### Key Features

- HTTP/1.1 and HTTP/2 support
- Asynchronous and synchronous requests
- WebSocket support
- Better than HttpURLConnection
- Modern API design

**See [HttpClientStandard.java](httpclient/HttpClientStandard.java) for complete example.**

---

## 2. Files Methods Enhancements

New convenience methods for reading and writing files.

### Overview

Java 11 adds `readString()` and `writeString()` methods to the `Files` class for simplified file I/O.

### Basic Usage

```java
import java.nio.file.*;

// Read entire file as string
String content = Files.readString(Paths.get("file.txt"));

// Write string to file
Files.writeString(Paths.get("output.txt"), "Hello, Java 11!");

// With charset
String content = Files.readString(Paths.get("file.txt"), 
    StandardCharsets.UTF_8);
```

### Key Features

- Simplified file reading
- Simplified file writing
- Automatic charset handling
- Less boilerplate code

**See [FilesMethods.java](files/FilesMethods.java) for complete example.**

---

## 3. String Methods Enhancements

New methods for string manipulation and validation.

### Overview

Java 11 adds several useful methods to the `String` class: `isBlank()`, `lines()`, `strip()`, `stripLeading()`, `stripTrailing()`, and `repeat()`.

### Basic Usage

```java
// isBlank() - checks if string is blank (whitespace only)
String str = "   ";
boolean blank = str.isBlank(); // true

// lines() - returns stream of lines
String text = "Line 1\nLine 2\nLine 3";
text.lines().forEach(System.out::println);

// strip() - removes leading and trailing whitespace
String s = "  hello  ";
String stripped = s.strip(); // "hello"

// repeat() - repeats string
String repeated = "Java ".repeat(3); // "Java Java Java "
```

### Key Features

- `isBlank()`: Better than checking `trim().isEmpty()`
- `lines()`: Stream of lines from multiline string
- `strip()`: Unicode-aware trim
- `repeat()`: Repeat string N times

**See [StringMethods.java](strings/StringMethods.java) for complete example.**

---

## 4. Optional.isEmpty()

A more readable way to check if an Optional is empty.

### Overview

`isEmpty()` provides a clearer alternative to `!isPresent()`.

### Basic Usage

```java
Optional<String> optional = Optional.empty();

// Before Java 11
if (!optional.isPresent()) {
    // handle empty
}

// Java 11+
if (optional.isEmpty()) {
    // handle empty - more readable
}
```

### Benefits

- More readable code
- Clearer intent
- Better than `!isPresent()`

**See [OptionalIsEmpty.java](optional/OptionalIsEmpty.java) for complete example.**

---

## 5. Predicate.not()

A static method to negate predicates.

### Overview

`Predicate.not()` provides a cleaner way to negate predicates in streams.

### Basic Usage

```java
List<String> names = Arrays.asList("Alice", "", "Bob", "  ");

// Filter non-blank strings
List<String> nonBlank = names.stream()
    .filter(Predicate.not(String::isBlank))
    .collect(Collectors.toList());
```

### Benefits

- Cleaner than `filter(s -> !s.isBlank())`
- Method reference support
- More readable

**See [PredicateNot.java](predicate/PredicateNot.java) for complete example.**

---

## 6. Local-Variable Syntax for Lambda Parameters

The `var` keyword can now be used in lambda parameters.

### Overview

Java 11 allows using `var` in lambda parameters for consistency with local variables.

### Basic Usage

```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

// Using var in lambda
names.forEach((var name) -> System.out.println(name));

// With annotations
names.forEach((@NonNull var name) -> System.out.println(name));
```

### Benefits

- Consistency with local variables
- Allows annotations on lambda parameters
- Type inference still works

**See [VarInLambda.java](lambda/VarInLambda.java) for complete example.**

---

## 7. Nest-Based Access Control

Improved access control for nested classes.

### Overview

Nest-based access control allows classes in the same nest to access each other's private members without synthetic bridge methods.

### Benefits

- Better performance (no synthetic methods)
- Cleaner bytecode
- More efficient reflection
- Better encapsulation

**See [NestBasedAccessControl.java](nestbased/NestBasedAccessControl.java) for complete example.**

---

## 8. Java Flight Recorder (JFR)

JFR is now open-source and available in OpenJDK.

### Overview

Java Flight Recorder provides low-overhead profiling and monitoring capabilities.

### Key Features

- Low overhead profiling
- Production-ready monitoring
- Event collection
- Performance analysis

**See [JavaFlightRecorderDemo.java](jfr/JavaFlightRecorderDemo.java) for complete example.**

---

## 9. Epsilon Garbage Collector

A no-op garbage collector for testing and performance analysis.

### Overview

Epsilon GC does not perform any garbage collection, useful for testing memory allocation patterns.

### Usage

```bash
java -XX:+UnlockExperimentalVMOptions -XX:+UseEpsilonGC MyApp
```

### Use Cases

- Performance testing
- Memory allocation analysis
- Short-lived applications

---

## 10. ZGC (Experimental)

A low-latency garbage collector (experimental in Java 11).

### Overview

ZGC is designed for applications requiring low latency and large heaps.

### Usage

```bash
java -XX:+UnlockExperimentalVMOptions -XX:+UseZGC MyApp
```

### Key Features

- Low latency
- Large heap support
- Concurrent collection
- Experimental in Java 11

---

## 11. Removed Java EE and CORBA Modules

Java EE and CORBA modules were removed from JDK.

### Overview

These modules are now available as separate dependencies if needed.

### Removed Modules

- `java.xml.ws` (JAX-WS)
- `java.xml.bind` (JAXB)
- `java.activation` (JAF)
- `java.corba` (CORBA)
- `java.transaction` (JTA)
- `java.se.ee` (Java SE EE)

### Impact

- Smaller JDK size
- Use external dependencies if needed
- Better modularity

---

## 12. Common Interview Questions

### Q1: What are the key features of Java 11?
**A:** Key features:
- HTTP Client (standardized)
- Files methods (readString, writeString)
- String methods (isBlank, lines, strip, repeat)
- Optional.isEmpty()
- Predicate.not()
- Var in lambda parameters
- Nest-based access control
- JFR open-sourced

### Q2: What is the difference between strip() and trim()?
**A:**
- **trim()**: Removes only ASCII whitespace (≤ U+0020)
- **strip()**: Removes all Unicode whitespace characters
- **stripLeading()**: Removes leading whitespace
- **stripTrailing()**: Removes trailing whitespace

### Q3: What is HTTP Client API?
**A:** Modern HTTP API:
- HTTP/1.1 and HTTP/2 support
- Asynchronous and synchronous requests
- Better than HttpURLConnection
- Standardized in Java 11

### Q4: What is Nest-Based Access Control?
**A:** Improved access control:
- Classes in same nest can access private members
- No synthetic bridge methods
- Better performance
- Cleaner bytecode

### Q5: What modules were removed in Java 11?
**A:** Removed modules:
- Java EE modules (JAX-WS, JAXB, etc.)
- CORBA module
- Available as external dependencies

---

**Last Updated:** 2025  
**Version:** 1.0

*This guide covers the features of Java 11 (LTS), released on September 25, 2018.*


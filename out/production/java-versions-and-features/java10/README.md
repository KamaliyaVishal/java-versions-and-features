# Java 10

A comprehensive guide to all Java 10 concepts with practical examples for interview preparation.

## Table of Contents

1. [Local-Variable Type Inference (var)](#1-local-variable-type-inference-var)
2. [Unmodifiable Collections Enhancements](#2-unmodifiable-collections-enhancements)
3. [Optional.orElseThrow()](#3-optionalorelseThrow)
4. [Root Certificates](#4-root-certificates)
5. [Parallel Full GC for G1](#5-parallel-full-gc-for-g1)
6. [Application Class-Data Sharing](#6-application-class-data-sharing)
7. [Thread-Local Handshakes](#7-thread-local-handshakes)
8. [Heap Allocation on Alternative Memory Devices](#8-heap-allocation-on-alternative-memory-devices)
9. [Graal JIT Compiler (Experimental)](#9-graal-jit-compiler-experimental)
10. [Additional Unicode Language-Tag Extensions](#10-additional-unicode-language-tag-extensions)
11. [Removal of javah Tool](#11-removal-of-javah-tool)
12. [Common Interview Questions](#12-common-interview-questions)

---

## 1. Local-Variable Type Inference (var)

The `var` keyword allows the compiler to infer the type of local variables.

### Basic Usage

```java
// Before Java 10
String message = "Hello, World!";
List<String> names = new ArrayList<>();
Map<String, Integer> map = new HashMap<>();

// Java 10 - type inference
var message = "Hello, World!";  // String
var names = new ArrayList<String>();  // ArrayList<String>
var map = new HashMap<String, Integer>();  // HashMap<String, Integer>
```

### When to Use var

```java
// ✅ Good use cases

// 1. Complex generic types
var entries = Map.ofEntries(
    Map.entry("one", 1),
    Map.entry("two", 2)
);

// 2. Constructor calls
var person = new Person("John", 30);

// 3. Method return types
var result = calculateComplexResult();

// 4. Loop variables
for (var entry : map.entrySet()) {
    System.out.println(entry.getKey() + ": " + entry.getValue());
}

for (var i = 0; i < 10; i++) {
    System.out.println(i);
}
```

### Limitations

```java
// ❌ Cannot use var in these cases:

// 1. Not for field declarations
class MyClass {
    // var field; // Compile error
    private String field; // Must be explicit
}

// 2. Not for method parameters
// public void method(var param) {} // Compile error
public void method(String param) {} // Must be explicit

// 3. Not for method return types
// public var method() {} // Compile error
public String method() {} // Must be explicit

// 4. Cannot initialize with null (no type inference)
// var value = null; // Compile error

// 5. Cannot use in lambda parameters (until Java 11)
// var list = Arrays.asList(1, 2, 3);
// list.forEach((var x) -> System.out.println(x)); // Compile error in Java 10
```

### Best Practices

```java
// ✅ Use when type is obvious from context
var users = getUsers();  // Return type is clear from method name
var count = calculateCount();

// ✅ Use for complex generics
var map = new HashMap<String, List<Person>>();

// ❌ Avoid when type is not obvious
var data = process();  // What type is data?

// ❌ Avoid reducing readability
var x = getValue();  // Better: String x = getValue();
```

---

## 2. Unmodifiable Collections Enhancements

New methods for creating unmodifiable collections.

### List.copyOf()

```java
List<String> original = new ArrayList<>(Arrays.asList("A", "B", "C"));
List<String> copy = List.copyOf(original);  // Unmodifiable copy

// copy.add("D"); // UnsupportedOperationException
// copy.set(0, "X"); // UnsupportedOperationException

// If original is already unmodifiable, returns same instance
List<String> immutable = List.of("A", "B");
List<String> copy2 = List.copyOf(immutable);  // Same instance returned
```

### Set.copyOf() and Map.copyOf()

```java
// Set.copyOf()
Set<String> originalSet = new HashSet<>(Arrays.asList("A", "B", "C"));
Set<String> copySet = Set.copyOf(originalSet);  // Unmodifiable

// Map.copyOf()
Map<String, Integer> originalMap = new HashMap<>();
originalMap.put("A", 1);
originalMap.put("B", 2);
Map<String, Integer> copyMap = Map.copyOf(originalMap);  // Unmodifiable

// copyMap.put("C", 3); // UnsupportedOperationException
```

### Characteristics

- Creates true unmodifiable collections
- Defensive copies when needed
- Preserves immutability (returns same instance if already unmodifiable)
- No null values allowed (throws NullPointerException)

---

## 3. Optional.orElseThrow()

Simplified way to throw exception when Optional is empty.

```java
Optional<String> optional = Optional.of("Value");

// Before Java 10
String value = optional.orElseThrow(() -> new IllegalArgumentException("Not found"));

// Java 10 - no-arg version throws NoSuchElementException
String value2 = optional.orElseThrow();  // Throws NoSuchElementException if empty

// Practical example
Optional<String> name = findName(id);
if (!name.isPresent()) {
    throw new IllegalArgumentException("Name not found");
}
String result = name.get();

// Better with Java 10
String result2 = findName(id).orElseThrow();  // Cleaner
```

---

## 4. Root Certificates

OpenJDK now includes root certificates (previously Oracle JDK only).

```java
// No code changes needed - automatically available
// Better security for SSL/TLS connections
```

---

## 5. Parallel Full GC for G1

G1 garbage collector now performs full GC in parallel (improves performance).

```java
// Automatic - no code changes needed
// Improved GC performance for G1 collector
```

---

## 6. Application Class-Data Sharing

Extension of Class-Data Sharing for application classes.

### Benefits

- Faster startup times
- Reduced memory footprint
- Shared class metadata across JVM instances

### Usage

```bash
# Enable application CDS
java -Xshare:dump  # Create shared archive
java -Xshare:on -cp app.jar MainClass  # Use shared archive
```

---

## 7. Thread-Local Handshakes

New JVM feature for executing callbacks on threads without global safepoint.

```java
// JVM-level feature - improves performance
// Enables more efficient GC and stack trace sampling
// No direct API for application developers
```

---

## 8. Heap Allocation on Alternative Memory Devices

Experimental feature for allocating heap on alternative memory devices (e.g., NV-DIMM).

```bash
# Experimental feature
-XX:+UseG1GC -XX:MaxGCPauseMillis=200
-XX:+UnlockExperimentalVMOptions
-XX:+UseTransparentHugePages
-XX:+UseLargePages
-XX:AllocateHeapAt=<path_to_memory_device>
```

---

## 9. Graal JIT Compiler (Experimental)

Java 10 introduced Graal, an experimental Just-In-Time (JIT) compiler written in Java, aiming to improve performance and maintainability.

### Features

- Written in Java (unlike C++ C1/C2 compilers)
- Better performance for some workloads
- More maintainable codebase
- Experimental status in Java 10

### Enabling Graal

```bash
# Enable Graal JIT Compiler
java -XX:+UnlockExperimentalVMOptions -XX:+UseJVMCICompiler -jar app.jar

# With additional options
java -XX:+UnlockExperimentalVMOptions \
     -XX:+UseJVMCICompiler \
     -XX:+EnableJVMCI \
     -jar app.jar
```

### Benefits

- Improved performance for certain workloads
- Better optimization capabilities
- Foundation for future compiler improvements

### Note

- Experimental feature (requires `-XX:+UnlockExperimentalVMOptions`)
- Not recommended for production use in Java 10
- Performance improvements vary by application

---

## 10. Additional Unicode Language-Tag Extensions

Java 10 adds support for additional Unicode language-tag extensions, enhancing internationalization capabilities.

### Unicode Extensions

```java
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

// 12-hour clock format
Locale locale12h = Locale.forLanguageTag("en-US-u-hc-h12");
DateFormat df12h = DateFormat.getTimeInstance(DateFormat.DEFAULT, locale12h);
System.out.println("12-hour: " + df12h.format(new Date()));

// 24-hour clock format
Locale locale24h = Locale.forLanguageTag("en-US-u-hc-h23");
DateFormat df24h = DateFormat.getTimeInstance(DateFormat.DEFAULT, locale24h);
System.out.println("24-hour: " + df24h.format(new Date()));

// Number format extensions
Locale numberLocale = Locale.forLanguageTag("en-US-u-nu-latn");  // Latin numerals
```

### Supported Extensions

- **hc**: Hour cycle (h11, h12, h23, h24)
- **nu**: Number system (latn, arab, etc.)
- **ca**: Calendar type
- **co**: Collation type

### Benefits

- Better internationalization support
- More flexible locale handling
- Standardized Unicode extensions

---

## 11. Removal of javah Tool

The `javah` tool has been removed in Java 10. Its functionality has been integrated into the `javac` compiler.

### Before Java 10

```bash
# Old way - using javah
javah -o MyClass.h com.example.MyClass
```

### Java 10+

```bash
# New way - using javac
javac -h <output_directory> MyClass.java

# Example
javac -h ./headers com/example/MyClass.java
# Generates: headers/com_example_MyClass.h
```

### Benefits

- Unified tooling (one tool instead of two)
- Better integration with build systems
- Simplified workflow

### Migration

- Replace `javah` calls with `javac -h`
- Update build scripts and documentation
- No functional changes to generated headers

---

## 12. Common Interview Questions

### Q1: What is the `var` keyword and when should you use it?
**A:** `var` enables local-variable type inference. Use it when:
- Type is obvious from context
- Reduces boilerplate for complex generics
- Improves readability

Avoid when:
- Type is not obvious
- Reduces code clarity
- In public APIs (method signatures)

### Q2: What's the difference between `List.copyOf()` and creating a new list?
**A:**
- `List.copyOf()` creates an unmodifiable copy
- If the source is already unmodifiable, returns the same instance (optimization)
- Defensive copying when source is modifiable

```java
List<String> modifiable = new ArrayList<>();
List<String> immutable = List.copyOf(modifiable);  // New unmodifiable copy

List<String> alreadyImmutable = List.of("A", "B");
List<String> same = List.copyOf(alreadyImmutable);  // Same instance returned
```

### Q3: Can you use `var` for fields, method parameters, or return types?
**A:** No. `var` can only be used for:
- Local variables
- Loop variables
- Try-with-resources variables

### Q4: What exception does `Optional.orElseThrow()` throw?
**A:** `NoSuchElementException` when called with no arguments. With a supplier, it throws the exception provided by the supplier.

---

## Quick Reference Cheat Sheet

### var Usage
```java
var variable = value;  // Local variables only
for (var item : collection) {}  // Loop variables
try (var resource = ...) {}  // Try-with-resources
```

### Collection Copy Methods
- `List.copyOf(collection)`: Unmodifiable list copy
- `Set.copyOf(collection)`: Unmodifiable set copy
- `Map.copyOf(map)`: Unmodifiable map copy

### Optional Enhancement
- `orElseThrow()`: Throws NoSuchElementException if empty

---

**Last Updated:** 2024  
**Version:** 1.0

*This guide is designed for quick reference during interview preparation. Practice the examples and understand the concepts thoroughly.*


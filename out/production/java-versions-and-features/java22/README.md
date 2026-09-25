# Java 22

A comprehensive guide to all Java 22 concepts with practical examples for interview preparation.

## Table of Contents

1. [Unnamed Variables and Patterns (Finalized)](#1-unnamed-variables-and-patterns-finalized)
2. [Foreign Function & Memory API (Finalized)](#2-foreign-function--memory-api-finalized)
3. [String Templates (Second Preview)](#3-string-templates-second-preview)
4. [Statements Before super() (Preview)](#4-statements-before-super-preview)
5. [Implicitly Declared Classes and Instance Main Methods (Second Preview)](#5-implicitly-declared-classes-and-instance-main-methods-second-preview)
6. [Structured Concurrency (Second Preview)](#6-structured-concurrency-second-preview)
7. [Stream Gatherers (Preview)](#7-stream-gatherers-preview)
8. [Scoped Values (Second Preview)](#8-scoped-values-second-preview)
9. [Launch Multi-File Source-Code Programs](#9-launch-multi-file-source-code-programs)
10. [Class-File API](#10-class-file-api)
11. [Common Interview Questions](#11-common-interview-questions)

---

## 1. Unnamed Variables and Patterns (Finalized)

Unnamed variables and patterns are now standard in Java 22, providing a cleaner way to handle unused values and pattern components.

### Overview

Unnamed variables and patterns use the underscore (`_`) to explicitly mark values and pattern components that are intentionally unused, improving code readability and eliminating compiler warnings.

### Unnamed Variables

```java
// Unnamed variable - unused
for (int i = 0; i < 10; i++) {
    var _ = process(i);  // Result not used
}

// Catch block
try {
    riskyOperation();
} catch (Exception _) {
    System.out.println("Error occurred");
    // Exception not used
}

// Lambda parameters
list.forEach((_, value) -> System.out.println(value));

// Multiple assignments
int count = 0;
for (var _ : list) {
    count++;  // Element not used
}
```

### Unnamed Patterns

```java
record Point(int x, int y) {}
record Rectangle(Point topLeft, Point bottomRight) {}

// Unnamed pattern components
if (obj instanceof Point(int x, _)) {
    System.out.println("X: " + x);
    // Y not used
}

// Nested unnamed patterns
if (rect instanceof Rectangle(Point(int x, _), _)) {
    System.out.println("Top-left X: " + x);
}
```

### Benefits

- Clearer intent (explicitly ignoring values)
- Better readability
- Eliminates compiler warnings for unused variables
- Cleaner code
- More expressive pattern matching

**See [UnnamedVariables.java](unnamedvariables/UnnamedVariables.java) for complete example.**

---

## 2. Foreign Function & Memory API (Finalized)

The Foreign Function & Memory API is now a standard feature in Java 22, providing a safer and more efficient alternative to JNI.

### Overview

This API enables Java programs to interoperate with native code and manage off-heap memory in a type-safe manner, replacing the need for JNI boilerplate.

```java
import java.lang.foreign.*;

// Memory allocation with arenas
try (Arena arena = Arena.ofConfined()) {
    MemorySegment segment = arena.allocate(100);
    // Use segment for native memory operations
} // Automatically freed

// Calling native functions
Linker linker = Linker.nativeLinker();
SymbolLookup stdlib = linker.defaultLookup();
MemorySegment strlen = stdlib.find("strlen").orElseThrow();
```

### Key Features

- Type-safe native interop
- Memory safety with arenas
- No JNI boilerplate
- Better performance than JNI
- Structured memory management
- Finalized API (stable)

### Benefits

- Safer than JNI
- More efficient
- Better error handling
- Automatic memory management
- Type safety
- Production-ready (finalized)

**See [ForeignFunctionMemoryDemo.java](foreignapi/ForeignFunctionMemoryDemo.java) for complete example.**

---

## 3. String Templates (Second Preview)

Refinements to string templates.

```java
String name = "John";
int age = 30;

// String template (preview)
String message = STR."Hello, \{name}! You are \{age} years old.";

// Formatted template
String formatted = FMT."Age: %03d\{age}";  // Age: 030

// Custom template processor
String result = MY_PROCESSOR."Name: \{name}, Age: \{age}";
```

---

**Note:** String Templates were withdrawn in Java 23+ due to design concerns. See [StringTemplates.java](stringtemplates/StringTemplates.java) for details.

---

## 4. Statements Before super() (Preview)

Allow statements before super() call in constructors.

```java
class Parent {
    Parent(String name) {
        System.out.println("Parent: " + name);
    }
}

class Child extends Parent {
    Child(String name) {
        // Statements before super() (preview)
        String processed = name.toUpperCase();
        String validated = validate(processed);
        super(validated);  // Now allowed
    }
    
    private String validate(String s) {
        return s != null ? s : "Default";
    }
}
```

### Benefits

- More flexibility in constructors
- Can do validation/preprocessing before super()
- Better code organization
- Allows static method calls before super()

**See [StatementsBeforeSuper.java](constructors/StatementsBeforeSuper.java) for complete example.**

---

## 5. Implicitly Declared Classes and Instance Main Methods (Second Preview)

Simplified class structure (refinement).

```java
// Unnamed class with instance main
void main() {
    System.out.println("Hello, World!");
}

// Compiler generates class automatically
// Simplified entry point for simple programs
```

---

## 6. Structured Concurrency (Second Preview)

Refinements to structured concurrency with improved error handling and lifecycle management.

### Overview

Structured concurrency treats groups of related tasks as a single unit, ensuring proper lifecycle management and error handling.

```java
import java.util.concurrent.StructuredTaskScope;

// Enhanced structured concurrency
try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
    Future<String> result1 = scope.fork(() -> task1());
    Future<String> result2 = scope.fork(() -> task2());
    
    scope.join();
    scope.throwIfFailed();
    
    // Use results
}
```

### Key Features

- Structured lifecycle management
- Error propagation
- Shutdown strategies
- Automatic resource cleanup
- Prevents thread leaks

**See [StructuredConcurrency.java](structuredconcurrency/StructuredConcurrency.java) for complete example.**

---

## 7. Stream Gatherers (Preview)

Custom intermediate stream operations that extend the Stream API functionality.

### Overview

Stream Gatherers allow you to create custom intermediate operations for streams, providing more flexibility than existing operations.

```java
import java.util.stream.Gatherer;

// Custom gatherer
Gatherer<String, ?, String> myGatherer = Gatherer.ofSequential(
    () -> new String[] { "" },
    (state, element, downstream) -> {
        // Process element
        return downstream.push(element);
    },
    (state, downstream) -> {
        // Final processing
    }
);

// Use gatherer
List<String> result = stream
    .gather(myGatherer)
    .toList();
```

### Built-in Gatherers

```java
import java.util.stream.Gatherers;

// Sliding window
List<List<Integer>> windows = stream
    .gather(Gatherers.windowSliding(3))
    .toList();

// Fixed window
List<List<Integer>> fixed = stream
    .gather(Gatherers.windowFixed(3))
    .toList();

// Fold operation
String result = stream
    .gather(Gatherers.fold(() -> "", (acc, elem) -> acc + elem))
    .findFirst()
    .orElse("");
```

### Benefits

- Extend Stream API
- Create reusable operations
- More expressive code
- Better performance for custom operations

**See [StreamGatherersDemo.java](streamgatherers/StreamGatherersDemo.java) for complete example.**

---

## 8. Scoped Values (Second Preview)

Refinements to scoped values with improved performance and safety.

### Overview

Scoped values provide a way to share immutable data within and across threads, offering a safer alternative to ThreadLocal.

```java
import java.util.concurrent.ScopedValue;

final ScopedValue<String> USER = ScopedValue.newInstance();

ScopedValue.runWhere(USER, "Alice", () -> {
    String user = USER.get();
    // Use value
});
```

### Key Features

- Immutable values
- Inherited by child threads
- Automatic cleanup
- Better performance than ThreadLocal
- Structured scoping

**See [ScopedValues.java](scopedvalues/ScopedValues.java) for complete example.**

---

## 9. Launch Multi-File Source-Code Programs

Allows execution of Java programs with multiple source files without prior compilation.

### Overview

Java 22 allows you to launch multi-file Java programs directly using the `java` launcher, eliminating the need for separate compilation steps.

### Basic Usage

**Main.java:**
```java
public class Main {
    public static void main(String[] args) {
        System.out.println("Main class");
        Helper.printMessage();
    }
}
```

**Helper.java:**
```java
public class Helper {
    public static void printMessage() {
        System.out.println("Hello from Helper!");
    }
}
```

### Running the Program

```bash
# Run directly without compilation
java Main.java Helper.java

# Or use wildcard
java *.java
```

### Key Features

- No compilation step required
- Automatic dependency resolution
- Multiple source files support
- Simplified development workflow

### Benefits

- Faster development cycle
- Easier to run examples
- Better for learning and experimentation
- No build tools needed for simple programs

### Use Cases

- Quick prototyping
- Testing multi-file programs
- Learning Java
- Small scripts with multiple classes
- Development and debugging

**See [LaunchMultiFileDemo.md](multifile/LaunchMultiFileDemo.md) for complete guide.**

---

## 10. Class-File API

API for parsing, generating, and transforming Java class files.

### Overview

The Class-File API provides a standard way to work with Java class files, facilitating tools and frameworks that manipulate bytecode.

### Key Features

- Parse class files
- Generate class files
- Transform bytecode
- Standard API for class file manipulation
- Better than ASM for some use cases

### Use Cases

- Bytecode manipulation tools
- Code generation frameworks
- Static analysis tools
- Compiler plugins
- Runtime code generation

### Benefits

- Standard API
- Type-safe operations
- Better than third-party libraries
- Integrated with JDK

**Note:** This is an advanced API primarily for tool developers and frameworks.

---

## 11. Common Interview Questions

### Q1: What are Unnamed Variables and when to use them?
**A:** Unnamed variables (`_`) are for unused values:
- Clearly indicate value is intentionally unused
- Avoid compiler warnings
- Better code readability
- Useful in loops, catch blocks, lambdas

### Q2: What are Unnamed Patterns?
**A:** Unnamed pattern components:
- Ignore specific components in pattern matching
- Example: `Point(int x, _)` ignores y component
- Clearer intent in pattern matching

### Q3: What is Stream Gatherers?
**A:** Custom intermediate stream operations:
- Extend Stream API functionality
- Create reusable stream operations
- More flexible than existing operations
- Built-in gatherers available (windowSliding, windowFixed, fold)

### Q4: What is the Foreign Function & Memory API?
**A:** API for native code interop:
- Type-safe native function calls
- Memory management with arenas
- Replacement for JNI
- Finalized in Java 22
- Better performance and safety

### Q5: What is Launch Multi-File Source-Code Programs?
**A:** Feature to run multi-file programs:
- No compilation step needed
- Run directly with `java` launcher
- Automatic dependency resolution
- Useful for quick prototyping and learning

### Q6: What are the key improvements in Java 22?
**A:** Key improvements:
- Unnamed Variables and Patterns finalized
- Foreign Function & Memory API finalized
- Stream Gatherers (preview)
- Launch Multi-File Source-Code Programs
- Class-File API

---

**Last Updated:** 2024  
**Version:** 1.0


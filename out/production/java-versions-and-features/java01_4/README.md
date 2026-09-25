# Java 1.4

A comprehensive guide to all Java 1.4 concepts with practical examples for interview preparation.

## Table of Contents

1. [Assertions](#1-assertions)
2. [Regular Expressions](#2-regular-expressions)
3. [Exception Chaining](#3-exception-chaining)
4. [NIO (New Input/Output)](#4-nio-new-inputoutput)
5. [Logging API](#5-logging-api)
6. [Common Interview Questions](#6-common-interview-questions)

---

## 1. Assertions

Assertions provide a way to test assumptions in code during development.

### Overview

Assertions help catch programming errors by testing assumptions that should always be true.

### Syntax

```java
// Simple assertion
assert condition;

// Assertion with message
assert condition : "Error message";

// Example
assert x > 0 : "x must be positive";
```

### Usage

```java
public int divide(int a, int b) {
    assert b != 0 : "Division by zero";
    return a / b;
}
```

### Enabling Assertions

```bash
# Enable assertions
java -ea MyClass

# Disable assertions (default)
java -da MyClass
```

### Key Features

- Development-time testing
- Can be enabled/disabled
- Helps catch bugs early
- Not for production error handling

**See [AssertionsExample.java](assertions/AssertionsExample.java) for complete example.**

---

## 2. Regular Expressions

Java 1.4 introduced the `java.util.regex` package for pattern matching.

### Overview

Regular expressions provide powerful pattern matching capabilities for strings.

### Basic Usage

```java
import java.util.regex.*;

// Pattern matching
String text = "The quick brown fox";
Pattern pattern = Pattern.compile("\\b\\w{4}\\b");
Matcher matcher = pattern.matcher(text);

while (matcher.find()) {
    System.out.println("Found: " + matcher.group());
}

// String methods with regex
String result = text.replaceAll("\\s+", " ");
boolean matches = text.matches(".*fox.*");
```

### Key Features

- Pattern compilation
- Matcher for finding patterns
- String methods support
- Group capturing
- Replacement operations

**See [RegularExpressionsExample.java](regex/RegularExpressionsExample.java) for complete example.**

---

## 3. Exception Chaining

Exception chaining allows exceptions to wrap other exceptions, preserving the original cause.

### Overview

Exception chaining helps maintain the full exception history when exceptions are caught and rethrown.

### Usage

```java
try {
    // Some operation
} catch (IOException e) {
    throw new MyException("Operation failed", e); // Chain exception
}

// Accessing chained exception
try {
    // Code
} catch (MyException e) {
    Throwable cause = e.getCause(); // Get original exception
    cause.printStackTrace();
}
```

### Key Features

- Preserve exception history
- Better error diagnostics
- Cause tracking
- Improved debugging

**See [ExceptionChainingExample.java](exceptions/ExceptionChainingExample.java) for complete example.**

---

## 4. NIO (New Input/Output)

NIO provides non-blocking I/O operations and improved performance.

### Overview

NIO offers better I/O performance through buffers, channels, and selectors.

### Key Components

```java
import java.nio.*;
import java.nio.channels.*;
import java.nio.file.*;

// FileChannel
FileChannel channel = FileChannel.open(Paths.get("file.txt"));
ByteBuffer buffer = ByteBuffer.allocate(1024);
channel.read(buffer);

// Selector for non-blocking I/O
Selector selector = Selector.open();
ServerSocketChannel serverChannel = ServerSocketChannel.open();
serverChannel.configureBlocking(false);
serverChannel.register(selector, SelectionKey.OP_ACCEPT);
```

### Key Features

- Non-blocking I/O
- Buffers for efficient I/O
- Channels for I/O operations
- Selectors for multiplexing
- Better performance

**See [NIOExample.java](nio/NIOExample.java) for complete example.**

---

## 5. Logging API

Java 1.4 introduced the `java.util.logging` package for application logging.

### Overview

The Logging API provides a standard way to log messages in Java applications.

### Basic Usage

```java
import java.util.logging.*;

Logger logger = Logger.getLogger("MyLogger");
logger.setLevel(Level.INFO);

logger.info("Information message");
logger.warning("Warning message");
logger.severe("Error message");
```

### Key Features

- Multiple log levels
- Handlers for output
- Formatters for formatting
- Configurable logging
- Standard API

**See [LoggingExample.java](logging/LoggingExample.java) for complete example.**

---

## 6. Common Interview Questions

### Q1: What are Assertions?
**A:** Development-time testing mechanism:
- Test assumptions in code
- Can be enabled/disabled with -ea flag
- Not for production error handling
- Helps catch bugs early

### Q2: How do Regular Expressions work in Java?
**A:** Pattern matching with `java.util.regex`:
- Pattern class for compiling regex
- Matcher class for finding matches
- String methods support regex
- Group capturing and replacement

### Q3: What is Exception Chaining?
**A:** Wrapping exceptions:
- Preserve original exception as cause
- Better error diagnostics
- Full exception history
- Access via getCause() method

### Q4: What is NIO?
**A:** New Input/Output:
- Non-blocking I/O
- Buffers for efficient I/O
- Channels for I/O operations
- Selectors for multiplexing
- Better performance than traditional I/O

### Q5: What is the Logging API?
**A:** Standard logging framework:
- Multiple log levels (INFO, WARNING, SEVERE)
- Handlers for output destinations
- Formatters for message formatting
- Configurable logging behavior

---

**Last Updated:** 2025  
**Version:** 1.0

*This guide covers the features of Java 1.4, released on February 6, 2002.*


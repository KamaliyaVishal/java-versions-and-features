# Java 18

A comprehensive guide to all Java 18 concepts with practical examples for interview preparation.

## Table of Contents

1. [Simple Web Server](#1-simple-web-server)
2. [Pattern Matching for switch (Second Preview)](#2-pattern-matching-for-switch-second-preview)
3. [Code Snippets in Java API Documentation](#3-code-snippets-in-java-api-documentation)
4. [Reimplement Core Reflection with Method Handles](#4-reimplement-core-reflection-with-method-handles)
5. [Internet-Address Resolution SPI](#5-internet-address-resolution-spi)
6. [Foreign Function & Memory API (Second Incubator)](#6-foreign-function--memory-api-second-incubator)
7. [Vector API (Third Incubator)](#7-vector-api-third-incubator)
8. [UTF-8 by Default](#8-utf-8-by-default)
9. [Deprecate Finalization for Removal](#9-deprecate-finalization-for-removal)
10. [Common Interview Questions](#10-common-interview-questions)

---

## 1. Simple Web Server

Minimal web server for prototyping and testing.

### Basic Usage

```java
import com.sun.net.httpserver.*;

// Create simple web server
HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

// Create context
server.createContext("/", exchange -> {
    String response = "Hello, World!";
    exchange.sendResponseHeaders(200, response.length());
    try (OutputStream os = exchange.getResponseBody()) {
        os.write(response.getBytes());
    }
});

// Start server
server.start();
System.out.println("Server started on port 8000");

// Stop server
server.stop(0);
```

### Command Line Tool

```bash
# Start simple web server from command line
jwebserver

# Custom port and directory
jwebserver -p 9000 -d /path/to/files

# Options:
# -p: port number
# -d: directory to serve
# -h: show help
```

**Note:** See [SimpleWebServer.java](webserver/SimpleWebServer.java) for complete example.

---

## 2. Pattern Matching for switch (Second Preview)

Enhanced pattern matching capabilities in switch expressions.

### Overview

Pattern matching for switch allows you to match patterns directly in switch statements, making code more concise and readable.

### Basic Usage

```java
Object obj = "Hello";

// Pattern matching with switch (preview - use --enable-preview)
String result = switch (obj) {
    case String s -> "String: " + s;
    case Integer i -> "Integer: " + i;
    case null -> "Null";
    default -> "Unknown type";
};
```

### With Sealed Classes

```java
sealed interface Shape permits Circle, Rectangle, Triangle {}

Shape shape = new Circle(5.0);

double area = switch (shape) {
    case Circle c -> Math.PI * c.radius() * c.radius();
    case Rectangle r -> r.width() * r.height();
    case Triangle t -> 0.5 * t.base() * t.height();
    // No default needed - exhaustive (all cases covered)
};
```

### Guarded Patterns

```java
Object obj = "Hello World";

String result = switch (obj) {
    case String s when s.length() > 10 -> "Long string: " + s;
    case String s -> "Short string: " + s;
    case Integer i when i > 100 -> "Large number: " + i;
    case Integer i -> "Small number: " + i;
    default -> "Unknown";
};
```

### Benefits

- More concise code
- Exhaustive pattern matching with sealed classes
- Type-safe operations
- Better readability

**Note:** Requires `--enable-preview` flag. See [PatternMatchingSwitch.java](patternmatching/PatternMatchingSwitch.java) for complete examples.

---

## 3. Code Snippets in Java API Documentation

Enhanced Javadoc with code snippets.

### @snippet Tag

```java
/**
 * Calculates the sum of two numbers.
 * 
 * <p>Example usage:</p>
 * {@snippet :
 * Calculator calc = new Calculator();
 * int result = calc.add(5, 3);
 * System.out.println(result); // Output: 8
 * }
 * 
 * @param a first number
 * @param b second number
 * @return sum of a and b
 */
public int add(int a, int b) {
    return a + b;
}

// External snippet file
/**
 * {@snippet file="examples.java" region="calculator"}
 */
```

### Benefits

- Syntax highlighting
- Compile-time validation
- Better documentation
- Embedded examples

---

**Note:** See [CodeSnippetsExample.java](javadoc/CodeSnippetsExample.java) for complete examples.

---

## 4. Reimplement Core Reflection with Method Handles

Core reflection reimplemented using Method Handles.

- Better performance
- Reduced maintenance
- Consistent implementation

---

**Note:** This is an internal JVM improvement and doesn't require code changes. It provides better performance and maintainability.

---

## 5. Internet-Address Resolution SPI

Service Provider Interface for custom hostname resolution.

### Overview

The Internet-Address Resolution SPI allows you to provide custom implementations for hostname and address resolution, giving you control over how Java resolves network addresses.

### Basic Usage

```java
import java.net.spi.InetAddressResolver;
import java.net.spi.InetAddressResolverProvider;
import java.net.*;

// Custom resolver provider
public class CustomResolverProvider extends InetAddressResolverProvider {
    @Override
    public InetAddressResolver get(Configuration configuration) {
        return new InetAddressResolver() {
            @Override
            public InetAddress[] lookupByName(String host, LookupPolicy lookupPolicy) 
                    throws UnknownHostException {
                // Custom resolution logic
                if (host.equals("example.com")) {
                    return new InetAddress[]{InetAddress.getByName("93.184.216.34")};
                }
                return InetAddress.getAllByName(host);
            }

            @Override
            public String lookupByAddress(byte[] addr) throws UnknownHostException {
                return InetAddress.getByAddress(addr).getHostName();
            }
        };
    }
    
    @Override
    public String name() {
        return "custom-resolver";
    }
}
```

### Features

- **Custom resolution**: Implement your own hostname resolution logic
- **Service provider**: Register via ServiceLoader
- **Flexible**: Override default DNS resolution behavior
- **Testing**: Useful for testing and mocking

### Use Cases

- Custom DNS resolution
- Testing and mocking
- Network address translation
- Load balancing
- Security filtering

**Note:** See [InternetAddressResolutionSPI.java](internetaddress/InternetAddressResolutionSPI.java) for complete example.

---

## 6. Foreign Function & Memory API (Second Incubator)

Refinements to Foreign Function & Memory API.

```java
import jdk.incubator.foreign.*;

// Same as Java 17, with improvements
// Better performance, more features
```

---

## 7. Vector API (Third Incubator)

Refinements to Vector API.

```java
import jdk.incubator.vector.*;

// Enhanced vector operations
// Better performance for SIMD
```

---

## 8. UTF-8 by Default

UTF-8 is now the default charset for all Java APIs.

```java
// APIs now default to UTF-8
String text = Files.readString(path);  // UTF-8 by default
Files.writeString(path, text);  // UTF-8 by default

// No need to specify charset in most cases
// Better cross-platform compatibility
```

### Benefits

- Consistent behavior across platforms
- Simpler code (no charset specification needed)
- Better Unicode support
- Reduced charset-related bugs

---

## 9. Deprecate Finalization for Removal

Finalization deprecated (will be removed in future).

```java
// Deprecated - use try-with-resources instead
@Override
protected void finalize() throws Throwable {
    // Cleanup - deprecated
    super.finalize();
}

// Recommended: Use try-with-resources or Cleaner API
public class Resource implements AutoCloseable {
    @Override
    public void close() {
        // Cleanup
    }
}

try (Resource res = new Resource()) {
    // Use resource
} // Automatically closed
```

### Alternatives

- Try-with-resources (immediate cleanup)
- Cleaner API (similar to finalization but better)
- Phantom references

---

## 10. Common Interview Questions

### Q1: What is the Simple Web Server and when to use it?
**A:** Simple HTTP server tool:
- Useful for: Prototyping, testing, development
- Quick way to serve static files
- Not for production use
- Available via `jwebserver` command

### Q2: What are the benefits of UTF-8 by default?
**A:**
- Consistent behavior across platforms
- Simpler code (no charset specification)
- Better Unicode support
- Reduced charset-related bugs

### Q3: Why was Finalization deprecated?
**A:**
- Unpredictable timing (GC decides when)
- Performance overhead
- Not guaranteed to run
- Use try-with-resources or Cleaner API instead

---

**Last Updated:** 2024  
**Version:** 1.0


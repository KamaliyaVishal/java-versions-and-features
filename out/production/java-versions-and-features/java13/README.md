# Java 13

A comprehensive guide to all Java 13 concepts with practical examples for interview preparation.

## Table of Contents

1. [Text Blocks (Preview)](#1-text-blocks-preview)
2. [Switch Expressions (Second Preview)](#2-switch-expressions-second-preview)
3. [Reimplementation of the Legacy Socket API](#3-reimplementation-of-the-legacy-socket-api)
4. [Dynamic CDS Archives](#4-dynamic-cds-archives)
5. [FileSystems.newFileSystem()](#5-filesystemsnewfilesystem)
6. [DOM and SAX Factories](#6-dom-and-sax-factories)
7. [ZGC Improvements](#7-zgc-improvements)
8. [Common Interview Questions](#8-common-interview-questions)

---

## 1. Text Blocks (Preview)

Multi-line string literals for better readability.

### Basic Usage

```java
// Before Java 13
String html = "<html>\n" +
              "    <body>\n" +
              "        <p>Hello, World!</p>\n" +
              "    </body>\n" +
              "</html>";

// Java 13 - Text Blocks
String html = """
              <html>
                  <body>
                      <p>Hello, World!</p>
                  </body>
              </html>
              """;

// Preserves formatting
String json = """
              {
                  "name": "John",
                  "age": 30,
                  "city": "New York"
              }
              """;
```

### Escape Sequences

```java
// Escape quotes
String text = """
              She said "Hello"
              """;

// Escape newline (suppress line break)
String text2 = """
                Line 1 \
                Line 2
                """;  // Results in "Line 1 Line 2"

// Escape sequence
String text3 = """
                Tab:\t
                Newline:\n
                """;
```

### Indentation

```java
// Common indentation is removed
String code = """
              public void method() {
                  System.out.println("Hello");
              }
              """;

// If content is indented differently
String code2 = """
              public void method() {
                  System.out.println("Hello");
              }
              """.indent(4);  // Add 4 spaces indentation
```

### Interpolation (Future)

```java
// Note: String interpolation comes later
// For now, use concatenation or formatting

String name = "John";
String message = """
                Hello, %s!
                """.formatted(name);
```

---

## 2. Switch Expressions (Second Preview)

Refinements to switch expressions based on feedback.

```java
// Same as Java 12, with refinements
int result = switch (value) {
    case 1 -> 10;
    case 2 -> 20;
    default -> {
        System.out.println("Default case");
        yield 0;
    }
};
```

---

## 3. Reimplementation of the Legacy Socket API

Java 13 reimplements the legacy socket API to improve performance, scalability, and maintainability.

### Overview

The socket API has been rewritten to:
- Improve performance and scalability
- Better maintainability
- Enhanced reliability
- No breaking changes to existing code

### Usage

```java
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

// Server example
try (ServerSocket serverSocket = new ServerSocket(8080)) {
    System.out.println("Server listening on port 8080");
    while (true) {
        Socket socket = serverSocket.accept();
        // Handle client connection
    }
} catch (IOException ex) {
    ex.printStackTrace();
}

// Client example
try (Socket socket = new Socket("localhost", 8080)) {
    // Use socket for communication
} catch (IOException ex) {
    ex.printStackTrace();
}
```

### Benefits

- Better performance
- Improved scalability
- Enhanced maintainability
- Backward compatible (no code changes needed)

---

## 4. Dynamic CDS Archives

Dynamic Class-Data Sharing (CDS) Archives allow dynamic archiving of classes at application exit.

### Overview

Dynamic CDS extends the existing CDS feature by allowing classes to be archived dynamically at runtime, not just during JDK build time.

### Creating Dynamic Archive

```bash
# Create dynamic archive at application exit
java -XX:ArchiveClassesAtExit=app-cds.jsa -cp myApp.jar com.example.MyApp
```

### Using Dynamic Archive

```bash
# Use the dynamic archive in subsequent runs
java -XX:SharedArchiveFile=app-cds.jsa -cp myApp.jar com.example.MyApp
```

### Benefits

- Faster application startup
- Reduced memory footprint
- Dynamic class archiving (no rebuild needed)
- Works with application-specific classes

### Use Cases

- Microservices with frequent restarts
- Applications with long startup times
- Memory-constrained environments
- Development environments (faster iteration)

**Note:** For detailed documentation and examples, see [Dynamic CDS Archives Guide](cds/DynamicCDSArchivesGuide.md).

---

## 5. FileSystems.newFileSystem()

New method for creating file systems, particularly useful for working with ZIP files as file systems.

### Overview

The `FileSystems.newFileSystem()` method allows you to create file systems from various sources, most commonly ZIP/JAR files.

### Basic Usage

```java
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

// Create file system from ZIP file
Path zipPath = Paths.get("archive.zip");
Map<String, String> env = new HashMap<>();
env.put("create", "true");

try (FileSystem zipFs = FileSystems.newFileSystem(
        URI.create("jar:" + zipPath.toUri()), env)) {
    
    // Access files within ZIP
    Path fileInZip = zipFs.getPath("file.txt");
    String content = Files.readString(fileInZip);
    
    // Or write to ZIP
    Path newFile = zipFs.getPath("newfile.txt");
    Files.writeString(newFile, "Hello from ZIP!");
}
```

### Benefits

- Treat ZIP/JAR files as file systems
- Use standard Files API
- No need for special ZIP libraries
- Cleaner code
- Better integration with NIO.2

---

## 6. DOM and SAX Factories

New factory methods for DOM and SAX parsers.

### Overview

Java 13 introduces `newDefaultInstance()` methods for XML parser factories, providing explicit default instance creation.

### Usage

```java
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParserFactory;

// DOM Parser
DocumentBuilderFactory domFactory = 
    DocumentBuilderFactory.newDefaultInstance();
DocumentBuilder builder = domFactory.newDocumentBuilder();

// SAX Parser
SAXParserFactory saxFactory = 
    SAXParserFactory.newDefaultInstance();
SAXParser parser = saxFactory.newSAXParser();
```

### Comparison

**Old way:**
```java
DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
SAXParserFactory saxFactory = SAXParserFactory.newInstance();
```

**New way (Java 13+):**
```java
DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
SAXParserFactory saxFactory = SAXParserFactory.newDefaultInstance();
```

### Benefits

- Explicit default instance creation
- Better clarity in code
- Consistent API design
- Future-proof API

---

## 7. ZGC Improvements

Z Garbage Collector improvements for better memory management.

### Uncommit Unused Memory

ZGC can now return unused heap memory to the operating system.

### Usage

```bash
# Enable ZGC with uncommit delay
java -XX:+UnlockExperimentalVMOptions \
     -XX:+UseZGC \
     -XX:ZUncommitDelay=300 \
     -cp myApp.jar com.example.MyApp
```

### Configuration Options

- `-XX:+UseZGC`: Enable Z Garbage Collector
- `-XX:ZUncommitDelay=<seconds>`: Delay before uncommitting unused memory (default: 300)
- `-XX:+UnlockExperimentalVMOptions`: Required for experimental features

### Benefits

- Better memory utilization
- Return unused memory to OS
- Improved resource efficiency
- Better performance for large heaps

### Use Cases

- Applications with varying memory needs
- Large heap sizes
- Memory-constrained environments
- Long-running applications

---

## 8. Common Interview Questions

### Q1: What are Text Blocks and why use them?
**A:** Text Blocks provide multi-line string literals:
- Better readability for multi-line strings
- Preserves formatting
- Easier to write HTML, JSON, SQL, etc.
- Reduces escape sequences needed

### Q2: How does indentation work in Text Blocks?
**A:** Common indentation is automatically removed. The leftmost non-whitespace character determines the baseline. All lines are adjusted relative to this baseline.

---

**Last Updated:** 2024  
**Version:** 1.0


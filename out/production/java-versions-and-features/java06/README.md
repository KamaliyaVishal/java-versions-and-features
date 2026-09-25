# Java 6

A comprehensive guide to all Java 6 concepts with practical examples for interview preparation.

## Table of Contents

1. [Scripting Support (JSR 223)](#1-scripting-support-jsr-223)
2. [Improved Web Services Support](#2-improved-web-services-support)
3. [Compiler API](#3-compiler-api)
4. [Pluggable Annotations Processing API](#4-pluggable-annotations-processing-api)
5. [JDBC 4.0 Enhancements](#5-jdbc-40-enhancements)
6. [Common Interview Questions](#6-common-interview-questions)

---

## 1. Scripting Support (JSR 223)

Java 6 introduced scripting support, allowing Java applications to execute scripts in various languages.

### Overview

The Scripting API enables Java applications to integrate with scripting languages like JavaScript, Python, Ruby, etc.

### Basic Usage

```java
import javax.script.*;

ScriptEngineManager manager = new ScriptEngineManager();
ScriptEngine engine = manager.getEngineByName("JavaScript");

// Execute script
engine.eval("print('Hello from JavaScript');");

// Evaluate expression
Object result = engine.eval("2 + 3");
System.out.println("Result: " + result);
```

### Key Features

- Multiple scripting languages
- Script execution
- Variable binding
- Function invocation
- Script compilation

**See [ScriptingExample.java](scripting/ScriptingExample.java) for complete example.**

---

## 2. Improved Web Services Support

Java 6 enhanced support for web services with JAX-WS.

### Overview

JAX-WS (Java API for XML Web Services) provides better support for SOAP and REST web services.

### Key Features

- SOAP web services
- REST support
- WSDL generation
- Client generation
- Better tooling

**Note:** This is primarily for enterprise web services development.

---

## 3. Compiler API

The Compiler API allows programmatic compilation of Java source code.

### Overview

The Compiler API enables tools and frameworks to compile Java code programmatically.

### Basic Usage

```java
import javax.tools.*;

JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);

Iterable<? extends JavaFileObject> compilationUnits = 
    fileManager.getJavaFileObjectsFromStrings(Arrays.asList("MyClass.java"));

compiler.getTask(null, fileManager, null, null, null, compilationUnits).call();
```

### Key Features

- Programmatic compilation
- Tool integration
- IDE support
- Build tool support

**See [CompilerAPIExample.java](compiler/CompilerAPIExample.java) for complete example.**

---

## 4. Pluggable Annotations Processing API

The Pluggable Annotations Processing API allows custom annotation processors.

### Overview

Annotation processors can process annotations at compile time to generate code or perform validation.

### Key Features

- Compile-time annotation processing
- Code generation
- Validation
- Tool integration

**Note:** This is an advanced feature primarily for tool developers.

---

## 5. JDBC 4.0 Enhancements

JDBC 4.0 introduced several improvements for database connectivity.

### Overview

JDBC 4.0 enhancements include automatic driver loading, better exception handling, and new data types.

### Key Features

- Automatic driver loading
- Better exception handling
- SQLXML support
- RowId support
- National Character Set support

**See [JDBC4Example.java](jdbc/JDBC4Example.java) for complete example.**

---

## 6. Common Interview Questions

### Q1: What is Scripting Support in Java 6?
**A:** JSR 223 Scripting API:
- Execute scripts in various languages
- JavaScript, Python, Ruby support
- Variable binding
- Function invocation

### Q2: What is the Compiler API?
**A:** Programmatic Java compilation:
- Compile code programmatically
- Tool integration
- IDE support
- Build tool support

### Q3: What are JDBC 4.0 Enhancements?
**A:** Improved database connectivity:
- Automatic driver loading
- Better exception handling
- SQLXML support
- RowId support

### Q4: What is Pluggable Annotations Processing?
**A:** Compile-time annotation processing:
- Custom annotation processors
- Code generation
- Validation
- Tool integration

---

**Last Updated:** 2025  
**Version:** 1.0

*This guide covers the features of Java 6, released on December 11, 2006.*


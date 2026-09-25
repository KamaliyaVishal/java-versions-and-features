# Java 15 Hidden Classes Guide

## Overview

Hidden classes are classes that cannot be used directly by the bytecode of other classes. They are intended for use by frameworks that generate classes at runtime and use them indirectly through reflection.

**NOTE:** Hidden classes are primarily used by frameworks and language runtimes. They are not typically used directly in application code.

## Characteristics

1. **Cannot be linked by other classes**: Hidden classes cannot be referenced directly in bytecode
2. **Not discoverable by reflection**: Unless explicitly allowed, hidden classes are not visible via reflection
3. **Can be unloaded independently**: Hidden classes can be garbage collected when not needed
4. **Created at runtime**: Hidden classes are generated dynamically
5. **Not part of the package hierarchy**: Hidden classes don't follow normal package structure

## Basic Usage

```java
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;

// Framework creates hidden class
MethodHandles.Lookup lookup = MethodHandles.lookup();
byte[] byteCode = generateClassBytecode();

Lookup.ClassOption[] options = {Lookup.ClassOption.NESTMATE};

Lookup hiddenLookup = lookup.defineHiddenClass(
    byteCode,
    true,  // initialize class
    options
);

Class<?> hiddenClass = hiddenLookup.lookupClass();
// Hidden class can be used via reflection or method handles
// But not directly referenced in bytecode
```

## Use Cases

1. **Frameworks loading classes dynamically**
   - Dynamic proxy generation
   - Runtime code generation
   - Framework internals

2. **Language runtimes**
   - Lambda expression implementation
   - Dynamic method handles
   - Scripting engines

3. **Application servers**
   - Dynamic class loading
   - Hot deployment
   - Plugin systems

4. **Dynamic code generation**
   - Code generators
   - Template engines
   - Compiler frameworks

5. **Performance optimization**
   - Faster class loading
   - Reduced memory footprint
   - Better garbage collection

## Benefits

1. **Better encapsulation**: Hidden classes are not visible to application code
2. **Can be unloaded when not needed**: Improves memory management
3. **Faster class loading**: Optimized for runtime generation
4. **Framework-friendly API**: Designed for framework developers
5. **Reduced memory footprint**: Can be garbage collected independently

## Example: Framework Usage (Conceptual)

```java
// Framework generates bytecode for a proxy class
byte[] proxyBytecode = generateProxyClass(targetInterface);

// Create hidden class
Lookup lookup = MethodHandles.lookup();
Lookup hiddenLookup = lookup.defineHiddenClass(
    proxyBytecode,
    true
);

// Use hidden class via method handles
Class<?> proxyClass = hiddenLookup.lookupClass();
// Hidden class is not visible to other classes' bytecode
// But can be used via reflection or method handles
```

## Important Notes

- **Framework-focused**: Hidden classes are primarily for framework developers
- **Not for application code**: Not typically used directly in application code
- **JVM internal use**: Used internally by JVM for lambdas and other features
- **Performance benefits**: Can improve performance for dynamic class loading
- **Better than anonymous classes**: More efficient than anonymous classes for some use cases

## Real-World Usage

Frameworks and systems that use hidden classes:

- **Lambda expression implementation**: JVM uses hidden classes for lambda expressions
- **Dynamic proxy generation**: Frameworks generate proxies as hidden classes
- **Bytecode manipulation frameworks**: ASM, Javassist, etc.
- **Application servers**: Dynamic class loading and hot deployment
- **Scripting engines**: Runtime code generation

## Comparison with Regular Classes

### Regular Classes

- Visible to all classes in the same package
- Can be discovered via reflection
- Part of the package hierarchy
- Cannot be unloaded independently
- Slower class loading

### Hidden Classes

- Not visible to other classes' bytecode
- Not discoverable by reflection (unless allowed)
- Not part of package hierarchy
- Can be unloaded independently
- Faster class loading

## API Details

### MethodHandles.Lookup.defineHiddenClass()

```java
public Lookup defineHiddenClass(byte[] bytes, boolean initialize, ClassOption... options)
```

**Parameters:**
- `bytes`: Bytecode of the class to define
- `initialize`: Whether to initialize the class immediately
- `options`: Class options (e.g., NESTMATE, STRONG)

**Returns:**
- A `Lookup` object that can access the hidden class

## Use Cases in Detail

### 1. Lambda Expressions

The JVM uses hidden classes internally to implement lambda expressions efficiently. Each lambda is compiled to a hidden class.

### 2. Dynamic Proxies

Frameworks can generate proxy classes as hidden classes, making them invisible to application code but usable via interfaces.

### 3. Hot Deployment

Application servers can use hidden classes for hot deployment, allowing classes to be unloaded when new versions are deployed.

### 4. Code Generation

Frameworks that generate code at runtime can use hidden classes to avoid polluting the class namespace.

## Benefits Summary

1. **Encapsulation**: Better separation between framework and application code
2. **Memory Management**: Can be unloaded when not needed
3. **Performance**: Faster class loading and better GC behavior
4. **Flexibility**: Framework-friendly API for dynamic class generation
5. **Security**: Hidden classes are not accessible to untrusted code

## Additional Resources

- [JEP 371: Hidden Classes](https://openjdk.org/jeps/371)
- Java API Documentation for `MethodHandles.Lookup`
- Project Loom documentation (uses hidden classes)

## Summary

Hidden classes provide a mechanism for frameworks to generate classes at runtime that are not visible to application code. This feature is primarily for framework developers and language runtimes, enabling better encapsulation, performance, and memory management for dynamically generated classes.


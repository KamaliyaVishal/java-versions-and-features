# Java 10 Graal JIT Compiler Guide

Graal is an experimental Just-In-Time (JIT) compiler introduced in Java 10, written in Java itself.

## Overview

Graal is a high-performance JIT compiler that:
- Is written in Java (unlike traditional C++ C1/C2 compilers)
- Provides better performance for certain workloads
- Offers more maintainable codebase
- Is experimental in Java 10

## Enabling Graal

### Basic Usage

```bash
# Enable Graal JIT Compiler
java -XX:+UnlockExperimentalVMOptions \
     -XX:+UseJVMCICompiler \
     -jar app.jar
```

### Full Command with Options

```bash
java -XX:+UnlockExperimentalVMOptions \
     -XX:+EnableJVMCI \
     -XX:+UseJVMCICompiler \
     -XX:+UseJVMCICompiler \
     -jar app.jar
```

## JVMCI (Java-Level JVM Compiler Interface)

Graal uses JVMCI, which allows:
- Java code to be used as a JIT compiler
- Better integration with JVM internals
- More flexible compiler development

## Performance Considerations

### When Graal May Help

- Long-running applications
- CPU-intensive workloads
- Applications with complex code paths
- Server-side applications

### When Graal May Not Help

- Short-lived applications
- Applications with simple code paths
- Applications already well-optimized by C2

## Comparison with C2 Compiler

### C2 Compiler (Traditional)
- Written in C++
- Mature and stable
- Default in production
- Well-optimized

### Graal Compiler (Experimental)
- Written in Java
- More maintainable
- Better for some workloads
- Experimental status

## Example: Running Application with Graal

```bash
# Compile your application
javac -d classes src/**/*.java

# Run with Graal
java -XX:+UnlockExperimentalVMOptions \
     -XX:+UseJVMCICompiler \
     -cp classes \
     com.example.Main
```

## Benchmarking

To compare performance:

```bash
# Run with default C2 compiler
java -jar app.jar

# Run with Graal compiler
java -XX:+UnlockExperimentalVMOptions \
     -XX:+UseJVMCICompiler \
     -jar app.jar

# Compare execution times and throughput
```

## Limitations

1. **Experimental**: Not recommended for production in Java 10
2. **Performance Varies**: May not improve all applications
3. **Requires Flags**: Must use experimental VM options
4. **Platform Dependent**: Availability may vary by platform

## Future Development

- Graal became more stable in later Java versions
- GraalVM is a separate project with additional features
- Native image compilation (AOT compilation)
- Polyglot capabilities

## Best Practices

1. **Test Thoroughly**: Benchmark before using in production
2. **Monitor Performance**: Compare with default compiler
3. **Use for Long-Running Apps**: Better for server applications
4. **Keep Updated**: Check for improvements in newer Java versions

## Troubleshooting

### If Graal is Not Available

```bash
# Check if JVMCI is available
java -XX:+UnlockExperimentalVMOptions \
     -XX:+PrintFlagsFinal \
     -version | grep JVMCI
```

### Common Issues

- **Not Available**: Some JVM builds may not include Graal
- **Performance Regression**: May perform worse for some workloads
- **Compatibility**: Some code patterns may not compile well

## Notes

- Graal is **experimental** in Java 10
- Requires `-XX:+UnlockExperimentalVMOptions`
- Performance improvements are workload-dependent
- Consider GraalVM for production use (separate project)

## Resources

- JEP 317: Experimental Java-Based JIT Compiler
- GraalVM Project: https://www.graalvm.org/
- JVMCI Documentation


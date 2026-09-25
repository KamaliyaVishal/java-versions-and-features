# Java 13 Dynamic CDS Archives Guide

## Overview

Dynamic Class-Data Sharing (CDS) Archives extend the existing CDS feature by allowing classes to be archived dynamically at application exit, not just during JDK build time.

## What is CDS?

Class-Data Sharing (CDS) improves startup performance by sharing common class metadata between different Java processes. This reduces memory footprint and speeds up application startup.

## Dynamic CDS vs Static CDS

### Static CDS (Java 5+)
- Created during JDK build time
- Contains only JDK classes
- Fixed at JDK installation

### Dynamic CDS (Java 13+)
- Created at application runtime
- Contains application-specific classes
- Generated when application exits
- Can be updated without rebuilding JDK

## Creating Dynamic CDS Archive

### Step 1: Run Application with Archive Option

```bash
java -XX:ArchiveClassesAtExit=app-cds.jsa \
     -cp myApp.jar \
     com.example.MyApp
```

This command:
- Runs your application normally
- Archives all loaded classes when the application exits
- Creates `app-cds.jsa` file

### Step 2: Use Dynamic Archive in Subsequent Runs

```bash
java -XX:SharedArchiveFile=app-cds.jsa \
     -cp myApp.jar \
     com.example.MyApp
```

This command:
- Loads classes from the archive
- Faster startup time
- Reduced memory footprint

## Complete Example

### 1. First Run (Create Archive)

```bash
# Run application and create archive
java -XX:ArchiveClassesAtExit=myapp-cds.jsa \
     -cp target/myapp.jar \
     com.example.Main

# Archive is created at: myapp-cds.jsa
```

### 2. Subsequent Runs (Use Archive)

```bash
# Use the archive for faster startup
java -XX:SharedArchiveFile=myapp-cds.jsa \
     -cp target/myapp.jar \
     com.example.Main
```

## JVM Options

### Creating Archive
- `-XX:ArchiveClassesAtExit=<filename>`: Create archive at exit

### Using Archive
- `-XX:SharedArchiveFile=<filename>`: Use existing archive
- `-Xshare:on`: Force CDS (fail if archive not found)
- `-Xshare:auto`: Use CDS if available (default)
- `-Xshare:off`: Disable CDS

## Benefits

1. **Faster Startup**: Classes are pre-loaded from archive
2. **Reduced Memory**: Shared class metadata across processes
3. **Application-Specific**: Includes your application classes
4. **Dynamic**: No JDK rebuild required
5. **Easy Updates**: Regenerate archive when classes change

## Use Cases

### Microservices
- Frequent restarts benefit from faster startup
- Multiple instances can share memory

### Development
- Faster iteration cycles
- Quicker test execution

### Production
- Reduced startup time
- Lower memory footprint
- Better resource utilization

## Best Practices

1. **Create Archive in Production Environment**
   - Archive should match production classpath
   - Use same JDK version

2. **Update Archive When Needed**
   - Regenerate when classes change
   - Update when dependencies change

3. **Test Archive Compatibility**
   - Verify archive works with new code
   - Test startup performance

4. **Monitor Performance**
   - Measure startup time improvement
   - Check memory usage reduction

## Troubleshooting

### Archive Not Found
```
Error: Could not open shared archive file
```
**Solution**: Ensure archive path is correct and file exists

### Archive Incompatible
```
Error: Shared archive file is incompatible
```
**Solution**: Regenerate archive with current classes

### Archive Creation Failed
```
Error: Failed to create archive
```
**Solution**: Check file permissions and disk space

## Example Workflow

```bash
# 1. Build application
mvn clean package

# 2. First run - create archive
java -XX:ArchiveClassesAtExit=app-cds.jsa \
     -cp target/app.jar \
     com.example.Main

# 3. Verify archive created
ls -lh app-cds.jsa

# 4. Use archive in subsequent runs
java -XX:SharedArchiveFile=app-cds.jsa \
     -cp target/app.jar \
     com.example.Main

# 5. Measure performance improvement
time java -XX:SharedArchiveFile=app-cds.jsa \
          -cp target/app.jar \
          com.example.Main
```

## Performance Impact

Typical improvements:
- **Startup Time**: 10-30% faster
- **Memory**: 5-10% reduction
- **First Request**: Slightly faster (classes pre-loaded)

## Limitations

1. Archive must match classpath
2. Archive is JDK version specific
3. Some classes cannot be archived
4. Archive size grows with application size

## Additional Resources

- [JEP 350: Dynamic CDS Archives](https://openjdk.org/jeps/350)
- Java Documentation: Class-Data Sharing
- Performance Tuning Guide

## Summary

Dynamic CDS Archives provide an easy way to improve application startup performance by archiving application classes at runtime. This feature is particularly beneficial for applications that restart frequently or have long startup times.


# Java 9 Multi-Release JAR Files Guide

Multi-Release JAR (MR JAR) files allow you to package different versions of class files for different Java versions in a single JAR file.

## Overview

Multi-Release JAR files enable:
- **Backward compatibility**: Support multiple Java versions
- **Version-specific optimizations**: Use Java 9+ features when available
- **Gradual migration**: Migrate code incrementally
- **Single JAR**: One JAR file works across multiple Java versions

## JAR Structure

```
myapp.jar
├── META-INF/
│   └── MANIFEST.MF (with Multi-Release: true)
├── com/
│   └── example/
│       └── MyClass.class (Java 8 version - base)
└── META-INF/
    └── versions/
        └── 9/
            └── com/
                └── example/
                    └── MyClass.class (Java 9 version)
```

## Creating a Multi-Release JAR

### Step 1: Create Java 8 Version (Base)

**src/main/java/com/example/MyClass.java** (Java 8 compatible)
```java
package com.example;

import java.util.ArrayList;
import java.util.List;

public class MyClass {
    public String getVersion() {
        return "Java 8 version";
    }
    
    public List<String> createList() {
        List<String> list = new ArrayList<>();
        list.add("Item 1");
        list.add("Item 2");
        list.add("Item 3");
        return list;
    }
}
```

### Step 2: Create Java 9 Version

**src/main/java9/com/example/MyClass.java** (Java 9 optimized)
```java
package com.example;

import java.util.List;

public class MyClass {
    public String getVersion() {
        return "Java 9 version";
    }
    
    // Using Java 9 List.of() factory method
    public List<String> createList() {
        return List.of("Item 1", "Item 2", "Item 3");
    }
}
```

### Step 3: Compile Both Versions

```bash
# Compile Java 8 version
javac -d build/classes/java8 src/main/java/com/example/MyClass.java

# Compile Java 9 version (targeting Java 9)
javac --release 9 -d build/classes/java9 src/main/java9/com/example/MyClass.java
```

### Step 4: Create MANIFEST.MF

**META-INF/MANIFEST.MF**
```
Manifest-Version: 1.0
Multi-Release: true
Main-Class: com.example.Main
```

### Step 5: Create Multi-Release JAR

```bash
# Create JAR with base classes
jar --create --file myapp.jar \
    --manifest META-INF/MANIFEST.MF \
    -C build/classes/java8 .

# Add Java 9 version classes
jar --update --file myapp.jar \
    --release 9 \
    -C build/classes/java9 .
```

### Step 6: Verify JAR Structure

```bash
# List JAR contents
jar --list --file myapp.jar

# Should show:
# META-INF/MANIFEST.MF
# com/example/MyClass.class
# META-INF/versions/9/com/example/MyClass.class
```

## Using Multi-Release JARs

### Running on Java 8
```bash
java -jar myapp.jar
# Uses: com/example/MyClass.class (Java 8 version)
```

### Running on Java 9+
```bash
java -jar myapp.jar
# Uses: META-INF/versions/9/com/example/MyClass.class (Java 9 version)
```

## Maven Configuration

**pom.xml** (Maven example)
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.8.1</version>
            <configuration>
                <source>8</source>
                <target>8</target>
            </configuration>
            <executions>
                <!-- Compile Java 9 version -->
                <execution>
                    <id>compile-java9</id>
                    <phase>compile</phase>
                    <goals>
                        <goal>compile</goal>
                    </goals>
                    <configuration>
                        <source>9</source>
                        <target>9</target>
                        <compileSourceRoots>
                            <compileSourceRoot>${project.basedir}/src/main/java9</compileSourceRoot>
                        </compileSourceRoots>
                        <outputDirectory>${project.build.outputDirectory}/../java9</outputDirectory>
                    </configuration>
                </execution>
            </executions>
        </plugin>
        
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-jar-plugin</artifactId>
            <version>3.2.0</version>
            <configuration>
                <archive>
                    <manifestEntries>
                        <Multi-Release>true</Multi-Release>
                    </manifestEntries>
                </archive>
            </configuration>
        </plugin>
    </plugins>
</build>
```

## Gradle Configuration

**build.gradle** (Gradle example)
```gradle
java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

sourceSets {
    java9 {
        java {
            srcDirs = ['src/main/java9']
        }
    }
}

compileJava9Java {
    sourceCompatibility = 9
    targetCompatibility = 9
}

jar {
    manifest {
        attributes('Multi-Release': 'true')
    }
    
    into('META-INF/versions/9') {
        from sourceSets.java9.output
    }
}
```

## Best Practices

1. **Keep Base Version Compatible**: Base version should work on lowest supported Java version
2. **Version-Specific Features Only**: Only use version-specific code in versioned directories
3. **Test All Versions**: Test JAR on all supported Java versions
4. **Document Supported Versions**: Clearly document which Java versions are supported
5. **Incremental Migration**: Migrate code incrementally, not all at once

## Limitations

- **Class Files Only**: Only class files can be versioned, not resources
- **Package Structure**: Versioned classes must be in same package structure
- **Reflection**: Reflection works on versioned classes correctly
- **Class Loading**: JVM automatically selects correct version at runtime

## Example Use Cases

1. **Using Java 9 Features**: Use `List.of()` in Java 9, `new ArrayList<>()` in Java 8
2. **Performance Optimizations**: Use Java 9+ optimizations when available
3. **API Differences**: Handle API differences between versions
4. **Gradual Migration**: Migrate large codebases incrementally

## Verification

```bash
# Check if JAR is Multi-Release
jar --describe-module --file myapp.jar

# Extract and inspect
jar --extract --file myapp.jar
# Check META-INF/versions/9/ directory
```

## Benefits

✅ **Single JAR**: One JAR works across multiple Java versions  
✅ **Backward Compatible**: Works on older Java versions  
✅ **Forward Compatible**: Uses new features when available  
✅ **Gradual Migration**: Migrate code incrementally  
✅ **Version-Specific Optimizations**: Optimize for each Java version  

## Notes

- Multi-Release JARs require Java 9+ to create, but can run on Java 8+
- The `Multi-Release: true` entry in MANIFEST.MF is required
- Version directories must be under `META-INF/versions/`
- Only class files can be versioned, not resources or other files


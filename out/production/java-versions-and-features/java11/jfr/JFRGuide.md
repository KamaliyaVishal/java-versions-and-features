# Java 11 Java Flight Recorder (JFR) Guide

Java Flight Recorder is a profiling and diagnostics tool that collects runtime information with minimal performance overhead.

## Overview

JFR is a production-ready profiling tool that:
- Has **low overhead** (typically < 1% performance impact)
- Is **production safe** (can be used in production)
- Provides **comprehensive data** (CPU, memory, I/O, locks, exceptions)
- Uses **event-based** recording

## Enabling JFR

### At Startup

```bash
# Basic usage
java -XX:+FlightRecorder -XX:StartFlightRecording=duration=60s,filename=recording.jfr MyApp

# With settings
java -XX:+FlightRecorder \
     -XX:StartFlightRecording=duration=5m,filename=recording.jfr,settings=profile \
     MyApp

# Continuous recording (circular buffer)
java -XX:+FlightRecorder \
     -XX:StartFlightRecording=maxsize=100M,maxage=1h,name=MyRecording \
     MyApp
```

### On Running JVM (jcmd)

```bash
# Find JVM process ID
jps

# Start recording
jcmd <pid> JFR.start duration=60s filename=recording.jfr

# Stop recording
jcmd <pid> JFR.stop

# Dump current recording
jcmd <pid> JFR.dump filename=recording.jfr

# Check recording status
jcmd <pid> JFR.check
```

## Recording Settings

### Predefined Settings

```bash
# Profile (CPU profiling)
-XX:StartFlightRecording=settings=profile

# Default (balanced)
-XX:StartFlightRecording=settings=default

# Low overhead
-XX:StartFlightRecording=settings=low

# High detail
-XX:StartFlightRecording=settings=high
```

### Custom Settings

```bash
# Use custom settings file
-XX:StartFlightRecording=settings=my-custom.jfc
```

## Analyzing Recordings

### Using jfr Tool

```bash
# Print all events
jfr print recording.jfr

# Print specific events
jfr print --events CPULoad,GCPhasePause recording.jfr

# Summary
jfr summary recording.jfr

# Filter by time range
jfr print --start-time 2024-01-01T10:00:00 --end-time 2024-01-01T11:00:00 recording.jfr
```

### Using JDK Mission Control (JMC)

1. Open JMC
2. File → Open → Select recording.jfr
3. Analyze:
   - **General**: Overview of recording
   - **Memory**: Memory usage and GC
   - **Threads**: Thread activity
   - **I/O**: File and socket I/O
   - **Exceptions**: Exception statistics
   - **Method Profiling**: Hot methods

## Programmatic Access

### Basic Recording

```java
import jdk.jfr.*;

Recording recording = new Recording();
recording.start();

// Your application code

recording.stop();
recording.dump(Paths.get("recording.jfr"));
recording.close();
```

### Custom Events

```java
@Label("Custom Event")
@Description("Application-specific event")
static class CustomEvent extends Event {
    @Label("Event ID")
    int eventId;
    
    @Label("Message")
    String message;
    
    @Label("Duration")
    @Timespan(Timespan.MILLISECONDS)
    long duration;
}

// Record event
CustomEvent event = new CustomEvent();
event.eventId = 1;
event.message = "Processing started";
event.duration = 100;
event.commit();
```

## Common Use Cases

### 1. Performance Profiling

```bash
java -XX:+FlightRecorder \
     -XX:StartFlightRecording=duration=5m,settings=profile \
     MyApp
```

### 2. Memory Leak Detection

```bash
java -XX:+FlightRecorder \
     -XX:StartFlightRecording=duration=10m,settings=default \
     MyApp
```

### 3. Production Monitoring

```bash
# Continuous recording with rotation
java -XX:+FlightRecorder \
     -XX:StartFlightRecording=maxsize=200M,maxage=24h \
     MyApp
```

### 4. Exception Tracking

```bash
# Record exceptions
java -XX:+FlightRecorder \
     -XX:StartFlightRecording=settings=default \
     MyApp
```

## Key Events

### CPU Events
- `CPULoad`: CPU utilization
- `CPUInformation`: CPU details
- `ThreadCPULoad`: Per-thread CPU usage

### Memory Events
- `GCPhasePause`: GC pause times
- `ObjectAllocationInNewTLAB`: Object allocation
- `HeapSummary`: Heap statistics

### Thread Events
- `ThreadStart`: Thread creation
- `ThreadEnd`: Thread termination
- `ThreadSleep`: Thread sleeping
- `ThreadPark`: Thread parking

### I/O Events
- `FileRead`: File read operations
- `FileWrite`: File write operations
- `SocketRead`: Socket read operations
- `SocketWrite`: Socket write operations

### Lock Events
- `JavaMonitorWait`: Monitor wait
- `JavaMonitorEnter`: Monitor enter
- `ContendedLock`: Lock contention

## Best Practices

1. **Use Appropriate Settings**: Choose settings based on needs
   - `low` for production monitoring
   - `profile` for performance analysis
   - `default` for general diagnostics

2. **Limit Duration/Size**: Set reasonable limits
   ```bash
   duration=5m,maxsize=100M
   ```

3. **Production Use**: JFR is safe for production
   - Low overhead (< 1%)
   - Minimal impact on application

4. **Regular Analysis**: Analyze recordings regularly
   - Identify bottlenecks
   - Track trends
   - Monitor exceptions

5. **Custom Events**: Add custom events for application-specific metrics

## Troubleshooting

### JFR Not Available

```bash
# Check if JFR is available
java -XX:+FlightRecorder -version

# If not available, use OpenJDK with JFR support
```

### Large Recordings

```bash
# Use circular buffer
-XX:StartFlightRecording=maxsize=200M,maxage=1h

# Or limit duration
-XX:StartFlightRecording=duration=5m
```

### Performance Impact

```bash
# Use low overhead settings
-XX:StartFlightRecording=settings=low

# Or reduce event frequency
-XX:StartFlightRecording=settings=low,event=CPULoad:period=1s
```

## Benefits

✅ **Low Overhead**: < 1% performance impact  
✅ **Production Safe**: Can be used in production  
✅ **Comprehensive**: Covers CPU, memory, I/O, locks  
✅ **Event-Based**: Records events as they occur  
✅ **Easy Analysis**: Tools like JMC for visualization  

## Resources

- JDK Mission Control (JMC): https://jdk.java.net/jmc/
- JFR Documentation: Oracle Java Documentation
- JEP 328: Flight Recorder


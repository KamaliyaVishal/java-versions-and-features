package java9.processapi;

import java.io.IOException;
import java.time.Instant;
import java.util.Optional;

/**
 * Java 9 Process API Improvements
 * Demonstrates ProcessHandle for managing and querying processes
 */
public class ProcessAPIDemo {
    public static void main(String[] args) {
        
        // Get current process
        System.out.println("=== Current Process Information ===");
        ProcessHandle currentProcess = ProcessHandle.current();
        long pid = currentProcess.pid();
        System.out.println("Current Process ID: " + pid);
        
        // Get process info
        ProcessHandle.Info info = currentProcess.info();
        System.out.println("\n=== Process Info ===");
        
        info.command().ifPresent(cmd -> System.out.println("Command: " + cmd));
        info.commandLine().ifPresent(cl -> System.out.println("Command Line: " + cl));
        info.startInstant().ifPresent(start -> System.out.println("Start Time: " + start));
        info.totalCpuDuration().ifPresent(cpu -> System.out.println("CPU Duration: " + cpu));
        info.user().ifPresent(user -> System.out.println("User: " + user));
        
        // List all processes (limited to first 10 for demo)
        System.out.println("\n=== All Processes (First 10) ===");
        ProcessHandle.allProcesses()
            .filter(ph -> ph.info().command().isPresent())
            .limit(10)
            .forEach(ph -> {
                ProcessHandle.Info processInfo = ph.info();
                System.out.println("PID: " + ph.pid() + 
                    " | Command: " + processInfo.command().orElse("N/A"));
            });
        
        // Process builder with redirect
        System.out.println("\n=== Process Builder Example ===");
        try {
            ProcessBuilder pb = new ProcessBuilder("echo", "Hello from Process API")
                .redirectOutput(ProcessBuilder.Redirect.INHERIT);
            
            Process process = pb.start();
            int exitCode = process.waitFor();
            System.out.println("Process exited with code: " + exitCode);
        } catch (IOException | InterruptedException e) {
            System.err.println("Error executing process: " + e.getMessage());
        }
        
        // Check if process is alive
        System.out.println("\n=== Process Status ===");
        System.out.println("Current process is alive: " + currentProcess.isAlive());
        
        // Parent process
        Optional<ProcessHandle> parent = currentProcess.parent();
        parent.ifPresent(p -> System.out.println("Parent PID: " + p.pid()));
    }
}


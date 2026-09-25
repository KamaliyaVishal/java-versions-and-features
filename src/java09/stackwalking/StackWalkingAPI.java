package java9.stackwalking;

import java.lang.StackWalker;

/**
 * Java 9 Stack-Walking API
 * Demonstrates efficient stack trace analysis
 */
public class StackWalkingAPI {
    public static void main(String[] args) {
        StackWalkingAPI demo = new StackWalkingAPI();
        demo.method1();
    }
    
    public void method1() {
        method2();
    }
    
    public void method2() {
        method3();
    }
    
    public void method3() {
        // Get caller class using StackWalker
        StackWalker walker = StackWalker.getInstance(
            StackWalker.Option.RETAIN_CLASS_REFERENCE);
        
        String caller = walker.walk(stream -> 
            stream.skip(1)  // Skip current method
                  .findFirst()
                  .map(StackWalker.StackFrame::getClassName)
                  .orElse("Unknown"));
        
        System.out.println("Caller class: " + caller);
        
        // Filter and print stack frames
        System.out.println("\nStack frames containing 'StackWalking':");
        walker.walk(stream -> {
            stream.filter(frame -> frame.getClassName().contains("StackWalking"))
                  .forEach(frame -> System.out.println(
                      frame.getClassName() + "." + frame.getMethodName() + 
                      " (line " + frame.getLineNumber() + ")"));
            return null;  // walk() requires a return value
        });
        
        // Get all stack frames
        System.out.println("\nAll stack frames:");
        walker.forEach(frame -> 
            System.out.println(frame.getClassName() + "." + 
                             frame.getMethodName() + 
                             " (line " + frame.getLineNumber() + ")"));
    }
}


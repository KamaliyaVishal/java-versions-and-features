package java9.interfaces;

/**
 * Java 9 Private Methods in Interfaces
 * Demonstrates private methods in interfaces for code reuse
 */
public class PrivateMethodsInInterfaces {
    public static void main(String[] args) {
        Calculator calc = new BasicCalculator();
        
        System.out.println("Addition: " + calc.add(10, 5));
        System.out.println("Subtraction: " + calc.subtract(10, 5));
        System.out.println("Operation Name: " + Calculator.getOperationName());
    }
}

interface Calculator {
    default int add(int a, int b) {
        return performOperation(a, b, "+");
    }
    
    default int subtract(int a, int b) {
        return performOperation(a, b, "-");
    }
    
    // Private instance method - shared implementation
    private int performOperation(int a, int b, String op) {
        System.out.println("Performing: " + a + " " + op + " " + b);
        return op.equals("+") ? a + b : a - b;
    }
    
    static String getOperationName() {
        return getDefaultName();
    }
    
    // Private static method - shared implementation
    private static String getDefaultName() {
        return "Basic Calculator";
    }
}

class BasicCalculator implements Calculator {
    // Uses default methods from interface
}


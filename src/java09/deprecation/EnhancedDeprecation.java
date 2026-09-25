package java9.deprecation;

/**
 * Java 9 Enhanced Deprecation
 * Demonstrates enhanced @Deprecated annotation with since and forRemoval attributes
 */
public class EnhancedDeprecation {
    
    /**
     * Old class deprecated since Java 9 and marked for removal
     * 
     * @deprecated This class is deprecated since Java 9 and will be removed in a future version.
     * Use {@link NewClass} instead.
     */
    @Deprecated(since = "9", forRemoval = true)
    public static class OldClass {
        /**
         * Old method that should not be used
         * 
         * @deprecated This method is deprecated since Java 9 and will be removed.
         * Use {@link NewClass#newMethod()} instead.
         */
        @Deprecated(since = "9", forRemoval = true)
        public void oldMethod() {
            System.out.println("This is an old method");
        }
        
        /**
         * Method deprecated but not marked for removal
         * 
         * @deprecated This method is deprecated but still supported.
         * Consider using {@link NewClass#betterMethod()} for new code.
         */
        @Deprecated(since = "9")
        public void deprecatedButNotRemoved() {
            System.out.println("This method is deprecated but not removed");
        }
    }
    
    /**
     * New class to replace OldClass
     */
    public static class NewClass {
        public void newMethod() {
            System.out.println("This is the new recommended method");
        }
        
        public void betterMethod() {
            System.out.println("This is a better method");
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== Enhanced Deprecation Demo ===");
        
        // Using deprecated class (will show warnings)
        System.out.println("\n--- Using Deprecated Class ---");
        OldClass old = new OldClass();
        old.oldMethod(); // Warning: deprecated
        old.deprecatedButNotRemoved(); // Warning: deprecated
        
        // Using new class
        System.out.println("\n--- Using New Class ---");
        NewClass newClass = new NewClass();
        newClass.newMethod();
        newClass.betterMethod();
        
        // Check deprecation programmatically
        System.out.println("\n=== Checking Deprecation Programmatically ===");
        if (OldClass.class.isAnnotationPresent(Deprecated.class)) {
            Deprecated deprecated = OldClass.class.getAnnotation(Deprecated.class);
            System.out.println("OldClass is deprecated");
            System.out.println("  Since: " + deprecated.since());
            System.out.println("  For removal: " + deprecated.forRemoval());
        }
        
        try {
            java.lang.reflect.Method oldMethod = OldClass.class.getMethod("oldMethod");
            if (oldMethod.isAnnotationPresent(Deprecated.class)) {
                Deprecated deprecated = oldMethod.getAnnotation(Deprecated.class);
                System.out.println("\noldMethod() is deprecated");
                System.out.println("  Since: " + deprecated.since());
                System.out.println("  For removal: " + deprecated.forRemoval());
            }
        } catch (NoSuchMethodException e) {
            System.err.println("Method not found: " + e.getMessage());
        }
        
        System.out.println("\n=== Enhanced Deprecation Features ===");
        System.out.println("1. 'since' attribute: Indicates when the element was deprecated");
        System.out.println("2. 'forRemoval' attribute: Indicates if the element will be removed");
        System.out.println("3. Better tooling support: IDEs can show deprecation info");
        System.out.println("4. Compiler warnings: More informative deprecation warnings");
        
        System.out.println("\n=== Best Practices ===");
        System.out.println("- Use @Deprecated(since=\"X\") to indicate version");
        System.out.println("- Use forRemoval=true for elements that will be removed");
        System.out.println("- Always provide @deprecated javadoc with replacement");
        System.out.println("- Plan migration path before removing deprecated elements");
    }
}


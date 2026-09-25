package java6.compiler;

import javax.tools.*;
import java.util.Arrays;
import java.io.File;

/**
 * Java 6 Compiler API Example Demonstrates programmatic compilation
 */
public class CompilerAPIExample
{
	public static void main(String[] args)
	{
		System.out.println("=== Java 6 Compiler API ===\n");

		System.out.println("The Compiler API allows programmatic compilation of Java source code.\n");

		System.out.println("Example code structure:");
		System.out.println("""
				import javax.tools.*;
				import java.util.Arrays;
				
				public class CompilerAPIExample {
				    public static void main(String[] args) {
				        // 1. Get Java compiler
				        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
				        
				        if (compiler == null) {
				            System.err.println("Compiler not available");
				            return;
				        }
				        
				        // 2. Get file manager
				        StandardJavaFileManager fileManager = 
				            compiler.getStandardFileManager(null, null, null);
				        
				        // 3. Get compilation units
				        Iterable<? extends JavaFileObject> compilationUnits = 
				            fileManager.getJavaFileObjectsFromStrings(
				                Arrays.asList("MyClass.java")
				            );
				        
				        // 4. Create compilation task
				        JavaCompiler.CompilationTask task = compiler.getTask(
				            null,           // Writer for compiler output
				            fileManager,    // File manager
				            null,           // Diagnostic listener
				            null,           // Compiler options
				            null,           // Classes for annotation processing
				            compilationUnits
				        );
				        
				        // 5. Execute compilation
				        boolean success = task.call();
				        
				        if (success) {
				            System.out.println("Compilation successful");
				        } else {
				            System.out.println("Compilation failed");
				        }
				        
				        // 6. Close file manager
				        fileManager.close();
				    }
				}
				""");

		System.out.println("\nKey Features:");
		System.out.println("- Programmatic compilation");
		System.out.println("- Tool integration");
		System.out.println("- IDE support");
		System.out.println("- Build tool support");
		System.out.println("- Annotation processing");

		System.out.println("\nUse Cases:");
		System.out.println("- Build tools (Maven, Gradle)");
		System.out.println("- IDEs (Eclipse, IntelliJ)");
		System.out.println("- Code generation tools");
		System.out.println("- Dynamic compilation");

		System.out.println("\nBenefits:");
		System.out.println("- Compile code programmatically");
		System.out.println("- Better tool integration");
		System.out.println("- IDE features");
		System.out.println("- Build automation");
	}
}


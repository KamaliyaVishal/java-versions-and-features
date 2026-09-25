package java6.scripting;

import javax.script.*;

/**
 * Java 6 Scripting Support (JSR 223) Example Demonstrates script execution
 * NOTE: Requires scripting engine (e.g., Nashorn for JavaScript)
 */
public class ScriptingExample
{
	public static void main(String[] args)
	{
		System.out.println("=== Java 6 Scripting Support (JSR 223) ===\n");

		try
		{
			ScriptEngineManager manager = new ScriptEngineManager();

			System.out.println("1. Available Script Engines");
			System.out.println("--------------------------");
			manager.getEngineFactories().forEach(factory -> {
				System.out.println("  - " + factory.getLanguageName() + " (" + factory.getEngineName() + ")");
			});

			System.out.println("\n2. Execute JavaScript");
			System.out.println("--------------------");
			ScriptEngine engine = manager.getEngineByName("JavaScript");
			if (engine != null)
			{
				engine.eval("print('Hello from JavaScript');");
				Object result = engine.eval("2 + 3");
				System.out.println("2 + 3 = " + result);
			}
			else
			{
				System.out.println("JavaScript engine not available");
				System.out.println("Example code structure:");
				System.out.println("""
						ScriptEngineManager manager = new ScriptEngineManager();
						ScriptEngine engine = manager.getEngineByName("JavaScript");
						
						// Execute script
						engine.eval("print('Hello from JavaScript');");
						
						// Evaluate expression
						Object result = engine.eval("2 + 3");
						
						// Bind variables
						engine.put("x", 10);
						engine.put("y", 20);
						Object sum = engine.eval("x + y");
						""");
			}

			System.out.println("\n3. Variable Binding");
			System.out.println("-----------------");
			if (engine != null)
			{
				engine.put("name", "Java");
				engine.put("version", 6);
				engine.eval("print('Language: ' + name + ', Version: ' + version);");
			}

			System.out.println("\n4. Function Invocation");
			System.out.println("---------------------");
			if (engine != null)
			{
				engine.eval("function greet(name) { return 'Hello, ' + name; }");
				Invocable invocable = (Invocable) engine;
				Object result = invocable.invokeFunction("greet", "World");
				System.out.println("Function result: " + result);
			}

		}
		catch (ScriptException e)
		{
			System.err.println("Script error: " + e.getMessage());
		}
		catch (NoSuchMethodException e)
		{
			System.err.println("Method not found: " + e.getMessage());
		}

		System.out.println("\nKey Features:");
		System.out.println("- Multiple scripting languages");
		System.out.println("- Script execution");
		System.out.println("- Variable binding");
		System.out.println("- Function invocation");
		System.out.println("- Script compilation");

		System.out.println("\nSupported Languages:");
		System.out.println("- JavaScript (Nashorn in Java 8+)");
		System.out.println("- Python (with Jython)");
		System.out.println("- Ruby (with JRuby)");
		System.out.println("- Groovy");
		System.out.println("- And more...");

		System.out.println("\nNote: Scripting engines may not be available in all Java distributions.");
		System.out.println("Nashorn (JavaScript) was available in Java 8-14 but removed in Java 15+.");
	}
}


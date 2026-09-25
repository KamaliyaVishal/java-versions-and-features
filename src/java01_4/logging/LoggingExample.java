package java1_4.logging;

import java.util.logging.*;

/**
 * Java 1.4 Logging API Example Demonstrates java.util.logging
 */
public class LoggingExample
{
	private static final Logger logger = Logger.getLogger(LoggingExample.class.getName());

	public static void main(String[] args)
	{
		System.out.println("=== Java 1.4 Logging API ===\n");

		// Set log level
		logger.setLevel(Level.INFO);

		System.out.println("1. Basic Logging");
		System.out.println("---------------");
		logger.severe("This is a SEVERE message");
		logger.warning("This is a WARNING message");
		logger.info("This is an INFO message");
		logger.config("This is a CONFIG message");
		logger.fine("This is a FINE message");
		logger.finer("This is a FINER message");
		logger.finest("This is a FINEST message");

		System.out.println("\n2. Log Levels");
		System.out.println("------------");
		System.out.println("SEVERE (highest)");
		System.out.println("WARNING");
		System.out.println("INFO");
		System.out.println("CONFIG");
		System.out.println("FINE");
		System.out.println("FINER");
		System.out.println("FINEST (lowest)");

		System.out.println("\n3. Logging with Parameters");
		System.out.println("------------------------");
		String user = "John";
		int age = 30;
		logger.log(Level.INFO, "User: {0}, Age: {1}", new Object[]{user, age});

		System.out.println("\n4. Exception Logging");
		System.out.println("-------------------");
		try
		{
			int result = 10 / 0;
		}
		catch (ArithmeticException e)
		{
			logger.log(Level.SEVERE, "Division by zero", e);
		}

		System.out.println("\n5. Custom Handler");
		System.out.println("---------------");
		Logger customLogger = Logger.getLogger("CustomLogger");
		ConsoleHandler handler = new ConsoleHandler();
		handler.setLevel(Level.ALL);
		customLogger.addHandler(handler);
		customLogger.setLevel(Level.ALL);
		customLogger.info("Custom handler message");

		System.out.println("\nKey Features:");
		System.out.println("- Multiple log levels");
		System.out.println("- Handlers for output destinations");
		System.out.println("- Formatters for message formatting");
		System.out.println("- Configurable logging");
		System.out.println("- Standard API");

		System.out.println("\nHandlers:");
		System.out.println("- ConsoleHandler: Output to console");
		System.out.println("- FileHandler: Output to file");
		System.out.println("- SocketHandler: Output to network");

		System.out.println("\nBenefits:");
		System.out.println("- Standard logging API");
		System.out.println("- Configurable");
		System.out.println("- Multiple output destinations");
		System.out.println("- Performance levels");
	}
}


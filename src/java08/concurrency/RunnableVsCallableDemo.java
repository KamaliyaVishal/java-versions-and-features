package java8.concurrency;

import java.util.concurrent.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Demonstrates the use of Runnable and Callable in Java 8.
 * <p>
 * This class provides examples of:
 * <ul>
 *   <li>Using {@code Runnable} to create a task that does not return a value.</li>
 *   <li>Using {@code Callable} to create a task that returns a value.</li>
 *   <li>Submitting tasks to an executor service and processing the results.</li>
 * </ul>
 */
public class RunnableVsCallableDemo
{

	public static void main(String[] args)
	{
		// Creating thread pool
		ExecutorService executor = Executors.newFixedThreadPool(4);

		// Lists to store Future objects
		List<Future<Integer>> callableResults = new ArrayList<>();
		List<Future<?>> runnableResults = new ArrayList<>();

		try
		{
			// 1. Runnable Example
			Runnable runnableTask = new Runnable()
			{
				@Override
				public void run()
				{
					try
					{
						System.out.println("Runnable task is running on thread: "
								+ Thread.currentThread().getName());
						Thread.sleep(2000);
						// Cannot return value
						// Cannot throw checked exception
					}
					catch (InterruptedException e)
					{
						Thread.currentThread().interrupt();
					}
				}
			};

			// 2. Callable Example
			Callable<Integer> callableTask = new Callable<Integer>()
			{
				@Override
				public Integer call() throws Exception
				{
					System.out.println("Callable task is running on thread: "
							+ Thread.currentThread().getName());
					Thread.sleep(2000);
					return 42; // Can return value
				}
			};

			// 3. Submitting multiple tasks
			for (int i = 0; i < 3; i++)
			{
				// Submitting Runnable tasks
				Future<?> runnableFuture = executor.submit(runnableTask);
				runnableResults.add(runnableFuture);

				// Submitting Callable tasks
				Future<Integer> callableFuture = executor.submit(callableTask);
				callableResults.add(callableFuture);
			}

			// 4. Processing Runnable results
			System.out.println("\nProcessing Runnable results:");
			for (Future<?> future : runnableResults)
			{
				try
				{
					future.get(); // Returns null for Runnable
					System.out.println("Runnable task completed");
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}

			// 5. Processing Callable results
			System.out.println("\nProcessing Callable results:");
			for (Future<Integer> future : callableResults)
			{
				try
				{
					Integer result = future.get(); // Returns actual value
					System.out.println("Callable task result: " + result);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}

		}
		finally
		{
			executor.shutdown();
		}
	}

	// Example of practical use cases
	static class PracticalExamples
	{
		// Runnable use case - File processing without return
		static class FileProcessingTask implements Runnable
		{
			@Override
			public void run()
			{
				// Process file without returning result
				System.out.println("Processing file...");
			}
		}

		// Callable use case - Data computation with return
		static class DataComputationTask implements Callable<Double>
		{
			@Override
			public Double call() throws Exception
			{
				// Compute and return result
				return Math.random() * 100;
			}
		}

		// Example with exception handling
		static class ExceptionHandlingExample
		{
			public static void demonstrateExceptionHandling()
			{
				ExecutorService executor = Executors.newSingleThreadExecutor();

				// Runnable with try-catch
				Runnable runnableWithException = () -> {
					try
					{
						throw new RuntimeException("Runtime Exception in Runnable");
					}
					catch (RuntimeException e)
					{
						System.out.println("Caught in Runnable: " + e.getMessage());
					}
				};

				// Callable with declared exception
				Callable<String> callableWithException = () -> {
					if (true)
					{
						throw new Exception("Checked Exception in Callable");
					}
					return "Success";
				};

				executor.submit(runnableWithException);
				Future<String> future = executor.submit(callableWithException);

				try
				{
					future.get();
				}
				catch (Exception e)
				{
					System.out.println("Caught from Callable: " + e.getMessage());
				}
				finally
				{
					executor.shutdown();
				}
			}
		}
	}
}


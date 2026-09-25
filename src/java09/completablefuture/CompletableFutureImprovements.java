package java9.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.Executor;

/**
 * Java 9 CompletableFuture Improvements
 * Demonstrates new methods added to CompletableFuture in Java 9: - completeOnTimeout(): Complete with default value on timeout - orTimeout(): Complete exceptionally with TimeoutException on timeout - delayedExecutor(): Create executor with delay - newIncompleteFuture(): Override for custom CompletableFuture subclasses
 */
public class CompletableFutureImprovements
{

	public static void main(String[] args) throws Exception
	{

		System.out.println("=== CompletableFuture.completeOnTimeout() ===");

		// 1. completeOnTimeout() - Complete with default value on timeout
		CompletableFuture<String> future1 = CompletableFuture
				.supplyAsync(() -> {
					try
					{
						System.out.println("Long running task started...");
						Thread.sleep(3000);  // Simulate long operation
						return "Actual Result";
					}
					catch (InterruptedException e)
					{
						Thread.currentThread().interrupt();
						return null;
					}
				})
				.completeOnTimeout("Timeout Default Value", 1, TimeUnit.SECONDS);

		String result1 = future1.join();
		System.out.println("Result: " + result1);
		// Output: "Timeout Default Value" (because operation takes 3s but timeout is 1s)

		// 2. completeOnTimeout() - Operation completes before timeout
		CompletableFuture<String> future2 = CompletableFuture
				.supplyAsync(() -> {
					try
					{
						Thread.sleep(500);  // Fast operation
						return "Fast Result";
					}
					catch (InterruptedException e)
					{
						Thread.currentThread().interrupt();
						return null;
					}
				})
				.completeOnTimeout("Timeout", 2, TimeUnit.SECONDS);

		String result2 = future2.join();
		System.out.println("Result: " + result2);
		// Output: "Fast Result" (completes before timeout)

		System.out.println("\n=== CompletableFuture.orTimeout() ===");

		// 3. orTimeout() - Throw TimeoutException on timeout
		CompletableFuture<String> future3 = CompletableFuture
				.supplyAsync(() -> {
					try
					{
						System.out.println("Slow task started...");
						Thread.sleep(3000);
						return "Success";
					}
					catch (InterruptedException e)
					{
						Thread.currentThread().interrupt();
						return null;
					}
				})
				.orTimeout(1, TimeUnit.SECONDS);

		try
		{
			String result3 = future3.join();
			System.out.println("Result: " + result3);
		}
		catch (CompletionException e)
		{
			if (e.getCause() instanceof TimeoutException)
			{
				System.out.println("Operation timed out: " + e.getCause().getMessage());
			}
			else
			{
				System.out.println("Other error: " + e.getCause());
			}
		}

		// 4. orTimeout() - Handle timeout gracefully
		CompletableFuture<String> future4 = CompletableFuture
				.supplyAsync(() -> {
					try
					{
						Thread.sleep(2000);
						return "Data";
					}
					catch (InterruptedException e)
					{
						Thread.currentThread().interrupt();
						return null;
					}
				})
				.orTimeout(1, TimeUnit.SECONDS)
				.exceptionally(ex -> {
					if (ex.getCause() instanceof TimeoutException)
					{
						return "Fallback value due to timeout";
					}
					return "Error occurred";
				});

		String result4 = future4.join();
		System.out.println("Result with fallback: " + result4);

		System.out.println("\n=== CompletableFuture.delayedExecutor() ===");

		// 5. delayedExecutor() - Execute after delay
		Executor delayedExecutor = CompletableFuture.delayedExecutor(2, TimeUnit.SECONDS);

		System.out.println("Scheduling delayed task...");
		long startTime = System.currentTimeMillis();

		CompletableFuture<String> future5 = CompletableFuture
				.supplyAsync(() -> {
					long elapsed = System.currentTimeMillis() - startTime;
					return "Task executed after " + elapsed + " ms";
				}, delayedExecutor);

		String result5 = future5.get();
		System.out.println("Result: " + result5);
		// Output: Task executed after ~2000 ms

		// 6. delayedExecutor() - Chain with other operations
		CompletableFuture<String> future6 = CompletableFuture
				.supplyAsync(() -> "Initial", delayedExecutor)
				.thenApply(s -> s + " -> Processed")
				.thenApply(String::toUpperCase);

		System.out.println("Chained delayed task result: " + future6.get());

		System.out.println("\n=== Practical Examples ===");

		// 7. Timeout with multiple operations
		CompletableFuture<String> apiCall = CompletableFuture
				.supplyAsync(() -> {
					try
					{
						Thread.sleep(2500);  // Simulate API call
						return "API Response";
					}
					catch (InterruptedException e)
					{
						Thread.currentThread().interrupt();
						return null;
					}
				})
				.completeOnTimeout("Cached Response", 2, TimeUnit.SECONDS);

		System.out.println("API result: " + apiCall.join());

		// 8. Retry with delay
		CompletableFuture<String> retryTask = CompletableFuture
				.supplyAsync(() -> {
					System.out.println("Attempt 1");
					throw new RuntimeException("Failed");
				})
				.exceptionally(ex -> {
					System.out.println("Retrying after delay...");
					return null;
				})
				.thenCompose(v -> CompletableFuture
						.supplyAsync(() -> "Success on retry",
								CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS)));

		try
		{
			System.out.println("Retry result: " + retryTask.get(3, TimeUnit.SECONDS));
		}
		catch (Exception e)
		{
			System.out.println("Retry failed: " + e.getMessage());
		}

		System.out.println("\n=== Benefits ===");
		System.out.println("1. completeOnTimeout(): Graceful timeout handling with default values");
		System.out.println("2. orTimeout(): Fail-fast timeout with exceptions");
		System.out.println("3. delayedExecutor(): Schedule tasks with delays");
		System.out.println("4. Better control over asynchronous operations");
		System.out.println("5. Improved error handling and timeout management");

		System.out.println("\n=== Comparison: Java 8 vs Java 9 ===");
		System.out.println("Java 8: Manual timeout handling required");
		System.out.println("  CompletableFuture.supplyAsync(...)");
		System.out.println("    .get(1, TimeUnit.SECONDS);  // Blocking");
		System.out.println("\nJava 9: Non-blocking timeout handling");
		System.out.println("  CompletableFuture.supplyAsync(...)");
		System.out.println("    .completeOnTimeout(default, 1, TimeUnit.SECONDS);  // Non-blocking");
	}
}


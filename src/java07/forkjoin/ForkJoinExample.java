package java7.forkjoin;

import java.util.concurrent.*;

/**
 * Java 7 Fork/Join Framework Example Demonstrates parallel processing
 */
public class ForkJoinExample
{
	public static void main(String[] args)
	{
		System.out.println("=== Java 7 Fork/Join Framework ===\n");

		// Create array for summing
		int[] array = new int[10000];
		for (int i = 0; i < array.length; i++)
		{
			array[i] = i + 1;
		}

		System.out.println("1. Fork/Join Sum Calculation");
		System.out.println("---------------------------");
		ForkJoinPool pool = new ForkJoinPool();
		SumTask task = new SumTask(array, 0, array.length);
		long result = pool.invoke(task);
		System.out.println("Sum of 1 to 10000: " + result);
		System.out.println("Expected: " + (10000L * 10001L / 2L));

		System.out.println("\n2. Fork/Join Concept");
		System.out.println("------------------");
		System.out.println("""
				Fork/Join Framework:
				- Divide task into smaller subtasks
				- Fork: Split task and execute in parallel
				- Join: Combine results from subtasks
				- Work-stealing algorithm for load balancing
				""");

		System.out.println("\nKey Features:");
		System.out.println("- Divide-and-conquer parallelism");
		System.out.println("- Work-stealing algorithm");
		System.out.println("- Efficient for recursive tasks");
		System.out.println("- Automatic load balancing");

		System.out.println("\nUse Cases:");
		System.out.println("- Parallel sorting");
		System.out.println("- Parallel searching");
		System.out.println("- Parallel processing");
		System.out.println("- Recursive algorithms");

		System.out.println("\nBenefits:");
		System.out.println("- Better CPU utilization");
		System.out.println("- Automatic load balancing");
		System.out.println("- Efficient for recursive tasks");
		System.out.println("- Easy to use");
	}
}

// RecursiveTask for computing sum
class SumTask extends RecursiveTask<Long>
{
	private static final int THRESHOLD = 1000;
	private int[] array;
	private int start;
	private int end;

	public SumTask(int[] array, int start, int end)
	{
		this.array = array;
		this.start = start;
		this.end = end;
	}

	@Override
	protected Long compute()
	{
		int length = end - start;
		if (length < THRESHOLD)
		{
			// Direct computation for small arrays
			long sum = 0;
			for (int i = start; i < end; i++)
			{
				sum += array[i];
			}
			return sum;
		}
		else
		{
			// Fork: Split into subtasks
			int mid = start + length / 2;
			SumTask leftTask = new SumTask(array, start, mid);
			SumTask rightTask = new SumTask(array, mid, end);

			// Fork left task (execute in parallel)
			leftTask.fork();

			// Compute right task and join left task
			long rightResult = rightTask.compute();
			long leftResult = leftTask.join();

			// Combine results
			return leftResult + rightResult;
		}
	}
}


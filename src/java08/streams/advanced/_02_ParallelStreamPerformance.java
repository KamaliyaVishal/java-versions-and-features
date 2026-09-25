package java8.streams.advanced;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class _02_ParallelStreamPerformance
{
	public static void main(String[] args)
	{
		List<Integer> numbers = IntStream.range(1, 1000).boxed()
				.collect(Collectors.toList());

		// Sequential
		long seqStart = System.currentTimeMillis();
		numbers.stream().forEach(n -> compute(n));
		System.out.println("Sequential: " + (System.currentTimeMillis() - seqStart) +"ms");

		// Parallel
		long parStart = System.currentTimeMillis();
		numbers.parallelStream().forEach(n -> compute(n));
		System.out.println("Parallel: " + (System.currentTimeMillis() - parStart)+"ms");
	}

	private static void compute(int number)
	{
		// Simulate computation
		try
		{
			Thread.sleep(1);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}


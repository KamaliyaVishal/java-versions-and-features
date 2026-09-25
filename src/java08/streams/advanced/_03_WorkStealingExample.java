package java8.streams.advanced;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class _03_WorkStealingExample
{
	public static void main(String[] args)
	{
		List<Integer> numbers = IntStream.range(1, 11000).boxed()
				.collect(Collectors.toList());

		long start = System.currentTimeMillis();

		// Processing with parallel stream
		numbers.parallelStream()
				.filter(n -> n % 2 == 0)
				.map(n -> n * 2)
				.forEach(n -> {
					// Simulating time-consuming task
					try
					{
						Thread.sleep(1);
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				});

		System.out.println("Time taken: " + (System.currentTimeMillis() - start) + "ms");
	}
}


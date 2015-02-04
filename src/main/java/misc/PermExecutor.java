package misc;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

/**
 * 主结构
 * 
 * @author Destiny
 * 
 */

//Time Elapsed 6.196000s. Threads: 5, Task Threshold: 8. Total: 479001600
//Time Elapsed 4.088000s. Threads: 5, Task Threshold: 9. Total: 479001600
//Time Elapsed 4.195000s. Threads: 5, Task Threshold: 10. Total: 479001600
//Time Elapsed 4.354000s. Threads: 5, Task Threshold: 11. Total: 479001600
//Time Elapsed 7.643000s. Threads: 5, Task Threshold: 12. Total: 479001600
//Time Elapsed 4.127000s. Threads: 6, Task Threshold: 8. Total: 479001600
//Time Elapsed 4.256000s. Threads: 6, Task Threshold: 9. Total: 479001600
//Time Elapsed 4.243000s. Threads: 6, Task Threshold: 10. Total: 479001600
//Time Elapsed 4.386000s. Threads: 6, Task Threshold: 11. Total: 479001600
//Time Elapsed 7.597000s. Threads: 6, Task Threshold: 12. Total: 479001600
//Time Elapsed 4.176000s. Threads: 8, Task Threshold: 8. Total: 479001600
//Time Elapsed 4.240000s. Threads: 8, Task Threshold: 9. Total: 479001600
//Time Elapsed 4.298000s. Threads: 8, Task Threshold: 10. Total: 479001600
//Time Elapsed 4.293000s. Threads: 8, Task Threshold: 11. Total: 479001600
//Time Elapsed 7.559000s. Threads: 8, Task Threshold: 12. Total: 479001600

public class PermExecutor {

	public static AtomicInteger taskCounter;

	static {
		taskCounter = new AtomicInteger(1);
	}

	public static void main(String... args) throws InterruptedException,
			ExecutionException {

		int threads = Integer.valueOf(args[0]);
		int taskThreshold = Integer.valueOf(args[1]);

		long timeStart = System.currentTimeMillis();

		// 11! = 39916800 1.163s / 5 threads
		String target = "ABCDEFGHIJKL";

		ExecutorService es = Executors.newFixedThreadPool(threads);
		CompletionService<Integer> cs = new ExecutorCompletionService<Integer>(
				es);

		Callable<Integer> task = new PermTask("", target, taskThreshold, cs);

		cs.submit(task);

		Future<Integer> result;
		int total = 0;
		while (0 <= taskCounter.decrementAndGet()) {
			result = cs.take();
			Integer relInt = result.get();

			if (null != relInt) {
				total += relInt;
			}
		}

		es.shutdown();

		long timeEnd = System.currentTimeMillis();
		long timeElapsed = timeEnd - timeStart;

		System.out.printf("Time Elapsed %fs. Threads: %d, Task Threshold: %d. Total: %d%n",
				timeElapsed / 1000.0, threads, taskThreshold, total);

		taskCounter.set(1);
	}

	@Test
	public void testMain() throws InterruptedException, ExecutionException {
//		PermExecutor.main("5", "5");
//		PermExecutor.main("5", "6");
//		PermExecutor.main("5", "7");
		PermExecutor.main("5", "8");
		PermExecutor.main("5", "9");
		PermExecutor.main("5", "10");
		PermExecutor.main("5", "11");
		PermExecutor.main("5", "12");
//		PermExecutor.main("5", "13");
//		PermExecutor.main("5", "14");
		
		PermExecutor.main("6", "8");
		PermExecutor.main("6", "9");
		PermExecutor.main("6", "10");
		PermExecutor.main("6", "11");
		PermExecutor.main("6", "12");
		
		PermExecutor.main("8", "8");
		PermExecutor.main("8", "9");
		PermExecutor.main("8", "10");
		PermExecutor.main("8", "11");
		PermExecutor.main("8", "12");
	}

}

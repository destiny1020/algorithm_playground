package graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import chap1.LinkedQueue;
import chap1.LinkedStack;

/**
 * 2012-7-10 Paths Creation has problem, should set a flag to distinguish the
 * ladder and snake !
 * 
 * @author Destiny
 * 
 */
public class SnakeAndLadder
{
	private static int DICE_MAX = 6;
	private int size;

	// record the minimum steps to arrive the space at index
	private int[] space_min_count;

	// record the paths of minimum
	private int[] space_trace;

	private int snake_number;
	private int ladder_number;

	private Map<Integer, Integer> snakesAndLadders;
	private Map<Integer, Integer> reverse_snakesAndLadders;

	// BFS-like strategy
	private LinkedQueue<Integer> queue;

	public SnakeAndLadder(Scanner scanner)
	{
		// read the space
		size = scanner.nextInt();

		// init the space and minimum steps space
		space_min_count = new int[size + 1];
		space_trace = new int[size + 1];

		// init the arrays
		Arrays.fill(space_min_count, Integer.MAX_VALUE);
		Arrays.fill(space_trace, -1);

		// read snake number
		snake_number = scanner.nextInt();

		// read ladder number
		ladder_number = scanner.nextInt();

		// init the maps
		snakesAndLadders = new HashMap<Integer, Integer>();
		reverse_snakesAndLadders = new HashMap<Integer, Integer>();

		// read concrete snakes and ladders info
		int start, end;
		for (int i = 0; i < snake_number + ladder_number; i++)
		{
			start = scanner.nextInt();
			end = scanner.nextInt();
			snakesAndLadders.put(start, end);
			reverse_snakesAndLadders.put(end, start);
		}

		// init the queue
		queue = new LinkedQueue<Integer>();

		queue.enqueue(1);
		space_min_count[1] = 0;
		space_trace[1] = -1;

		process();
	}

	private void process()
	{
		while (!queue.isEmpty())
		{
			int current = queue.dequeue();
			int current_steps = space_min_count[current];

			for (int i = 1; i <= DICE_MAX; i++)
			{
				if ((current + i) <= (size))
				{
					// update the arrays
					int mayConverted = wishLucky(current + i);
					if (space_min_count[mayConverted] > (current_steps + 1))
					{
						space_min_count[mayConverted] = (current_steps + 1);
						space_trace[mayConverted] = current;

						// enqueue
						queue.enqueue(mayConverted);
					}
				}
			}
		}
	}

	private int wishLucky(int i)
	{
		Integer rst = snakesAndLadders.get(i);
		// consider ladders first
		if (null != rst)
		{
			return rst;
		}

		return i;
	}

	public int minimumSteps()
	{
		return space_min_count[size];
	}

	public Iterable<Integer> getMinimumPaths()
	{
		if (minimumSteps() < Integer.MAX_VALUE)
		{
			LinkedStack<Integer> paths = new LinkedStack<Integer>();

			int end = size;
			paths.push(end);
			Integer special = null;
			while (true)
			{
				end = space_trace[end];
				if (-1 == end)
				{
					return paths;
				}

				special = reverse_snakesAndLadders.get(end);
				paths.push(end);
				if (null != special)
				{
					paths.push(special);
					special = null;
				}
			}
		}

		return null;
	}

	public int[] getSpace_min_count()
	{
		return space_min_count;
	}

	public int[] getSpace_trace()
	{
		return space_trace;
	}
}

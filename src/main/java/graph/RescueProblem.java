package graph;

import graph.BoneTemptation.Direction;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

import chap1.LinkedStack;

/**
 * ZOJ-1649 Rescue BFS-Solution
 * 
 * @author Destiny
 * 
 */
public class RescueProblem
{
	private String dataFile;

	private int maze_length;
	private int maze_width;

	private int start_x;
	private int start_y;
	private Point start;

	private int end_x;
	private int end_y;
	private Point end;

	private char[][] maze;
	private int[][] price;

	private Map<Point, Point> parentLink;
	private Queue<Point> priQueue;

	public RescueProblem(String dataFile) throws FileNotFoundException
	{
		this.dataFile = dataFile;

		init();

		process();
	}

	private void init() throws FileNotFoundException
	{
		File f = new File(this.dataFile);

		Scanner scanner = new Scanner(f);

		// read maze size
		maze_width = scanner.nextInt();
		maze_length = scanner.nextInt();

		maze = new char[maze_width][maze_length];
		price = new int[maze_width][maze_length];

		priQueue = new PriorityQueue<Point>();
		parentLink = new HashMap<Point, Point>();

		// init the price array
		for (int i = 0; i < maze_width; i++)
		{
			Arrays.fill(price[i], Integer.MAX_VALUE);
		}

		scanner.nextLine();

		for (int i = 0; i < maze_width; i++)
		{
			String line = scanner.nextLine();
			for (int j = 0; j < maze_length; j++)
			{
				maze[i][j] = line.charAt(j);

				switch (maze[i][j])
					{
					case 'a':
						end_x = i;
						end_y = j;
						break;
					case 'r':
						start_x = i;
						start_y = j;
						price[i][j] = 0;
						priQueue.add(new Point(i, j, 0));
						break;
					default:
						break;
					}
			}
		}
	}

	private void process()
	{
		while (!priQueue.isEmpty())
		{
			Point current = priQueue.poll();

			for (Direction dir : Direction.values())
			{
				int targetX = current.getX() + dir.getDeltaX();
				int targetY = current.getY() + dir.getDeltaY();

				// check whether the target point is valid
				if (targetX >= 0 && targetX < maze_width && targetY >= 0
						&& targetY < maze_length)
				{
					int deltaWeight = calWeight(targetX, targetY);
					if (-1 != deltaWeight
							&& current.weight + deltaWeight < price[targetX][targetY])
					{
						// update the path
						int newPrice = current.weight + deltaWeight;
						price[targetX][targetY] = newPrice;

						// add target to the pri queue
						Point target = new Point(targetX, targetY, newPrice);
						priQueue.add(target);
						
						// update the parent link map
						parentLink.put(target, current);
					}
				}
			}
		}
	}

	public boolean isRescuable()
	{
		return (Integer.MAX_VALUE != price[end_x][end_y]);
	}

	public int minSteps()
	{
		return price[end_x][end_y];
	}
	
	public Iterable<Point> getPaths()
	{
		if(isRescuable())
		{
			LinkedStack<Point> paths = new LinkedStack<Point>();
			Point end = new Point(end_x, end_y, 0);
			paths.push(end);
			
			Point previous = parentLink.get(end);
			
			while(null != previous)
			{
				paths.push(previous);
				previous = parentLink.get(previous);
			}
			
			return paths;
		}
		
		return null;
	}

	private int calWeight(int targetX, int targetY)
	{
		char target = maze[targetX][targetY];

		// default: wall
		int weight = -1;

		switch (target)
			{
			case 'a':
			case '.':
				weight = 1;
				break;
			case 'x':
				weight = 2;
				break;
			default:
				break;
			}

		return weight;
	}

	public static class Point implements Comparable<Point>
	{
		private final int x;
		private final int y;
		private final int weight;

		public Point(int x, int y, int weight)
		{
			this.x = x;
			this.y = y;
			this.weight = weight;
		}

		@Override
		public String toString()
		{
			return "X: " + x + " - Y: " + y;
		}
		
		@Override
		public boolean equals(Object o)
		{
			if(o == this)
				return true;
			
			if(o instanceof Point)
			{
				Point other = (Point)o;
				
				if(other.x == this.x && other.y == this.y)
				{
					return true;
				} else
				{
					return false;
				}
			}
			
			return false;
		}
		
		@Override
		public int hashCode()
		{
			return this.x * 31 + y;
		}

		public int getX()
		{
			return x;
		}

		public int getY()
		{
			return y;
		}

		public int getWeight()
		{
			return weight;
		}

		@Override
		public int compareTo(Point other)
		{
			return this.weight - other.weight;
		}
	}
}

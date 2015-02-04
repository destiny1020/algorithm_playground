package graph;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import chap1.LinkedStack;

/**
 * ZOJ-2110 DFS Algorithm
 * 
 * @author Destiny
 * 
 */
public class BoneTemptation
{
	private String dataFile;

	private int maze_length;
	private int maze_width;
	private int targetTime;

	private int actualTime;

	private int start_x;
	private int start_y;

	private int end_x;
	private int end_y;

	private char[][] maze;
	private boolean isSuccessful;

	private LinkedStack<Point> records;

	public BoneTemptation(String dataFile) throws FileNotFoundException
	{
		this.dataFile = dataFile;

		init();
	}

	private void init() throws FileNotFoundException
	{
		File f = new File(this.dataFile);

		Scanner scanner = new Scanner(f);

		// read maze size and target time
		int[] ints = new int[3];
		for (int i = 0; i < 3; i++)
		{
			if (scanner.hasNextInt())
			{
				ints[i] = scanner.nextInt();
			} else
			{
				throw new RuntimeException("Data Format Error !");
			}
		}

		this.maze_length = ints[0];
		this.maze_width = ints[1];
		this.targetTime = ints[2];
		scanner.nextLine();

		// init the maze
		this.maze = new char[this.maze_width][this.maze_length];

		for (int i = 0; i < this.maze_width; i++)
		{
			String line = scanner.nextLine();
			for (int j = 0; j < this.maze_length; j++)
			{
				this.maze[i][j] = line.charAt(j);
				if ('S' == this.maze[i][j])
				{
					this.start_x = i;
					this.start_y = j;
				} else if ('D' == this.maze[i][j])
				{
					this.end_x = i;
					this.end_y = j;
				}
			}
		}

		// init the stack
		this.records = new LinkedStack<Point>();
	}

	public void process()
	{
		// pruning operation
		int judgement = Math.abs(this.end_x - this.start_x)
				+ Math.abs(this.end_y - this.start_y) - this.targetTime;

		// no need to do dfs searching
		if (judgement > 0 || judgement % 2 != 0)
		{
			this.isSuccessful = false;
			return;
		}

		this.maze[this.start_x][this.start_y] = 'X';
		dfs(this.start_x, this.start_y, 0);
	}

	private void dfs(int x, int y, int time)
	{
		// push the current coordinates into stack
		this.records.push(new Point(x, y));

		// whether meet the target
		if ('D' == this.maze[x][y] && time == this.targetTime)
		{
			this.isSuccessful = true;
			return;
		}

		// explore the neighbors
		for (Direction dir : Direction.values())
		{
			int targetX = x + dir.getDeltaX();
			int targetY = y + dir.getDeltaY();

			if (this.isSuccessful)
			{
				return;
			}

			if (targetX >= 0 && targetX < this.maze_width && targetY >= 0
					&& targetY < this.maze_length)
			{
				// possible path
				if ('X' != this.maze[targetX][targetY])
				{
					System.out.println("X: " + targetX + " - Y: " + targetY);
					// set the path, but not set the door !
					if (targetX != this.end_x || targetY != this.end_y)
					{
						this.maze[targetX][targetY] = 'X';
					}

					// dfs search
					// pay attention to this pre ++ operator, especially in
					// recursive calls
					dfs(targetX, targetY, ++this.actualTime);

					if (!this.isSuccessful)
					{
						// restore the path
						this.maze[targetX][targetY] = '.';
						this.records.pop();
						this.actualTime--;
					}
				}
			}
		}
	}

	public LinkedStack<Point> getRecords()
	{
		return records;
	}

	public boolean isSuccessful()
	{
		return isSuccessful;
	}

	public enum Direction
	{
		UP(-1, 0), RIGHT(0, 1), BOTTOM(1, 0), LEFT(0, -1);

		private int deltaX;
		private int deltaY;

		private Direction(int deltaX, int deltaY)
		{
			this.deltaX = deltaX;
			this.deltaY = deltaY;
		}

		public int getDeltaX()
		{
			return deltaX;
		}

		public int getDeltaY()
		{
			return deltaY;
		}
	}
}

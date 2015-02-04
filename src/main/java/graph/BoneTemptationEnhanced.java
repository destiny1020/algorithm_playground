package graph;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * ZOJ-2110 DFS Algorithm Aims to provide all solutions and add one more pruning
 * operation
 * 
 * @author Destiny
 * 
 */
public class BoneTemptationEnhanced {
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
	private int walls;

	private boolean isSuccessful;

	private List<Point> records;
	private List<List<Point>> all_records;

	public BoneTemptationEnhanced(String dataFile) throws FileNotFoundException {
		this.dataFile = dataFile;

		init();
	}

	private void init() throws FileNotFoundException {
		File f = new File(this.dataFile);

		Scanner scanner = new Scanner(f);

		// read maze size and target time
		int[] ints = new int[3];
		for (int i = 0; i < 3; i++) {
			if (scanner.hasNextInt()) {
				ints[i] = scanner.nextInt();
			} else {
				throw new RuntimeException("Data Format Error !");
			}
		}

		this.maze_length = ints[0];
		this.maze_width = ints[1];
		this.targetTime = ints[2];
		scanner.nextLine();

		// init the maze
		this.maze = new char[this.maze_width][this.maze_length];

		for (int i = 0; i < this.maze_width; i++) {
			String line = scanner.nextLine();
			for (int j = 0; j < this.maze_length; j++) {
				this.maze[i][j] = line.charAt(j);
				if ('X' == this.maze[i][j]) {
					this.walls++;
				} else if ('S' == this.maze[i][j]) {
					this.start_x = i;
					this.start_y = j;
				} else if ('D' == this.maze[i][j]) {
					this.end_x = i;
					this.end_y = j;
				}
			}
		}

		// init the stack
		this.records = new ArrayList<Point>();
	}

	public void process() {
		// pruning operation: consider total walls number and the target time
		// limit
		if (this.maze_length * this.maze_width - this.walls <= this.targetTime) {
			return;
		}

		this.maze[this.start_x][this.start_y] = 'X';
		dfs(this.start_x, this.start_y, 0);

		if (null != this.all_records && this.all_records.size() > 0) {
			this.isSuccessful = true;
		}
	}

	private void dfs(int x, int y, int time) {
		// pruning operation
		int judgement = Math.abs(this.end_x - x) + Math.abs(this.end_y - y)
				- (this.targetTime - time);

		// no need to do dfs searching
		if (judgement > 0 || judgement % 2 != 0) {
			return;
		}

		// push the current coordinates into stack
		this.records.add(new Point(x, y));

		// whether meet the target
		if ('D' == this.maze[x][y] && time == this.targetTime) {
			this.isSuccessful = true;

			// record the possible solution
			if (null == this.all_records) {
				this.all_records = new ArrayList<List<Point>>();
			}
			this.all_records.add(this.records);
			this.records = new ArrayList<Point>();

			// init the records
			this.records.add(new Point(start_x, start_y));

			return;
		}

		// explore the neighbors
		for (Direction dir : Direction.values()) {
			int targetX = x + dir.getDeltaX();
			int targetY = y + dir.getDeltaY();

			if (this.isSuccessful) {
				// if the current coordinates is the starting point
				if (x == this.start_x && y == this.start_y) {
					this.isSuccessful = false;
				} else {
					return;
				}
			}

			if (targetX >= 0 && targetX < this.maze_width && targetY >= 0
					&& targetY < this.maze_length) {
				// possible path
				if ('X' != this.maze[targetX][targetY]) {
					System.out.println("X: " + targetX + " - Y: " + targetY);
					// set the path, but not set the door !
					if (targetX != this.end_x || targetY != this.end_y) {
						this.maze[targetX][targetY] = 'X';
					}

					// dfs search
					// pay attention to this pre ++ operator, especially in
					// recursive calls
					dfs(targetX, targetY, ++this.actualTime);

					// restore the path, but not restore the door
					if (targetX != this.end_x || targetY != this.end_y) {
						this.maze[targetX][targetY] = '.';
					}

					this.actualTime--;

					if (!this.isSuccessful) {
						if (this.records.size() != 0) {
							this.records.remove(this.records.size() - 1);
						}
					}
				}
			}
		}
	}

	public List<Point> getRecords() {
		return records;
	}

	public List<List<Point>> getAllRecords() {
		return all_records;
	}

	public boolean isSuccessful() {
		return isSuccessful;
	}

	public enum Direction {
		UP(-1, 0), RIGHT(0, 1), BOTTOM(1, 0), LEFT(0, -1);

		private int deltaX;
		private int deltaY;

		private Direction(int deltaX, int deltaY) {
			this.deltaX = deltaX;
			this.deltaY = deltaY;
		}

		public int getDeltaX() {
			return deltaX;
		}

		public int getDeltaY() {
			return deltaY;
		}
	}
}

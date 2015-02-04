package acm.hdu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Cannot use now, need to debug
 * 
 * @author jiangr2
 * 
 */
public class HDU1010 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		BoneTemptationEnhanced bte;

		String[] meta = br.readLine().split(" ");
		int[] metas = new int[3];
		for (int i = 0; i < 3; i++) {
			metas[i] = Integer.parseInt(meta[i]);
		}

		while (metas[0] != 0 && metas[1] != 0 && metas[2] != 0) {
			bte = new BoneTemptationEnhanced(br, metas);
			bte.process();
			if (bte.isSuccessful()) {
				out.println("YES");
			} else {
				out.println("NO");
			}
			meta = br.readLine().split(" ");
			metas = new int[3];
			for (int i = 0; i < 3; i++) {
				metas[i] = Integer.parseInt(meta[i]);
			}
		}
		out.flush();
	}
}

class BoneTemptationEnhanced {
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

	public BoneTemptationEnhanced(BufferedReader br, int[] meta)
			throws IOException {

		this.maze_length = meta[0];
		this.maze_width = meta[1];
		this.targetTime = meta[2];

		// init the maze
		this.maze = new char[this.maze_width][this.maze_length];

		for (int i = 0; i < this.maze_width; i++) {
			String line = br.readLine();
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
	}

	public void process() {
		// pruning operation: consider total walls number and the target time
		// limit
		if (this.maze_length * this.maze_width - this.walls <= this.targetTime) {
			return;
		}

		this.maze[this.start_x][this.start_y] = 'X';
		dfs(this.start_x, this.start_y, 0);
	}

	private void dfs(int x, int y, int time) {
		// pruning operation
		int judgement = Math.abs(this.end_x - x) + Math.abs(this.end_y - y)
				- (this.targetTime - time);

		// no need to do dfs searching
		if (judgement > 0 || judgement % 2 != 0) {
			return;
		}

		// whether meet the target
		if ('D' == this.maze[x][y] && time == this.targetTime) {
			this.isSuccessful = true;
			return;
		}

		// explore the neighbors
		for (Direction dir : Direction.values()) {
			int targetX = x + dir.getDeltaX();
			int targetY = y + dir.getDeltaY();

			if (targetX >= 0 && targetX < this.maze_width && targetY >= 0
					&& targetY < this.maze_length) {
				// possible path
				if ('X' != this.maze[targetX][targetY]) {
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
				}
			}
		}
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
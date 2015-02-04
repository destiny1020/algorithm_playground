package introduction.chapter15;

import java.util.ArrayDeque;
import java.util.Deque;

public class AssemblyLine
{
	private int[][] time;
	private int numberOfLines;
	private int numberOfStations;

	private int totalTimeConsumed;
	private int[][] timeConsumed;
	private int exitStation;
	private int[][] records;
	
	// From line i to line j, transfer[i][j], returns a array of Integers
	private Object[][] transfer;

	public AssemblyLine(int[][] time, Object[][] transfer)
	{
		this.transfer = transfer;
		this.time = time;
		this.numberOfLines = time.length;
		this.numberOfStations = time[0].length;

		// Initialize the records arrays
		this.timeConsumed = new int[this.numberOfLines][this.numberOfStations];
		this.records = new int[this.numberOfLines][this.numberOfStations];
	}

	public void fastestWay()
	{
		// First Station
		for (int i = 0; i < this.numberOfLines; i++)
		{
			this.timeConsumed[i][1] = this.time[i][0] + this.time[i][1];
		}

		// From the second station to the last one
		for (int i = 2; i <= numberOfStations - 2; i++)
		{
			// For each line
			for (int j = 0; j < this.numberOfLines; j++)
			{
				this.timeConsumed[j][i] = getPartialFastest(j, i);
			}
		}

		// Last step
		this.totalTimeConsumed = this.timeConsumed[0][this.numberOfStations - 2]
				+ this.time[0][this.numberOfStations - 1];
		this.exitStation = 0;
		for (int i = 1; i < this.numberOfLines; i++)
		{
			int pTotal = this.timeConsumed[i][this.numberOfStations - 2]
					+ this.time[i][this.numberOfStations - 1];
			if (pTotal < totalTimeConsumed)
			{
				totalTimeConsumed = pTotal;
				exitStation = i;
			}
		}
	}

	private int getPartialFastest(int line, int station)
	{
		// init the fastest path as there is no line transfer operation
		int fastest = this.timeConsumed[line][station - 1]
				+ this.time[line][station];
		this.records[line][station] = line;
		for (int i = 0; i < this.numberOfLines; i++)
		{
			// Do not account for "equals" situation here
			if (i != line)
			{
				int pFastest = this.timeConsumed[i][station - 1]
						+ this.time[line][station] + ((Integer[])this.transfer[i][line])[station - 1];
				if (pFastest < fastest)
				{
					fastest = pFastest;
					// Record the line info
					this.records[line][station] = i;
				}
			}
		}
		return fastest;
	}

	public void printResult()
	{
		Deque<String> records = new ArrayDeque<String>();
		records.addFirst(this.catString(this.exitStation,
				this.numberOfStations - 2));
		int previousLine = this.exitStation;
		for (int i = this.numberOfStations - 2; i > 1; i--)
		{
			// Retrieve the records info and set the previousLine info
			records.addFirst(this.catString(
					previousLine = this.records[previousLine][i], i - 1));
		}

		while (!records.isEmpty())
		{
			System.out.println(records.removeFirst());
		}
		System.out.println("Total Time: " + this.totalTimeConsumed);
	}
	
	private String catString(int line, int station)
	{
		return "Line: " + (line + 1) + " - Station: " + station;
	}

	/**
	 * Test Client
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		// 2 lines, 6 stations + 1 entry + 1 exit
		int time[][] = new int[][]{{2, 7, 9, 3, 4, 8, 4, 3}, {4, 8, 5, 6, 4, 5, 7, 2}};
		Object[][] transfer = new Object[2][6];
		transfer[0][1] = new Integer[]{0, 2, 3, 1, 3, 4};
		transfer[1][0] = new Integer[]{0, 2, 1, 2, 2, 1};
		
		AssemblyLine al = new AssemblyLine(time, transfer);
		al.fastestWay();
		al.printResult();
	}
}

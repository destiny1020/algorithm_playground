package chap1;

import gadget.CustomRandom;

public class DiceSimulation
{
	public static int SIDES = 6;
	public static double ERR = 0.001;
	
	private int totalCount = 0;
	private int[] counts = new int[2 * SIDES + 1];
	private double[] dist = new double[2 * SIDES + 1];
	
	public DiceSimulation()
	{
		this.init();
	}

	// Calculate the standard probability
	private void init()
	{
		for(int i = 1; i <= SIDES; i++)
		{
			for(int j = 1; j <= SIDES; j++)
			{
				dist[i + j] += 1.0;
			}
		}
		
		for(int k = 2; k <= 2 * SIDES; k++)
		{
			dist[k] /= 36;
		}
	}
	
	public int process()
	{
		for(; checkForTerminate(); this.totalCount++)
		{
			// Pay attention to the algorithm of generating random number
			int first = CustomRandom.uniform(1, 7);
			int second = CustomRandom.uniform(1, 7);
			counts[first + second]++;
		}
		
		return totalCount;
	}

	private boolean checkForTerminate()
	{
		System.out.println(this.totalCount);
		
		if(this.totalCount > 100)
		{
			for(int i = 2; i < SIDES * 2; i++)
			{
				// Convert the int to double, or the loop will run forever.
				if(Math.abs((double)counts[i] / this.totalCount - this.dist[i]) > ERR)
				{
					return true;
				}
			}
			return false;
		}
		return true;
	}
}

package gadget;

import java.util.Random;

public class CustomRandom
{
	private static Random random = new Random();
	
	public static double uniform(double a, double b)
	{
		return a + random.nextDouble() * (b - a);
	}
	
	// Influence Range [0, bound - 1]
	public static int uniform(int bound)
	{
		return random.nextInt(bound);
	}
	
	public static int uniform(int a, int b)
	{
		return a + random.nextInt(b - a);
	}
	
	public static int discrete(double[] a)
	{
		double value = random.nextDouble();
		double sum = 0;
		for(int i = 0; i < a.length; i++)
		{
			sum += a[i];
			if(sum >= value)
			{
				return i;
			}
		}
		return -1;
	}
	
	public static void shuffle(double[] a)
	{
		int length = a.length;
		
		for(int i = 0; i < length; i++)
		{
			int random = CustomRandom.uniform(i, length - 1);
			
			// Swap operation
			double temp = a[i];
			a[i] = a[random];
			a[random] = temp;
		}
	}
	
	public static void shuffle(int[] a)
	{
		int length = a.length;
		
		for(int i = 0; i < length; i++)
		{
			int random = CustomRandom.uniform(i, length - 1);
			
			// Swap operation
			int temp = a[i];
			a[i] = a[random];
			a[random] = temp;
		}
	}
	
	public static void shuffleExtend(int[] a)
	{
		int length = a.length;
		
		for(int i = 0; i < length; i++)
		{
			int random = CustomRandom.uniform(i, length);
			
			// Swap operation
			int temp = a[i];
			a[i] = a[random];
			a[random] = temp;
		}
		for (int i = length - 1; i >= 0; i--)
		{
			int random = CustomRandom.uniform(0, i + 1);
			
			int temp = a[i];
			a[i] = a[random];
			a[random] = temp;
		}
	}
}

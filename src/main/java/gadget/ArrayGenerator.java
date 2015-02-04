package gadget;

public class ArrayGenerator
{
	public static int[] generateIntArray(int size, int low, int high)
	{
		int[] results = new int[size];
		
		for(int i = 0; i < size; i++)
		{
			int random = CustomRandom.uniform(low, high + 1);
			results[i] = random;
		}
		
		return results;
	}
	
	public static Integer[] generateIntegerArray(int size, int low, int high)
	{
		Integer[] results = new Integer[size];
		
		for(int i = 0; i < size; i++)
		{
			Integer random = CustomRandom.uniform(low, high + 1);
			results[i] = random;
		}
		
		return results;
	}
	
	public static Integer[] generateIntegerArrayForHeap(int size, int low, int high)
	{
		Integer[] results = new Integer[size + 1];
		
		for(int i = 1; i < size + 1; i++)
		{
			Integer random = CustomRandom.uniform(low, high + 1);
			results[i] = random;
		}
		
		return results;
	}
	
	public static Integer[] generateReverseIntegerArray(int size, int start, int step)
	{
		Integer[] results = new Integer[size];
		
		for(int i = 0; i < size; i++)
		{
			results[i] = start - step * i;
		}
		
		return results;
	}
	
	public static Integer[] generateOrderedIntegerArray(int size, int start, int step)
	{
		Integer[] results = new Integer[size];
		
		for(int i = 0; i < size; i++)
		{
			results[i] = start + i * step;
		}
		
		return results;
	}
}

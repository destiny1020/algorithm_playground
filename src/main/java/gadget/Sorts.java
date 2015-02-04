package gadget;

public class Sorts
{
	// Binary Search, the array should be ordered
	public static int binarySearch(int key, int[] array)
	{
		int hi = array.length - 1;
		int lo = 0;
		int mid;
		
		while(lo <= hi)
		{
			mid = (lo + hi) / 2;
			if(key == array[mid])
			{
				return mid;
			}
			if(key < array[mid])
			{
				hi = mid - 1;
			}
			else
			{
				lo = mid + 1;
			}
		}
		
		return -1;
	}
	
	// Range Binary Search, lo = 0, hi = length - 1
	public static int binarySearch(int key, int[] array, int lo, int hi)
	{
		int mid;
		
		while(lo <= hi)
		{
			mid = (lo + hi) / 2;
			if(key == array[mid])
			{
				return mid;
			}
			if(key < array[mid])
			{
				hi = mid - 1;
			}
			else
			{
				lo = mid + 1;
			}
		}
		
		return -1;
	}
	
	// Extended Binary Search, return two values: [index, count]
	public static int[] binarySearchExtend(int key, int[] array)
	{
		int[] results = new int[]{-1, -1};
		results[0] = binarySearch(key, array);
		
		int count;
		if (results[0] >= 0)
		{
			count = 1;
			int index = results[0];
			while (index > 0 && array[--index] == key)
			{
				count++;
			}
			index = results[0];
			while (index < array.length - 1 && array[++index] == key)
			{
				count++;
			}
			results[1] = count;
		}
		
		return results;
	}
	
	// Recursive Binary search
	public static int recursiveBinarySearch(int key, int[] array)
	{
		return recursiveBinarySearch(key, array, 0, array.length - 1);
	}
	
	// Recursive Binary search and keep deep info
	public static int recursiveBinarySearchWithDeep(int key, int[] array)
	{
		return recursiveBinarySearch(key, array, 0, array.length - 1, 1);
	}
	
	private static int recursiveBinarySearch(int key, int[] array, int lo, int hi)
	{
		if(lo > hi)
			return -1;
		
		int mid = (lo + hi) / 2;
		if(key == array[mid])
			return mid;
		if(key < array[mid])
			return recursiveBinarySearch(key, array, lo, mid - 1);
		else
			return recursiveBinarySearch(key, array, mid + 1, hi);
	}
	
	// Keep record of recursive deep and print arguments
	private static int recursiveBinarySearch(int key, int[] array, int lo, int hi, int deep)
	{
		System.out.println("Current Deep: " + deep + "\tLow: " + lo + "\tHigh: " + hi);
		
		if(lo > hi)
			return -1;
		
		int mid = (lo + hi) / 2;
		if(key == array[mid])
			return mid;
		if(key < array[mid])
			return recursiveBinarySearch(key, array, lo, mid - 1, ++deep);
		else
			return recursiveBinarySearch(key, array, mid + 1, hi, ++deep);
	}
}

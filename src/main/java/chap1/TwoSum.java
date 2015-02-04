package chap1;

import java.util.Arrays;

import gadget.ArrayGenerator;
import gadget.Sorts;

public class TwoSum
{
	private int[] array;
	private int size;
	private int low;
	private int high;
	private int count;
	
	public TwoSum(int size, int low, int high)
	{
		this.size = size;
		this.low = low;
		this.high = high;
		
		this.array = ArrayGenerator.generateIntArray(size, low, high);
//		this.array = new int[]{-10, -9, -8, -8, -7, 8, 8, 8, 8, 8};
		
		this.init();
	}
	
	private void init()
	{
		Arrays.sort(array);
	}
	
	public void bruteProcess()
	{
		for(int i = 0; i < this.size; i++)
		{
			for(int j = i + 1; j < this.size; j++)
			{
				if(array[i] + array[j] == 0)
				{
					count++;
				}
			}
		}
	}

	public void process()
	{
		for(int i = 0; i < this.size; i++)
		{
//			int index = Sorts.binarySearch(-array[i], array);
			int[] results = Sorts.binarySearchExtend(-array[i], array);
			if(results[0] > i)
			{
				this.count += results[1];
//				System.out.println(i + " - " + array[i] + " && " + index + " - " + (-array[i]));
			}
		}
	}
	
	public void processFast()
	{
		for(int i = 0; i < this.size; i++)
		{
			if(array[i] > 0)
			{
				return;
			}
			
//			int index = Sorts.binarySearch(-array[i], array);
			int[] results = Sorts.binarySearchExtend(-array[i], array);
			if(results[0] > i)
			{
				this.count += results[1];
//				System.out.println(i + " - " + array[i] + " && " + index + " - " + (-array[i]));
			}
		}
	}
	
	public int getCount()
	{
		return count;
	}
	
	public void setCount(int count)
	{
		this.count = count;
	}

	public int getLow()
	{
		return low;
	}

	public int getHigh()
	{
		return high;
	}
}

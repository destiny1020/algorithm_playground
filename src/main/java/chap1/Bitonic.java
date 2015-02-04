package chap1;

import gadget.CustomRandom;
//import gadget.Sorts;

public class Bitonic
{
	private int N;
	private int mid_index;
	private int[] bi_array;
	private boolean hasPeak;
	
	public Bitonic(int N, boolean hasPeak)
	{
		this.N = N;
		this.hasPeak = hasPeak;
	}
	
	public int[] createBiArray()
	{
		// Init the array
		this.bi_array = new int[N];
		
		// Init the mid index
		this.mid_index = CustomRandom.uniform(0 + N / 5, (4 * N / 5) + 1);
		
		for(int i = 1; i < this.mid_index; i++)
		{
			if(hasPeak)
			{
				this.bi_array[i] = this.bi_array[i - 1] + 1 + CustomRandom.uniform(10);
			}
			else
			{
				this.bi_array[i] = this.bi_array[i - 1] - 1 - CustomRandom.uniform(10);
			}
		}
		
		// Set the mid_index
		this.bi_array[this.mid_index] = this.bi_array[this.mid_index - 1] + CustomRandom.uniform(11) - 5;
		
		for(int i = this.mid_index + 1; i < N; i++)
		{
			if(hasPeak)
			{
				this.bi_array[i] = this.bi_array[i - 1] - 1 - CustomRandom.uniform(10);
			}
			else
			{
				this.bi_array[i] = this.bi_array[i - 1] + 1 + CustomRandom.uniform(10);
			}
		}
		
		return bi_array;
	}
}

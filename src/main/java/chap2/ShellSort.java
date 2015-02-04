package chap2;

public class ShellSort<T> implements Sortable<T>
{
	private static int[] steps = {1, 3, 7, 12, 19};
	
	public static <T> void sort(Comparable<T>[] a)
	{
		int N = a.length;
		int h = 1;
		
		while(h < N / 3)
		{
			h = h * 3 + 1;
		}
		
		//实际上，只用找到h个min放到前面几个index就行了
		//但是结果依然不理想，shell果然是很奇怪的一个排序方法
//		for(int i = 0; i < h; i++)
//		{
//			int min_index = i;
//			for(int j = i + 1; j < a.length; j++)
//			{
//				if(SortUtils.less(a[j], a[min_index]))
//				{
//					min_index = j;
//				}
//			}
//			SortUtils.exch(a, i, min_index);
//		}
		
		while(h >= 1)
		{
			for(int i = h; i < N; i++)
			{
				for(int j = i; j >= h && SortUtils.less(a[j], a[j - h]); j -= h)
				{
					//这里有改进的空间，潜在的交换次数可能比较多
					SortUtils.exch(a, j, j - h);
				}
				
				// 将改进后的算法封装到该方法中
//				InsertionWithSentinelSelection.sortWithSentinelStep(a, h, i);
			}
			
			h /= 3;
		}
	}
	
	public static <T> void sortByStepsFromArray(Comparable<T>[] a)
	{
		int step_index = 0;
		
		// Compute the step_index
		int N = a.length;
		
		while(steps[step_index] < N / 3)
		{
			step_index++;
		}
		
		while(step_index != -1)
		{
			for(int i = steps[step_index]; i < N; i++)
			{
				for(int j = i; j >= steps[step_index] && SortUtils.less(a[j], a[j - steps[step_index]]); j -= steps[step_index])
				{
					SortUtils.exch(a, j, j - steps[step_index]);
				}
			}
			
			step_index--;
		}
	}

	@Override
	public void sortIt(Comparable<T>[] a)
	{
		ShellSort.sort(a);
	}
}

package chap2;

public class InsertionWithSentinelSelection<T> implements Sortable<T>
{
	public static <T> void sortWithSentinel(Comparable<T>[] a)
	{
		int N = a.length;
		
		// 将Sentinel放到index为0的地方
		// 与InsertionWithSentinel的不同之处在于sentinel交换位置的实现方式不同
		// 不采用冒泡的方式，而使用选择 - 交换的方式
		int min = 0;
		for(int i = 1; i < N; i++)
		{
			if(SortUtils.less(a[i], a[min]))
			{
				min = i;
			}
		}
		SortUtils.exch(a, 0, min);
		
		for(int i = 2; i < N; i++)
		{
			Comparable<T> v = a[i];
			int j = i;
			
			while(SortUtils.less(v, a[j - 1]))
			{
				a[j] = a[j - 1];
				j--;
			}
			
			a[j] = v;
		}
	}
	
	// Plain
	public static <T> void sortWithSentinelStep(Comparable<T>[] a, int h, int i)
	{
//		for(int i = end_index; i >= step && SortUtils.less(a[i], a[i - step]); i -= step)
//		{
//			SortUtils.exch(a, i, i - step);
//		}
		
		int sentinel_index = i % h;
//		int min = sentinel_index;
//		for(int k = sentinel_index; k + h <= i; k += h)
//		{
//			if(SortUtils.less(a[k + h], a[min]))
//			{
//				min = k + h;
//			}
//		}
//		if(sentinel_index != min)
//		{
//			SortUtils.exch(a, sentinel_index, min);
//		}
		
		for(int k = sentinel_index + 2 * h; k <= i; k += h)
		{
			Comparable<T> v = a[k];
			int j = k;
			
			while(SortUtils.less(v, a[j - h]))
			{
				a[j] = a[j - h];
				j -= h;
			}
			
			a[j] = v;
		}
	}

	@Override
	public void sortIt(Comparable<T>[] a)
	{
		InsertionWithSentinelSelection.sortWithSentinel(a);
	}
}

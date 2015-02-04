package chap2;

public class InsertionSort<T> implements Sortable<T>
{
	public static <T> void sort(Comparable<T>[] a)
	{
		for(int i = 1; i < a.length; i++)
		{
			for(int j = i; j > 0 && SortUtils.less(a[j], a[j - 1]); j--)
			{
				SortUtils.exch(a, j, j - 1);
			}
		}
	}
	
	// Insertion sort on specified range, [lo, hi]
	public static <T> void sort(Comparable<T>[] a, int lo, int hi)
	{
		for(int i = lo + 1; i <= hi; i++)
		{
			for(int j = i; j > lo && SortUtils.less(a[j], a[j - 1]); j--)
			{
				SortUtils.exch(a, j, j - 1);
			}
		}
	}
	
	public static long sort(int[] a)
	{
		long start_t = System.nanoTime();
		
		for(int i = 1; i < a.length; i++)
		{
			for(int j = i; j > 0 && a[j] < a[j - 1]; j--)
			{
				SortUtils.exch(a, j, j - 1);
			}
		}
		
		long end_t = System.nanoTime();
		
		return end_t - start_t;
	}
	
	@Override
	public void sortIt(Comparable<T>[] a)
	{
		InsertionSort.sort(a);
	}
}

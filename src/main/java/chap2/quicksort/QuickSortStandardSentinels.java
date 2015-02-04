package chap2.quicksort;

import chap2.SortUtils;
import chap2.Sortable;

/**
 * The partition method was borrowed from algo book, with improvements of sentinels
 * @author Destiny
 *
 * @param <T>
 */
public class QuickSortStandardSentinels<T> implements Sortable<T>
{

	@Override
	public void sortIt(Comparable<T>[] a)
	{
		QuickSort.sort(a);
	}
	
	/**
	 * find the biggest element and swap it with the element indexed at hi
	 * @param a
	 * @param lo
	 * @param hi
	 */
	private void preHandleSentinel(Comparable<T>[] a, int lo, int hi)
	{
		int max_index = lo;
		
		for(int i = lo + 1; i <= hi; i++)
		{
			if(SortUtils.less(a[max_index], a[i]))
			{
				max_index = i;
			}
		}
		
		SortUtils.exch(a, hi, max_index);
		
	}

	public void sort(Comparable<T>[] a)
	{
		preHandleSentinel(a, 0, a.length - 1);
		
		inner_sort(a, 0, a.length - 1);
	}
	
	public void sort(Comparable<T>[] a, int lo, int hi)
	{
		preHandleSentinel(a, lo, hi);
		
		inner_sort(a, lo, hi);
	}

	private void inner_sort(Comparable<T>[] a, int lo, int hi)
	{
		if (lo >= hi)
		{
			return;
		}

		int j = partition(a, lo, hi);

		inner_sort(a, lo, j - 1);
		inner_sort(a, j + 1, hi);
	}

	private int partition(Comparable<T>[] a, int lo, int hi)
	{
		int i = lo, j = hi + 1; // left and right scan indices
		Comparable<T> v = a[lo]; // partitioning item
		while (true)
		{ // Scan right, scan left, check for scan complete, and exchange.
			while (SortUtils.less(a[++i], v))
			{
				// With Sentinels, there is no need to judge for bound
//				if (i == hi)
//					break;
			}
			while (SortUtils.less(v, a[--j]))
			{	
				// With Sentinels, there is no need to judge for bound
//				if (j == lo)
//					break;
			}
			if (i >= j)
			{
				break;
			}
			
			SortUtils.exch(a, i, j);
		}
		SortUtils.exch(a, lo, j); // Put v = a[j] into position
		
		return j; // with a[lo..j-1] <= a[j] <= a[j+1..hi].
	}
}

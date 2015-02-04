package chap2.quicksort;

import chap2.SortUtils;
import chap2.Sortable;

/**
 * The partition method was borrowed from algo book
 * @author Destiny
 *
 * @param <T>
 */
public class QuickSortStandard<T> implements Sortable<T>
{

	@Override
	public void sortIt(Comparable<T>[] a)
	{
		QuickSort.sort(a);
	}

	public static <T> void sort(Comparable<T>[] a)
	{
		sort(a, 0, a.length - 1);
	}

	public static <T> void sort(Comparable<T>[] a, int lo, int hi)
	{
		if (lo >= hi)
		{
			return;
		}

		int j = partition(a, lo, hi);

		sort(a, lo, j - 1);
		sort(a, j + 1, hi);
	}

	private static <T> int partition(Comparable<T>[] a, int lo, int hi)
	{
		int i = lo, j = hi + 1; // left and right scan indices
		Comparable<T> v = a[lo]; // partitioning item
		while (true)
		{ // Scan right, scan left, check for scan complete, and exchange.
			while (SortUtils.less(a[++i], v))
				if (i == hi)
					break;
			while (SortUtils.less(v, a[--j]))
				if (j == lo)
					break;
			if (i >= j)
				break;
			SortUtils.exch(a, i, j);
		}
		SortUtils.exch(a, lo, j); // Put v = a[j] into position
		return j; // with a[lo..j-1] <= a[j] <= a[j+1..hi].
	}
}

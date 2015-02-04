package chap2.quicksort;

import chap2.InsertionSort;
import chap2.SortUtils;
import chap2.Sortable;

public class QuickSort3WayBentley<T> implements Sortable<T>
{
	private static final int CUTOFF = 8;
	
	@Override
	public void sortIt(Comparable<T>[] a)
	{
		// TODO Auto-generated method stub

	}

	public static <T> void sort(Comparable<T>[] a)
	{
		sort(a, 0, a.length - 1);
	}

	private static <T> void sort(Comparable<T>[] a, int lo, int hi)
	{
		int N = hi - lo + 1;

		// cutoff to insertion sort
		if (N <= CUTOFF)
		{
			InsertionSort.sort(a, lo, hi);
			return;
		}

		// use median-of-3 as partitioning element
		else if (N <= 40)
		{
			int m = median3(a, lo, lo + N / 2, hi);
			SortUtils.exch(a, m, lo);
		}

		// use Tukey ninther as partitioning element
		else
		{
			int eps = N / 8;
			int mid = lo + N / 2;
			int m1 = median3(a, lo, lo + eps, lo + eps + eps);
			int m2 = median3(a, mid - eps, mid, mid + eps);
			int m3 = median3(a, hi - eps - eps, hi - eps, hi);
			int ninther = median3(a, m1, m2, m3);
			SortUtils.exch(a, ninther, lo);
		}

		// Bentley-McIlroy 3-way partitioning
		int i = lo, j = hi + 1;
		int p = lo, q = hi + 1;
		while (true)
		{
			Comparable<T> v = a[lo];
			while (SortUtils.less(a[++i], v))
				if (i == hi)
					break;
			while (SortUtils.less(v, a[--j]))
				if (j == lo)
					break;
			if (i >= j)
				break;
			SortUtils.exch(a, i, j);
			if (SortUtils.eq(a[i], v))
				SortUtils.exch(a, ++p, i);
			if (SortUtils.eq(a[j], v))
				SortUtils.exch(a, --q, j);
		}
		SortUtils.exch(a, lo, j);

		i = j + 1;
		j = j - 1;
		for (int k = lo + 1; k <= p; k++)
			SortUtils.exch(a, k, j--);
		for (int k = hi; k >= q; k--)
			SortUtils.exch(a, k, i++);

		sort(a, lo, j);
		sort(a, i, hi);
	}

	// return the index of the median element among a[i], a[j], and a[k]
	private static <T> int median3(Comparable<T>[] a, int i, int j, int k)
	{
		return (SortUtils.less(a[i], a[j]) ? (SortUtils.less(a[j], a[k]) ? j
				: SortUtils.less(a[i], a[k]) ? k : i) : (SortUtils.less(a[k],
				a[j]) ? j : SortUtils.less(a[k], a[i]) ? k : i));
	}

}

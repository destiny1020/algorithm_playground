package chap2.quicksort;

import chap2.SortUtils;
import chap2.Sortable;

public class QuickSort<T> implements Sortable<T>
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
		if(lo >= hi)
		{
			return;
		}
		
		int j = partition(a, lo, hi);
		
		sort(a, lo, j - 1);
		sort(a, j + 1, hi);
	}
	
	private static <T> int partition(Comparable<T>[] a, int lo, int hi)
	{
		int i = lo + 1;
		int j = hi;
		
		Comparable<T> t = a[lo];
		
		while(true)
		{
//			System.out.println("I: " + i + "\tJ: " + j);
//			SortUtils.show(a);
			
			while(i < j && SortUtils.lessOrEqual(a[i], t))
			{
				i++;
			}
			while(i <= j && SortUtils.less(t, a[j]))
			{
				j--;
			}
			if(i >= j)
			{
				break;
			}
			SortUtils.exch(a, i, j);
		}
		
		SortUtils.exch(a, lo, j);
		
		return j;
	}
}

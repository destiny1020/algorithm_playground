package chap2.quicksort;

import chap2.SortUtils;
import chap2.Sortable;

/**
 * The partition method was borrowed from algo book, 3 ways QS
 * @author Destiny
 *
 * @param <T>
 */
public class QuickSortStandard3Way<T> implements Sortable<T>
{

	@Override
	public void sortIt(Comparable<T>[] a)
	{
		QuickSortStandard3Way.sort(a);
	}

	public static <T> void sort(Comparable<T>[] a)
	{
		sort(a, 0, a.length - 1);
	}

	@SuppressWarnings("unchecked")
	public static <T> void sort(Comparable<T>[] a, int lo, int hi) 
	{
		if(hi <= lo)
		{
			return;
		}
		
		// Init of three pointers
		int lt = lo;
		int i = lo + 1;
		int gt = hi;
		
		Comparable<T> v = a[lo];
		
		while(i <= gt)
		{
			int compare = a[i].compareTo((T)v);
			switch(compare)
			{
			case 0:
				i++;
				break;
			case -1:
				SortUtils.exch(a, i++, lt++);
				break;
			case 1:
				SortUtils.exch(a, i, gt--);
				break;
			default:
				System.out.println("Unknown Exception");
				System.exit(1);
			}
		}
		
		sort(a, lo, lt - 1);
		sort(a, lt + 1, hi);
	}
}

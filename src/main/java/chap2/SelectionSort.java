package chap2;

public class SelectionSort<T> implements Sortable<T>
{
	public static <T> void sort(Comparable<T>[] a)
	{
		for(int i = 0; i < a.length; i++)
		{
			int min_index = i;
			for(int j = i + 1; j < a.length; j++)
			{
				if(SortUtils.less(a[j], a[min_index]))
				{
					min_index = j;
				}
			}
			SortUtils.exch(a, i, min_index);
		}
	}

	@Override
	public void sortIt(Comparable<T>[] a)
	{
		SelectionSort.sort(a);
	}
}

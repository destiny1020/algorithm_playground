package chap2;

public class BubbleSort<T> implements Sortable<T>
{
	@Override
	public void sortIt(Comparable<T>[] a)
	{
		BubbleSort.sort(a);
	}

	public static <T> void sort(Comparable<T>[] a)
	{
		int N = a.length;
		
		for(int i = N - 1; i > 0; i--)
		{
			for(int j = N - 1; j > N - 1 - i; j--)
			{
				if(SortUtils.less(a[j], a[j - 1]))
				{
					SortUtils.exch(a, j, j - 1);
				}
			}
		}
	}
	
}

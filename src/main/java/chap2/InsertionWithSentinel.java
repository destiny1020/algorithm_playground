package chap2;

public class InsertionWithSentinel<T> implements Sortable<T>
{
	public static <T> void sortWithSentinel(Comparable<T>[] a)
	{
		int N = a.length;
		
		// 将Sentinel放到index为0的地方，这是一个bubble过程，将最小的放到开头的位置
		// 所以该过程具有bubble_sort的缺点，即潜在的exchange操作太多，使用selection改进
		for(int i = N - 1; i > 0; i--)
		{
			if(SortUtils.less(a[i], a[i - 1]))
			{
				SortUtils.exch(a, i, i - 1);
			}
		}
		
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

	@Override
	public void sortIt(Comparable<T>[] a)
	{
		InsertionWithSentinel.sortWithSentinel(a);
	}
}

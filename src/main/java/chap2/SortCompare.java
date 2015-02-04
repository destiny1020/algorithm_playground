package chap2;

public class SortCompare
{
	public static <T> long time(Sortable<T> sort, Comparable<T>[] a)
	{
		long start_t = System.nanoTime();
		
		sort.sortIt(a);
		
		long end_t = System.nanoTime();
		
		return end_t - start_t;
	}
}

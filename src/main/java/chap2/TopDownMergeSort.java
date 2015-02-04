package chap2;

public class TopDownMergeSort<T extends Comparable<T>> implements Sortable<T>
{
	private Comparable<T>[] aux;
	
	private void sort(Comparable<T>[] a, int lo, int hi)
	{
		if(lo >= hi)
		{
			return;
		}
		int mid = lo + (hi - lo) / 2;
		
		// Recursive in a top-down style
		sort(a, lo, mid);
		sort(a, mid + 1, hi);
		merge(a, lo, mid, hi);
//		SortUtils.show(a);
	}
	
	private void merge(Comparable<T>[] a, int lo, int mid, int hi)
	{
//		System.out.println("Merge (" + lo + ", " + mid + ", " + hi + ")");
		
		// Copy a[lo..hi] to aux[lo..hi].
		for (int k = lo; k <= hi; k++) 
		{
			aux[k] = a[k];
		}
		
		int i = lo;
		int j = mid + 1;
		
		for(int k = lo; k <= hi; k++)
		{
			// Left half exhausted
			if(i > mid)
			{
				a[k] = aux[j++];
			}
			// Right half exhausted
			else if(j > hi)
			{
				a[k] = aux[i++];
			}
			// Left element is less than the right one
			else if(SortUtils.lessOrEqual(aux[i], aux[j]))
			{
				a[k] = aux[i++];
			}
			// Right element is less than the left one
			else
			{
				a[k] = aux[j++];
			}
		}
	}

	@Override
	public void sortIt(Comparable<T>[] a)
	{
		this.init(a);
		
		this.sort(a, 0, a.length - 1);
	}

	// Do init work - init aux, and to dump elements into it from a
	@SuppressWarnings("unchecked")
	private void init(Comparable<T>[] a)
	{
		this.aux = new Comparable[a.length];
		
		for(int i = 0; i < a.length; i++)
		{
			aux[i] = a[i];
		}
	}
}

package chap2;

public class TopDownMergeSortX<T> implements Sortable<T>
{
	@Override
	public void sortIt(Comparable<T>[] a)
	{
		sort(a);
	}

	private int order;
	
	private int CUTOFF = 15;
	
	public void setCutoff(int cutoff)
	{
		CUTOFF = cutoff;
	}
	
	public void sort(Comparable<T>[] a)
	{
		/*
        Comparable[] aux = new Comparable[a.length];
        for (int i = 0; i < a.length; i++)
            aux[i] = a[i];
		 */
		
		// a bit faster
		Comparable<T>[] aux = a.clone();
		
		// Entry
		sort(a, aux, 0, a.length - 1);
	}
	
	private void sort(Comparable<T>[] src, Comparable<T>[] dst, int lo, int hi)
	{
		if(lo + CUTOFF >= hi)
		{
			InsertionSort.sort(src, lo, hi);
			return;
		}
		
//		if(lo >= hi)
//		{
//			return;
//		}
		
		int mid = lo + (hi - lo) / 2;
		
		sort(dst, src, lo, mid);
		sort(dst, src, mid + 1, hi);
		
		if(SortUtils.less(dst[mid], dst[mid + 1]))
		{
			System.arraycopy(dst, lo, src, lo, hi - lo + 1);
			return;
		}
		else
		{
			merge(dst, src, lo, mid, hi);
		}
	}
	
	private void merge(Comparable<T>[] src, Comparable<T>[] dst, int lo, int mid, int hi)
	{
		// Original merge strategy except that there is no need to copy elements
		int i = lo;
		int j = mid + 1;
		
		for(int k = lo; k <= hi; k++)
		{
			// Left half exhausted
			if(i > mid)
			{
				dst[k] = src[j++];
			}
			// Right half exhausted
			else if(j > hi)
			{
				dst[k] = src[i++];
			}
			// Left element is less than the right one
			else if(SortUtils.lessOrEqual(src[i], src[j]))
			{
				dst[k] = src[i++];
			}
			// Right element is less than the left one
			else
			{
				dst[k] = src[j++];
			}
		}
		
//		this.order++;
//		System.out.println("Merge Num: " + order);
//		System.out.println("LO: " + lo + "\tMID: " + mid + "\tHI: " + hi);
//		System.out.println("SRC:");
//		SortUtils.show(src);
//		System.out.println("DST:");
//		SortUtils.show(dst);
//		System.out.println("-------------------------");
	}
	
}

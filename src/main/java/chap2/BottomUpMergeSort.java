package chap2;

public class BottomUpMergeSort<T> implements Sortable<T>
{
	private Comparable<T>[] aux;

	private void sort(Comparable<T>[] a, int lo, int hi)
	{
		this.aux = a.clone();

		int N = hi - lo + 1;

		for (int size = 1; size < N; size = size * 2)
		{
			for (int i = lo; i < N - size; i += 2 * size)
			{
				this.merge(a, i, i + size - 1, Math.min(i + 2 * size - 1, hi));
			}
		}
	}

	private void merge(Comparable<T>[] a, int lo, int mid, int hi)
	{
		for (int k = lo; k <= hi; k++)
		{
			aux[k] = a[k];
		}

		int i = lo;
		int j = mid + 1;

		for (int k = lo; k <= hi; k++)
		{
			// Left half exhausted
			if (i > mid)
			{
				a[k] = aux[j++];
			}
			// Right half exhausted
			else if (j > hi)
			{
				a[k] = aux[i++];
			}
			// Left element is less than the right one
			else if (SortUtils.lessOrEqual(aux[i], aux[j]))
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
		this.sort(a, 0, a.length - 1);
	}

}

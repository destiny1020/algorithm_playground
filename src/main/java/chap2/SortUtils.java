package chap2;

import java.lang.reflect.Array;

import chap2.heapsort.HeapNode;

public class SortUtils
{
	@SuppressWarnings("unchecked")
	public static <T> boolean less(Comparable<T> v, Comparable<T> w)
	{
		return v.compareTo((T) w) < 0;
	}

	public static boolean less(int v, int w)
	{
		return v < w;
	}

	@SuppressWarnings("unchecked")
	public static <T> boolean lessOrEqual(Comparable<T> v, Comparable<T> w)
	{
		int result = v.compareTo((T) w);

		return result < 0 || result == 0;
	}

	public static boolean lessOrEqual(int v, int w)
	{
		return v <= w;
	}

	public static <T> void exch(Comparable<T>[] a, int i, int j)
	{
		Comparable<T> temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static void exch(int[] a, int i, int j)
	{
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	@SuppressWarnings("unchecked")
	public static <T> boolean eq(Comparable<T> a, Comparable<T> b)
	{
		return (a.compareTo((T) b) == 0);
	}

	public static <T> void show(Comparable<T>[] a)
	{
		for (int i = 0; i < a.length; i++)
		{
			System.out.print(a[i] + " ");
		}

		System.out.println();
	}

	public static <T> boolean isSorted(Comparable<T>[] a)
	{
		for (int i = 1; i < a.length; i++)
		{
			if (less(a[i], a[i - 1]))
			{
				return false;
			}
		}

		return true;
	}

	// Index based: Check range [start, end)
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static boolean isHeapSortedReverse(HeapNode[] a, int start, int end)
	{
		for (int i = start + 1; i < end; i++)
		{
			if (less(a[i - 1].getKey(), a[i].getKey()))
			{
				return false;
			}
		}

		return true;
	}
}

package chap2.heapsort;

import java.util.Collection;
import java.util.PriorityQueue;

import chap2.Sortable;

public class HeapSortByPQ<Key> implements Sortable<Key>
{
	private PriorityQueue<Key> pq;
	private Key[] results;
	
	public Key[] getResults()
	{
		return this.results;
	}
	
	@SuppressWarnings("unchecked")
	public HeapSortByPQ(Collection<? extends Key> c)
	{
		pq = new PriorityQueue<Key>(c);
		this.results = (Key[])new Object[c.size()];
	}
	
	/**
	 * The parameter a has no meaning
	 */
	@Override
	public void sortIt(Comparable<Key>[] a)
	{
		this.sort();
	}
	
	private void sort()
	{
		int index = 0;
		
		while(!this.pq.isEmpty())
		{
			results[index++] = (Key)this.pq.poll();
		}
	}
}

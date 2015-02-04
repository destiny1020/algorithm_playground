package chap2.heapsort;

import java.util.Iterator;
import java.util.NoSuchElementException;

import chap2.SortUtils;
import chap2.Sortable;

/**
 * This class has some problems on line 26, ClassCastException, cannot convert Object array
 * to Comparable array
 * @author Destiny
 *
 * @param <Item>
 */
public class MinPQSingle<Item extends Comparable<Item>> implements Sortable<Item>
{
	private Item[] nodes;
	private int capacity;
	// The index of heap is starting from 1
	private int current = 1;
	
	@SuppressWarnings("unchecked")
	public MinPQSingle()
	{
		this.capacity = 10;
		nodes = (Item[])new Object[11];
	}
	
	public MinPQSingle(Item[] nodes)
	{
		this.nodes = nodes;
	}
	
	public Item[] getNodes()
	{
		return this.nodes;
	}
	
	public boolean isEmpty()
	{
		return this.current == 1;
	}
	
	public int size()
	{
		return this.current - 1;
	}
	
	public void insert(Item node)
	{
		nodes[current] = node;
		this.swim(current);
		this.current++;
	}
	
	public Item delMin()
	{
		Item min = this.nodes[1];
		
		this.nodes[1] = this.nodes[--current];
		this.nodes[current] = null;
		capacity--;
		
		this.sink(1, this.current);
		
		return min;
	}
	
	private void swim(int index)
	{
		while(index / 2 != 0)
		{
			Item parent = this.nodes[index / 2];
			int result = this.nodes[index].compareTo(parent);
			
			// The diff between maxPQ and minPQ
			if(result < 0)
			{
				SortUtils.exch(this.nodes, index, index / 2);
				index /= 2;
			}
			else
			{
				break;
			}
		}
	}	
	
	private void sink(int index, int boundry)
	{
		while(index * 2 <= boundry - 1)
		{
			int i = index * 2;
			
			if(i + 1 <= boundry - 1 && this.nodes[i].compareTo(this.nodes[i + 1]) > 0)
			{
				i++;
			}
			
			int result = this.nodes[index].compareTo(this.nodes[i]);
			
			// The diff between maxPQ and minPQ
			if(result > 0)
			{
				SortUtils.exch(this.nodes, index, i);
				index = i;
			}
			else
			{
				break;
			}
		}
	}
	
	// Sort the heapnodes
	// Because this is a min pq, the final result will be in decreasing order, such as 5, 4, 3, 2, 1
	public void sort()
	{
		int i = this.current - 1;
		
		while(i > 1)
		{
			SortUtils.exch(nodes, 1, i--);
			this.sink(1, i + 1);
		}
	}
	
	/**
	 * To test whether the nodes are in min heap order
	 * @return true if nodes are in heap order, false if not
	 */
	public boolean isMinHeapOrder()
	{
		return this.isMinHeapOrder(1);
	}
	
	private boolean isMinHeapOrder(int i)
	{
		// If the i is greater or equal to current, return true because there is no node of index i
		if(i > this.current - 1)
		{
			return true;
		}
		int left = i * 2;
		int right = left + 1;
		
		if(left < this.current && SortUtils.less(this.nodes[left], this.nodes[i]))
		{
			return false;
		}
		if(right < this.current && SortUtils.less(this.nodes[right], this.nodes[i]))
		{
			return false;
		}
		
		return this.isMinHeapOrder(left) && this.isMinHeapOrder(right);
	}
	
	public Iterator<Item> iterator()
	{
		return new HeapIterator();
	}
	
	private class HeapIterator implements Iterator<Item>
	{
		private MinPQSingle<Item> copy;
		
		public HeapIterator()
		{
			this.copy = new MinPQSingle<Item>();
			this.copy.capacity = size();
			this.copy.current = size() + 1;
			this.copy.nodes = getNodes().clone();
		}
		
		@Override
		public boolean hasNext()
		{
			return !copy.isEmpty();
		}

		@Override
		public Item next()
		{
			if(hasNext())
			{
				Item nodeNext = this.copy.delMin();
				return nodeNext;
			}
			else
			{
				throw new NoSuchElementException();
			}
		}

		@Override
		public void remove()
		{
			throw new UnsupportedOperationException();
		}
		
	}
	
	/**
	 * This method is from interface Sortable
	 * In this scenario, there are two possible solutions:
	 * 1) The underlying nodes have been initialized, and your intention is to sort it,
	 * 	  pay attention to give param a null value.
	 * 2) If you want to ignore the underlying nodes and sort the given parameter, pay
	 *    attention to give a non-null param into this method.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void sortIt(Comparable<Item>[] a)
	{
		if(null != a && a.length > 0)
		{
			int length = a.length;
			
			// If the heapnodes have been initialized, then call the sort method directly
			if(this.capacity == 0)
			{				
				this.nodes = (Item[])new Object[length + 1];
				initNodesByParam(a);
				createHeapOrder();
			}
		}
		
		// Before calling sort, the heapnodes should be in heap order
		this.sort();
	}

	private void createHeapOrder()
	{
		int boundry = this.current;
		for(int k = boundry / 2; k >= 1; k--)
		{
			this.sink(k, boundry);
		}
	}

	@SuppressWarnings("unchecked")
	private void initNodesByParam(Comparable<Item>[] a)
	{
		this.capacity = a.length;
		this.current = capacity + 1;
		
		for(int i = 0; i < a.length; i++)
		{
			this.nodes[i + 1] = (Item)a[i];
		}
	}
}

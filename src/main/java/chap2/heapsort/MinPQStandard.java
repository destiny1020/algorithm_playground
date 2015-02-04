package chap2.heapsort;

import java.util.Comparator;
import java.util.Iterator;

import chap2.Sortable;


public class MinPQStandard<Key> implements Sortable<Key>, Iterable<Key>
{
	/**
	 * The underlying array for storing elements in PQ
	 */
	private Key[] pq;
	
	/**
	 * For instance, capacity = 1 means there are two entries in pq, pq[0] is ignored
	 * and pq[1] is the first element in pq
	 */
	private int capacity;
	
	/**
	 * Current means the current index, for instance:
	 * current = 1 means the pq is empty, current = 2 means there is only one element in pq
	 */
	private int current;
	
	/**
	 * The comparator for the keys in pq
	 */
	private Comparator<Key> comparator;
	
	/**
	 * Default capacity is 10
	 */
	public MinPQStandard()
	{
		this(10);
	}
	
	@SuppressWarnings("unchecked")
	public MinPQStandard(int capacity)
	{
		if(capacity < 10)
		{
			capacity = 10;
		}
		this.capacity = capacity;
		this.current = 1;
		this.pq = (Key[])new Object[this.capacity + 1];
	}
	
	@SuppressWarnings("unchecked")
	public MinPQStandard(int capacity, Comparator<Key> comparator)
	{
		this.capacity = capacity;
		this.current = 1;
		this.pq = (Key[])new Object[this.capacity + 1];
		this.comparator = comparator;
	}
	
	public MinPQStandard(Comparator<Key> comparator)
	{
		this(1, comparator);
	}
	
	@SuppressWarnings("unchecked")
	public MinPQStandard(Key[] keys)
	{
		this.capacity = keys.length;
		this.current = this.capacity + 1;
		this.pq = (Key[])new Object[this.capacity + 1];
		for(int i = 0; i < this.capacity; i++)
		{
			pq[i + 1] = keys[i];
		}
		createHeapOrder();
	}
	
	/**
	 * Determine whether the PQ is empty
	 * @return true if the PQ is empty
	 */
	public boolean isEmpty()
	{
		return this.current == 1;
	}
	
	/**
	 * Get the capacity of PQ
	 * @return Capacity of PQ
	 */
	public int getCapacity()
	{
		return this.capacity;
	}
	
	/**
	 * Get the current of PQ
	 * @return Current index
	 */
	public int getCurrent()
	{
		return this.current;
	}
	
	public void setPQ(Key[] pq)
	{
		this.pq = pq;
		this.capacity = pq.length - 1;
		this.current = this.capacity + 1;
		this.createHeapOrder();
	}
	
	/**
	 * Aimed to test
	 * @param index
	 * @return
	 */
	public Key getKeyByIndex(int index)
	{
		return this.pq[index];
	}
	
	/**
	 * Defined method in interface Iterable<Key>
	 * @return an iterator for iterating elements in pq
	 */
	@Override
	public Iterator<Key> iterator()
	{
		return new HeapIterator();
	}
	
	/**
	 * A private class aimed to support iteration on pq
	 * @author Destiny
	 *
	 */
	private class HeapIterator implements Iterator<Key>
	{
		private MinPQStandard<Key> copy;
		
		public HeapIterator()
		{
			if(null == comparator)
			{
				copy = new MinPQStandard<Key>(capacity);
			}
			else
			{
				copy = new MinPQStandard<Key>(capacity, comparator);
			}
			
			// Add elements into the copy
			for(int i = 1; i < current; i++)
			{
				// Problematic OP, read only 
				this.copy.pq[i] = pq[i];
			}
			
			copy.current = current;
		}
		
		@Override
		public boolean hasNext()
		{
			return !copy.isEmpty();
		}

		@Override
		public Key next()
		{
			return copy.delMin();
		}

		@Override
		public void remove()
		{
			throw new UnsupportedOperationException("Heap Iterator does not support this operation");
		}
		
	}

	/**
	 * Defined method in interface Sortable<Key>
	 * @param a an array of type Comparable<Key>, has some bugs here -_-, just ignore the param
	 */
	@Override
	public void sortIt(Comparable<Key>[] a)
	{
		// If a is not null and have elements in it
//		if(null != a && a.length > 0)
//		{
//			int length = a.length;
//			
//			initNodesByParam(a);
//		}
		
		// Before calling sort, the heapnodes should be in heap order
		this.sort();
	}
	
	/**
	 * Helper method for heap sort
	 */
	private void sort()
	{
		int index = this.current - 1;
		
		while(index > 1)
		{
			this.exch(1, index);
			sink(1, index--);
		}
	}

//	@SuppressWarnings("unchecked")
//	private void initNodesByParam(Comparable<Key>[] a)
//	{
//		// TODO Auto-generated method stub
//		for(int i = 0; i < a.length; i++)
//		{
//			this.insert((Key)a[i]);
//		}
//	}

	/**
	 * Public API for determining whether the underlying pq is in heap order
	 * @return
	 */
	public boolean isMinHeap()
	{
		return isMinHeap(1);
	}
	
	/**
	 * Get the minimum Key in PQ, that is, the first element in PQ
	 * @return The minimum Key in PQ
	 */
	public Key min()
	{
		return this.pq[1];
	}
	
	/**
	 * Insert the newest key into the position current, and swim it
	 * @param key 
	 */
	public void insert(Key key)
	{
		if(this.capacity == this.current - 1)
		{
			this.resize(2 * this.capacity + 1);
		}
		
		// Insert the key into the position:current
		this.pq[this.current] = key;
		
		// Let the newest element swim, and increase current by one
		this.swim(this.current++);
	}
	
	/**
	 * Delete and get the minimum element in PQ
	 * @return Minimum element in PQ
	 */
	public Key delMin()
	{
		if(1 == this.current)
		{
			throw new RuntimeException("PQ is null");
		}
		
		// After this operation, the min element will be on position current - 1
		this.exch(1, this.current - 1);
		
		// Retrieve the min element and decrease the current
		Key min = this.pq[this.current - 1];
		this.current--;
		
		this.sink(1, this.current);
		
		// Avoid loitering and help with GC
		this.pq[this.current] = null;

		// If the current index is equal to one fourths of the capacity, resize the PQ
		if((this.current > 1) && ((this.current - 1) == (this.capacity / 4)))
		{
			this.resize(this.capacity / 2);
		}
		
		return min;
	}

	/**
	 * Helper method for resizing PQ
	 * @param newCapacity
	 */
	@SuppressWarnings("unchecked")
	private void resize(int newCapacity)
	{
		Key[] newPQ = (Key[])new Object[newCapacity];
		
		for(int i = 1; i <= this.current - 1; i++)
		{
			newPQ[i] = this.pq[i];
		}
		
		this.pq = newPQ;
		this.capacity = newCapacity - 1;
	}

	/**
	 * Helper method for determining whether the pq is in heap order
	 * @param i index 
	 * @return true if the subtree rooted at i is in heap order
	 */
	private boolean isMinHeap(int i)
	{
		if(i > this.capacity)
		{
			return true;
		}
		
		int left = 2 * i;
		int right = 2 * i + 1;
		
		if(left <= this.capacity && this.greater(i, left))
		{
			return false;
		}
		if(right <= this.capacity && this.greater(i, right))
		{
			return false;
		}
		
		return this.isMinHeap(left) && this.isMinHeap(right);
	}

	/**
	 * Helper method for adjusting the underlying pq to heap order by using sink()
	 */
	private void createHeapOrder()
	{
		int boundary = this.current;
		for(int i = boundary / 2; i > 0; i--)
		{
			this.sink(i, boundary);
		}
	}

	/**
	 * Helper method for keeping the pq in heap order
	 * @param index
	 */
	private void swim(int index)
	{
//		while(index / 2 != 0)
//		{
//			HeapNode<Key, Value> parent = this.nodes[index / 2];
//			int result = this.nodes[index].compareTo(parent.getKey());
//			
//			// The diff between maxPQ and minPQ
//			if(result < 0)
//			{
//				SortUtils.exch(this.nodes, index, index / 2);
//				index /= 2;
//			}
//			else
//			{
//				break;
//			}
//		}
		
		while(index / 2 != 0 && this.greater(index / 2, index))
		{
			this.exch(index / 2, index);
			index /= 2;
		}
	}
	
	/**
	 * Helper method for keep in heap order
	 * @param index
	 * @param boundary
	 */
	private void sink(int index, int boundary)
	{
		while(index * 2 <= boundary - 1)
		{
			int i = index * 2;
			
			if(i + 1 <= boundary - 1 && this.greater(i, i + 1))
			{
				i++;
			}
			
			if(!this.greater(index, i))
			{
				break;
			}
			this.exch(index, i);
			index = i;
		}
	}

	/**
	 * Helper method for compares and swaps
	 * @param i index of the first element to be swapped
	 * @param j index of the second element to be swapped
	 */
	private void exch(int i, int j)
	{
		Key swap = this.pq[i];
		this.pq[i] = this.pq[j];
		this.pq[j] = swap;
	}

	/**
	 * Helper method for compares and swaps
	 * @param i index i
	 * @param j index j
	 * @return true if pq[i] is greater than pq[j]
	 */
	@SuppressWarnings("unchecked")
	private boolean greater(int i, int j)
	{
		// If comparator is null, convernt the elements into Comparable and compare
		if(null == this.comparator)
		{
			return ((Comparable<Key>)pq[i]).compareTo(pq[j]) > 0;
		}
		else
		{
			return this.comparator.compare(pq[i], pq[j]) > 0;
		}
	}
}

package chap2.heapsort;

import java.util.Iterator;
import java.util.NoSuchElementException;

import chap2.SortUtils;
import chap2.Sortable;

public class MinPQ<Key extends Comparable<Key>, Value> implements Sortable<Key>
{
	private HeapNode<Key, Value>[] nodes;
	private int N;
	private int current = 1;
	
	public HeapNode<Key, Value>[] getNodes()
	{
		return nodes;
	}
	
	public MinPQ(HeapNode<Key, Value>[] nodes)
	{
		this.N = nodes.length - 1;
		this.nodes = nodes;
	}
	
	public MinPQ()
	{
	}
	
	public boolean isEmpty()
	{
		return this.current == 1;
	}
	
	public int size()
	{
		return this.current - 1;
	}
	
	public void insert(HeapNode<Key, Value> node)
	{
		nodes[current] = node;
		this.swim(current);
		this.current++;
	}
	
	public HeapNode<Key, Value> delMin()
	{
		HeapNode<Key, Value> min = this.nodes[1];
		
		this.nodes[1] = this.nodes[--current];
		this.nodes[current] = null;
		N--;
		
		this.sink(1, this.current);
		
		return min;
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

	private void swim(int index)
	{
		while(index / 2 != 0)
		{
			HeapNode<Key, Value> parent = this.nodes[index / 2];
			int result = this.nodes[index].compareTo(parent.getKey());
			
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
			
			if(i + 1 <= boundry - 1 && this.nodes[i].getKey().compareTo(this.nodes[i + 1].getKey()) > 0)
			{
				i++;
			}
			
			int result = this.nodes[index].getKey().compareTo(this.nodes[i].getKey());
			
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
		
		if(left < this.current && SortUtils.less(this.nodes[left].getKey(), this.nodes[i].getKey()))
		{
			return false;
		}
		if(right < this.current && SortUtils.less(this.nodes[right].getKey(), this.nodes[i].getKey()))
		{
			return false;
		}
		
		return this.isMinHeapOrder(left) && this.isMinHeapOrder(right);
	}
	
	public Iterator<HeapNode<Key, Value>> iterator()
	{
		return new HeapIterator();
	}
	
	private class HeapIterator implements Iterator<HeapNode<Key, Value>>
	{
		private MinPQ<Key, Value> copy;
		
		public HeapIterator()
		{
			this.copy = new MinPQ<Key, Value>();
			this.copy.N = size();
			this.copy.current = size() + 1;
			this.copy.nodes = getNodes().clone();
		}
		
		@Override
		public boolean hasNext()
		{
			return !copy.isEmpty();
		}

		@Override
		public HeapNode<Key, Value> next()
		{
			if(hasNext())
			{
				HeapNode<Key, Value> nodeNext = this.copy.delMin();
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

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
		for(HeapNode<Key, Value> node : this.nodes)
		{
			sb.append(node);
			sb.append("\n");
		}
		
		return sb.toString();
	}

	// Unified interface for sorting
	@SuppressWarnings("unchecked")
	@Override
	public void sortIt(Comparable<Key>[] a)
	{
		if(null != a && a.length > 0)
		{
			int length = a.length;
			
			// If the heapnodes have been initialized, then call the sort method directly
			if(this.N == 0)
			{				
				this.nodes = new HeapNode[length + 1];
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

	@SuppressWarnings(value = {"unchecked", "rawtypes"})
	private void initNodesByParam(Comparable<Key>[] a)
	{
		this.N = a.length;
		this.current = N + 1;
		
		for(int i = 0; i < a.length; i++)
		{
			this.nodes[i + 1] = new HeapNode(a[i], a[i]);
		}
	}
}

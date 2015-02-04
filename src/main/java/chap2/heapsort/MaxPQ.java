package chap2.heapsort;

import chap2.SortUtils;

public class MaxPQ<Key extends Comparable<Key>, Value>
{
	private HeapNode<Key, Value>[] nodes;
	private int N;
	private int current = 1;
	
	public MaxPQ(HeapNode<Key, Value>[] nodes)
	{
		this.N = nodes.length - 1;
		this.nodes = nodes;
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
	
	public HeapNode<Key, Value> delMax()
	{
		HeapNode<Key, Value> max = this.nodes[1];
		
		this.nodes[1] = this.nodes[--current];
		this.nodes[current] = null;
		N--;
		
		this.sink(1);
		
		return max;
	}

	private void swim(int index)
	{
		while(index / 2 != 0)
		{
			HeapNode<Key, Value> parent = this.nodes[index / 2];
			int result = this.nodes[index].compareTo(parent.getKey());
			if(result > 0)
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
	
	private void sink(int index)
	{
		while(index * 2 <= this.current - 1)
		{
			int i = index * 2;
			
			if(i + 1 <= N && this.nodes[i].getKey().compareTo(this.nodes[i + 1].getKey()) < 0)
			{
				i++;
			}
			
			int result = this.nodes[index].getKey().compareTo(this.nodes[i].getKey());
			
			if(result < 0)
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
}

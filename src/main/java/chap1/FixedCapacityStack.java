package chap1;

import java.util.Iterator;

public class FixedCapacityStack<Item> implements Iterable<Item>
{
	private Item[] a;
	private int N = 0;
	
	@SuppressWarnings("unchecked")
	public FixedCapacityStack(int cap)
	{
		this.a = (Item[])new Object[cap];
	}
	
	public boolean isEmpty()
	{
		return (N == 0);
	}
	
	public int size()
	{
		return N;
	}
	
	public void push(Item item)
	{
		if(N == a.length)
		{
			this.resize(2 * a.length);
		}
		a[N++] = item;
	}
	
	public Item pop()
	{
		Item item = a[--N];
		a[N] = null;
		
		if(N > 0 && N == a.length / 4)
		{
			this.resize(a.length / 2);
		}
		
		return item;
	}
	
	@SuppressWarnings("unchecked")
	public void resize(int max)
	{
		Item[] temp = (Item[])new Object[max];
		
		for(int i = 0; i < N; i++)
		{
			temp[i] = this.a[i];
		}
		
		this.a = temp;
	}
	
	@Override
	public Iterator<Item> iterator()
	{
		return new ReverseArrayIterator<Item>(a, N);
	}
}

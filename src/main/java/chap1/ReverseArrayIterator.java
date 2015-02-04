package chap1;

import java.util.Iterator;

public class ReverseArrayIterator<Item> implements Iterator<Item>
{
	private int N;
	private Item[] a;
	
	public ReverseArrayIterator(Item[] a, int cap)
	{
		this.a = a;
		this.N = cap;
	}
	
	@Override
	public boolean hasNext()
	{
		return this.N > 0;
	}

	@Override
	public Item next()
	{
		return a[--N];
	}

	@Override
	public void remove()
	{
		// TODO Auto-generated method stub
	}
	
}

package chap1;

import java.util.Iterator;

public class LinkedStack<Item> implements Iterable<Item>
{
	private Node<Item> top;
	private int N = 0;
	
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
		Node<Item> oldFirst = this.top;
		top = new Node<Item>();
		top.item = item;
		top.next = oldFirst;
		N++;
	}
	
	public Item pop()
	{
		Item item = top.item;
		top = top.next;
		N--;
		return item;
	}
	
	public Item peek()
	{
		return top.item;
	}
	
	@Override
	public Iterator<Item> iterator()
	{
		return new LinkedListIterator<Item>(top);
	}
	
	@Override
	public String toString()
	{
		Node<Item> temp = top;
		String result = null;
		
		for(; temp != null; temp = temp.next)
		{
			if(temp == top)
				result = temp.item.toString();
			else
				result += "->" + temp.item;
		}
		
		return result;
	}
}

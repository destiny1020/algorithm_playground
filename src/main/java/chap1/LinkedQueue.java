package chap1;

import java.util.Iterator;

public class LinkedQueue<Item> implements Iterable<Item>
{
	private Node<Item> tail;
	private Node<Item> head;
	private int N;
	
	public boolean isEmpty()
	{
		return (0 == N);
	}
	
	public int size()
	{
		return N;
	}
	
	public void enqueue(Item item)
	{
		Node<Item> oldLast = tail;
		tail = new Node<Item>();
		tail.item = item;
		tail.next = null;
		
		if(0 == N)
		{
			head = tail;
		}
		else
		{
			oldLast.next = tail;
		}
		N++;
	}
	
	public Item dequeue()
	{
		if(this.isEmpty())
		{
			return null;
		}
		
		Item item = head.item;
		head = head.next;
		if(this.isEmpty())
		{
			tail = null;
		}
		N--;
		return item;
	}

	@Override
	public Iterator<Item> iterator()
	{
		return new LinkedListIterator<Item>(head);
	}
}

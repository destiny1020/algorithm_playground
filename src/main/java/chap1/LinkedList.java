package chap1;

import java.util.Iterator;

/**
 * 循环单链表数据结构
 * @author Destiny
 *
 * @param <Item>
 */
public class LinkedList<Item extends Comparable<Item>> implements
		Iterable<Item>
{
	// 头结点
	private Node<Item> head;
	
	// 尾结点
	private Node<Item> current;
	
	// 当前链表的长度
	private int N;

	public LinkedList()
	{
		head = new Node<Item>();
		
		// 让头结点的指向自身
		head.next = head;
		current = head;
	}

	public int size()
	{
		return N;
	}

	public boolean isEmpty()
	{
		return (0 == N);
	}

	/**
	 * 给定一个Item，进行链表的添加操作
	 * @param item
	 * @return
	 */
	public Node<Item> insert(Item item)
	{
		Node<Item> oldCurrent = current;
		this.current = new Node<Item>();
		this.current.item = item;
		
		// 尾结点的下一个结点总是指向head结点
		this.current.next = head;
		oldCurrent.next = current;
		N++;

		return this.current;
	}

	/**
	 * 直接给一个Node，进行链表的添加操作
	 * @param node
	 * @return
	 */
	public Node<Item> insert(Node<Item> node)
	{
		Node<Item> oldCurrent = current;
		this.current = node;
		oldCurrent.next = current;
		N++;

		return this.current;
	}

	//TODO
	/**
	 * 还没有考虑对于current的invariant
	 * 
	 * 用于链表内部节点的调整，以及添加新的节点，这个时候nodeInsert的next应该为空
	 * 如果需要插入整个链表，使用insertAfter(Node<Item> nodeBefore, LinkedList<Item> list)
	 * 
	 * 在某个节点后进行插入
	 * @param nodeBefore 在该节点后进行插入操作
	 * @param nodeInsert 待插入的节点
	 * @return
	 */
	public Node<Item> insertAfter(Node<Item> nodeBefore, Node<Item> nodeInsert)
	{
		// 如果实在尾结点后进行插入，等同于插入操作
		if (nodeBefore == this.current)
		{
			return this.insert(nodeInsert);
		} else
		{
			// 首先获取位于插入位置后的那个结点
			Node<Item> nodeAfter = nodeBefore.next;

			// 加上这个bool变量是要考虑该insertAfter操作是否是链表内部进行的，
			// 也就是说，如果nodeInsert是这个链表中的一个节点的话，N是不会增加的
			// 有两种情况，这个节点是链表中的某个节点(不是尾结点)，对应着
			boolean operationWithinThisList = false;
			
			// 如果待插入节点的next field存在节点
			// 表示这个节点是链表内部的某个节点
			if (null != nodeInsert.next && head != nodeInsert.next)
			{
				operationWithinThisList = true;
				Node<Item> nodeInsertAfter = nodeInsert.next;
				nodeAfter.next = nodeInsertAfter;
				
				// 如果待插入的节点的下一个节点是head节点的话
			} else if(head == nodeInsert.next) {
				operationWithinThisList = true;
				// 这种情况比较麻烦，因为需要获取尾结点之前的那个节点
				Node<Item> nodeBeforeLast = head;
				
				// 遍历操作，效率不太好？
				while(nodeBeforeLast.next != current) {
					nodeBeforeLast = nodeBeforeLast.next;
				}
				
				// 获取到了尾结点之前的那个节点之后，将该节点直接指向头结点
				nodeBeforeLast.next = head;
			}

			nodeBefore.next = nodeInsert;
			nodeInsert.next = nodeAfter;
			
			if(!operationWithinThisList) {
				N++;
			}

			return nodeInsert;
		}
	}

	/**
	 * 删除操作，传入参数为索引号，但是这个索引是1-based，而不是0
	 * 比如要删除第一个节点，即head后的那个节点，传入1
	 * @param index
	 */
	public void delete(int index)
	{
		// 如果传入为0，不合法
		if(0 == index) {
			return;
		}
		
		// 如果该链表为空或者传入的index比N还要大，直接返回
		if (isEmpty() || N < index)
		{
			return;
		}

		// 待删节点的引用指向head节点的下一个
		Node<Item> nodeDelete = this.head.next;
		Node<Item> nodeBefore = this.head;
		int count = 1;

		while (count < index)
		{
			nodeBefore = nodeDelete;
			nodeDelete = nodeDelete.next;
			count++;
		}

		// 如果要删除的是最后一个节点
		if (index == N)
		{
			// 设置尾结点为现在尾结点的前一个节点
			this.current = nodeBefore;
		}

		nodeBefore.next = nodeDelete.next;
		N--;
	}

	/**
	 * 删除所有节点值为item的节点
	 * 
	 * @param item
	 */
	public void removeAll(Item item)
	{
		// 考虑各种边界条件
		if(isEmpty()) {
			return;
		}
		
		Node<Item> first = this.head;
		Node<Item> second = this.head.next;
		
		// 当first和second不是指向的同一节点
		while (head != second)
		{
			while (second.item.equals(item))
			{
				// 调用removeAfter方法对second节点进行删除
				this.removeAfter(first);
				second = first.next;
				
				// 如果此时second节点已经指向了head节点，表示链表已经遍历完毕，返回
				// 也表示最后一个节点也被删除了
				if (head == second)
				{
					//此时first指向的节点也就是尾结点了
					current = first;
					return;
				}
			}
			first = second;
			second = second.next;
		}
	}

	/**
	 * 删除最后一个节点
	 */
	public void removeLast()
	{
		// 测试边界条件
		if(isEmpty()) {
			return;
		}
		
		Node<Item> first = head;
		Node<Item> second = head.next;
		
		// 当second的下一个节点为head的时候，second也就是指向尾结点了
		while(second.next != head) {
			first = second;
			second = second.next;
		}
		
		// 设置尾节点的前面一个节点的next为头结点
		first.next = head;
		
		// 设置尾结点为first
		current = first;
	}

	/**
	 * 删除指定节点之后的所有节点
	 * @param node
	 */
	public void removeAfter(Node<Item> node)
	{
		// 如果node就是尾节点，那么直接返回
		if (node == this.current)
		{
			return;
		}
		// 关键是要计算链表的尺寸变化
		Node<Item> nodeBak = node;
		int sizeDelta = 0;
		while(node.next != head) {
			sizeDelta++;
			node = node.next;
		}
		
		// 计算删除node后面所有节点之后的size
		N -= sizeDelta;
		
		// 最后直接将node的下一个节点指向头结点即可
		nodeBak.next = head;
		
		// 同时还需要更新尾节点的引用
		current = nodeBak;
	}

	// TODO
	public boolean find(Item item)
	{
		Iterator<Item> iter = this.iterator();
		// Skip the head node
		iter.next();

		while (iter.hasNext())
		{
			Item i = iter.next();
			if (null != i && i.equals(item))
			{
				return true;
			}
		}

		return false;
	}

	public Node<Item> max()
	{
		if (this.isEmpty())
		{
			return null;
		}

		Node<Item> first = this.head.next;
		Node<Item> max = first;

		while (first.next != head && null != first.next)
		{
			max = (first.next.item.compareTo(max.item) == 1) ? first.next : max;
			first = first.next;
		}

		return max;
	}

	public Node<Item> maxRecursive(Node<Item> start)
	{
		if (null == start.next || start.next == head)
		{
			return start;
		}

		Node<Item> maxNode = maxRecursive(start.next);

		return (start.item.compareTo(maxNode.item) == 1) ? start : maxNode;
	}

	// Influence Range (start, end]
	public Node<Item> reverse(Node<Item> start, Node<Item> end)
	{
		Node<Item> last = end.next;
		Node<Item> begin = start.next;

		Node<Item> first = start.next;
		Node<Item> second = first.next;
		Node<Item> third = second.next;

		while (first != end)
		{
			second.next = first;

			first = second;
			second = third;
			third = third.next;
		}

		start.next = end;
		begin.next = last;

		if (end == this.current)
		{
			this.current = begin;
		}

		return start;
	}

	public Node<Item> reverse()
	{
		return this.reverse(this.head, this.current);
	}

	public Node<Item> reverseRecursive()
	{
		return this.reverseRecursive(this.head, this.current);
	}

	public Node<Item> reverseRecursive(Node<Item> start, Node<Item> end)
	{
		Node<Item> last = end.next;
		Node<Item> begin = start.next;

		Node<Item> tail = this.reverseRecursiveInner(begin, end);
		tail.next = last;
		start.next = end;

		if (end == this.current)
		{
			this.current = begin;
		}

		return start;
	}

	// Influence Range [begin, end]
	private Node<Item> reverseRecursiveInner(Node<Item> begin, Node<Item> end)
	{
		if (begin == end)
		{
			return end;
		}

		Node<Item> tail = reverseRecursiveInner(begin.next, end);
		tail.next = begin;
		return begin;
	}

	public Node<Item> getNode(int index)
	{
		int count = 0;
		Node<Item> target = this.head;

		while (count < index)
		{
			target = target.next;
			count++;
		}

		return target;
	}

	public int getIndex(Node<Item> node)
	{
		Node<Item> target = this.head;
		int count = 1;

		while (target.next != head && N > 0)
		{
			if (target.next.item == node.item && target.next.next == node.next)
			{
				return count;
			}
			target = target.next;
			count++;
		}

		return -1;
	}

	@Override
	public Iterator<Item> iterator()
	{
		return new LinkedListIterator<Item>(this.head);
	}

	// Getters and Setters

	public Node<Item> getHead()
	{
		return head;
	}

	public void setHead(Node<Item> head)
	{
		this.head = head;
	}

	public Node<Item> getCurrent()
	{
		return current;
	}

	public void setCurrent(Node<Item> current)
	{
		this.current = current;
	}

	@Override
	public String toString()
	{
		Node<Item> temp = head.next;
		String result = temp.item.toString();

		for (temp = temp.next; temp.item != null; temp = temp.next)
		{
			result += "->" + temp.item.toString();
		}

		return result;
	}
}

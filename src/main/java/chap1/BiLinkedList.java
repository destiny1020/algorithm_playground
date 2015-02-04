package chap1;

import chap3.BST;
import chap3.Node;

/**
 * 实现了双向链表的一些功能
 * 
 * @author Destiny
 * 
 * @param <Item>
 */
public class BiLinkedList<Item extends Comparable<Item>> {

	private BiNode<Item> head;
	private BiNode<Item> current;

	private int size = 0;

	public BiLinkedList() {
		head = new BiNode<Item>(null, head, head);

		current = head;
	}

	/**
	 * 判断此双向链表是否为空
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return (0 == size);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Head");
		BiNode<Item> first = head.next;
		while (first != head) {
			sb.append(" -> " + first.item);
			first = first.next;
		}

		sb.append(System.getProperty("line.separator"));
		sb.append("Head");
		BiNode<Item> last = head.prior;
		while (last != head) {
			sb.append(" -> " + last.item);
			last = last.prior;
		}

		return sb.toString();
	}

	/**
	 * 向双向链表的头结点之后添加一个节点
	 * 
	 * @param item
	 * @return 返回的是head，而非current，这一点和addToTail相区分
	 */
	public BiNode<Item> addAfterHead(Item item) {

		// 如果item为空，没有添加的必要
		if (null == item) {
			return null;
		}

		// 调整引用
		BiNode<Item> first = head.next;
		BiNode<Item> insert = new BiNode<Item>(item, head, first);
		head.next = insert;
		first.prior = insert;

		return head;
	}

	/**
	 * 向双向链表的头结点之后添加一个节点
	 * 
	 * @param insert
	 * @return 返回的是head，而非current
	 */
	public BiNode<Item> addAfterHead(BiNode<Item> insert) {
		// 如果insert本身为空或者其中包含的item为空
		if (null == insert || null == insert.item) {
			return null;
		}

		// 如果待插入的节点含有前驱结点或者后续节点，直接返回null，不添加
		if (null != insert.prior || null != insert.next) {
			return null;
		}

		// 设置insert的引用信息
		BiNode<Item> first = head.next;
		first.prior = insert;
		head.next = insert;
		insert.prior = head;
		insert.next = first;

		return head;
	}

	/**
	 * 将整个list插入到当前链表的头结点之后
	 * 
	 * @param list
	 * @return 当前链表
	 */
	public BiLinkedList<Item> addListAfterHead(BiLinkedList<Item> list) {

		// 如果list为空或者list的size为0，没有添加的必要
		if (null == list || list.isEmpty()) {
			return this;
		}

		BiNode<Item> first = head.next;
		BiNode<Item> list_first = list.getHead().next;
		BiNode<Item> list_current = list.getCurrent();

		// 调整引用
		head.next = list_first;
		list_first.prior = head;
		list_current.next = first;
		first.prior = list_current;

		return this;
	}

	/**
	 * 向双向链表的末尾添加一个节点
	 * 
	 * @param item
	 * @return
	 */
	public BiNode<Item> addToTail(Item item) {

		// 如果item为空，没有添加的必要
		if (null == item) {
			return null;
		}

		BiNode<Item> insert = new BiNode<Item>(item, current, head);

		current.next = insert;
		head.prior = insert;

		// 更新current节点为当前tail节点
		current = insert;

		return current;
	}

	/**
	 * 作为上面方法的一个shortcut，添加一个离散的节点
	 * 
	 * @param insert
	 * @return
	 */
	public BiNode<Item> addToTail(BiNode<Item> insert) {

		// 如果insert本身为空或者其中包含的item为空
		if (null == insert || null == insert.item) {
			return null;
		}

		// 如果待插入的节点含有前驱结点或者后续节点，直接返回null，不添加
		if (null != insert.prior || null != insert.next) {
			return null;
		}

		// 设置insert的引用信息
		insert.prior = current;
		insert.next = head;

		current.next = insert;
		head.prior = insert;

		// 更新current节点为当前tail节点
		current = insert;

		return current;
	}

	/**
	 * 将一个链表全部都加到当前链表尾部
	 * 
	 * @param list
	 * @return
	 */
	public BiLinkedList<Item> addListToTail(BiLinkedList<Item> list) {

		// 首先判断是否有添加的必要
		if (null == list || list.isEmpty()) {
			return this;
		}

		// 获得待插入链表的第一个节点
		BiNode<Item> list_first = list.head.next;
		list_first.prior = current;

		// 获得待插入链表的最后一个节点
		BiNode<Item> list_tail = list.getCurrent();
		list_tail.next = head;

		// 更新size信息
		size += list.getSize();

		return this;
	}

	/**
	 * 前向搜索
	 * 
	 * @param item
	 * @return
	 */
	public BiNode<Item> findForward(Item item) {
		// 如果传入的参数为空,或者链表为空，直接返回null
		if (null == item || 0 == size) {
			return null;
		}

		BiNode<Item> first = head.next;
		while (head != first) {
			if (0 == item.compareTo(first.item)) {
				return first;
			}
			first = first.next;
		}

		// 没有找到
		return null;
	}

	/**
	 * 反向搜索
	 * 
	 * @param item
	 * @return
	 */
	public BiNode<Item> findBackward(Item item) {
		// 如果传入的参数为空,或者链表为空，直接返回null
		if (null == item || 0 == size) {
			return null;
		}

		BiNode<Item> last = head.prior;
		while (head != last) {
			if (0 == item.compareTo(last.item)) {
				return last;
			}
			last = last.prior;
		}

		// 没有找到
		return null;
	}

	/**
	 * 根据item来删除节点，默认使用前向搜索
	 * 
	 * @param item
	 * @return
	 */
	public BiNode<Item> deleteNodeByItem(Item item) {
		return deleteNodeByItem(item, true);
	}

	/**
	 * 根据item来删除节点
	 * 
	 * @param item
	 * @return
	 */
	public BiNode<Item> deleteNodeByItem(Item item, boolean isForward) {

		// 如果item为空或者链表大小为0，直接返回
		if (null == item || 0 == size) {
			return null;
		}

		BiNode<Item> delete;
		if (isForward) {
			delete = findForward(item);
		} else {
			delete = findBackward(item);
		}

		// 如果没有找到直接返回
		if (null == delete) {
			return null;
		}

		// 调整节点引用
		delete.prior.next = delete.next;
		delete.next.prior = delete.prior;

		return delete;

	}

	/**
	 * 将传入的BST中的所有Keys按照顺序存储到一个双向链表中
	 * 
	 * @param <Value>
	 * @param <Key>
	 * @param bst
	 * @return
	 */
	public static <Value extends Comparable<Value>, Key extends Comparable<Key>> BiLinkedList<Key> fromBstToKeyBiLinkedList(
			BST<Key, Value> bst) {

		// 判断该树不能是空的
		if (null == bst || null == bst.getRoot()) {
			return null;
		}

		BiLinkedList<Key> list = new BiLinkedList<Key>();

		return fromBstToKeyBiLinkedListInternal(bst.getRoot(), list);
	}

	/**
	 * 对方法fromBstToKeyBiLinkedList的支持方法
	 * 
	 * @param node
	 * @param list
	 * @return
	 */
	private static <Value extends Comparable<Value>, Key extends Comparable<Key>> BiLinkedList<Key> fromBstToKeyBiLinkedListInternal(
			Node<Key, Value> node, BiLinkedList<Key> list) {

		if (null == node) {
			return null;
		}

		// 传入node非空，根据这个节点建立一个双向链表
		list.addListAfterHead(fromBstToKeyBiLinkedListInternal(node.left, list));
		list.addToTail(node.key);
		list.addListToTail(fromBstToKeyBiLinkedListInternal(node.right, list));

		return list;
	}

	/**
	 * 将传入的BST中的所有Values按照Keys的顺序存储到一个双向链表中
	 * 
	 * @param <Value>
	 * @param <Key>
	 * @param bst
	 * @return
	 */
	public static <Value extends Comparable<Value>, Key extends Comparable<Key>> BiLinkedList<Value> fromBstToValueBiLinkedList(
			BST<Key, Value> bst) {

		// 判断该树不能是空的
		if (null == bst || null == bst.getRoot()) {
			return null;
		}

		BiLinkedList<Value> list = new BiLinkedList<Value>();

		return fromBstToValueBiLinkedListInternal(bst.getRoot(), list);
	}

	/**
	 * 对方法fromBstToValueBiLinkedList的支持方法
	 * 
	 * @param node
	 * @param list
	 * @return
	 */
	private static <Value extends Comparable<Value>, Key extends Comparable<Key>> BiLinkedList<Value> fromBstToValueBiLinkedListInternal(
			Node<Key, Value> node, BiLinkedList<Value> list) {

		// 如果node为空，直接返回
		if (null == node) {
			return null;
		}

		list.addListAfterHead(fromBstToValueBiLinkedListInternal(node.left,
				list));
		list.addToTail(node.value);
		list.addListToTail(fromBstToValueBiLinkedListInternal(node.right, list));

		return list;
	}

	public static class BiNode<Item extends Comparable<Item>> {
		public Item item;
		public BiNode<Item> prior;
		public BiNode<Item> next;

		public BiNode() {

		}

		public BiNode(Item item, BiNode<Item> prior, BiNode<Item> next) {
			this.item = item;
			this.prior = prior;
			this.next = next;
		}
	}

	public BiNode<Item> getHead() {
		return head;
	}

	public BiNode<Item> getCurrent() {
		return current;
	}

	public int getSize() {
		return size;
	}
}

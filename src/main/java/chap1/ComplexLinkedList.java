package chap1;

/**
 * 复杂链表问题，链表中的节点的组成：值，下一节点，链表中的任意节点
 * 
 * @author Destiny
 *
 */
//TODO 因为不好测试，暂时不写
public class ComplexLinkedList<V> {

	private CNode<V> head;
	private CNode<V> current;
	
	public ComplexLinkedList() {
		// 让头结点的两个指针域全部指向自己
		this.head = new CNode<V>(null, null, null);
		head.next = head;
		head.random = head;
		
		current = head;
	}
	
	/**
	 * 向复杂链表中添加复杂节点，添加到尾部
	 * 
	 * @param cNode
	 */
	public void insertCNodeToTail(CNode<V> cNode) {
		if(null == cNode) {
			return;
		}
		
	}
	
	private class CNode<V> {
		private V value;
		private CNode<V> next;
		private CNode<V> random;
		
		CNode(V value, CNode<V> next, CNode<V> random) {
			this.value = value;
			this.next = next;
			this.random = random;
		}
	}
}

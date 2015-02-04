package chap3;

/**
 * 就是普通的二叉树，非搜索二叉树(BST)
 * @author Destiny
 *
 * @param <Key>
 * @param <Value>
 */
public class BT<Key, Value> {

	private Node<Key, Value> root;
	
	private int sz;
	
	public BT(Node<Key, Value> root) {
		this.root = root;
		sz = root.N;
	}
	
	/**
	 * 
	 * @param root
	 * @param left
	 * @param right
	 */
	public BT(Node<Key, Value> root, Node<Key, Value> left, Node<Key, Value> right) {
		
		
		
	}
	
	public void setRoot(Node<Key, Value> root) {
		this.root = root;
	}
	
	public Node<Key, Value> getRoot() {
		return root;
	}
	
	/**
	 * 检查BT是否为空，如果root为空，则该BT为空
	 * @return
	 */
	public boolean isEmpty() {
		return (null == root);
	}
	
	/**
	 * 将bt作为当前bt的左子树, 并返回之前的左子树
	 * @param bt
	 * @return
	 */
	public BT<Key, Value> replaceLeft(BT<Key, Value> bt) {
		
		BT<Key, Value> originLeft = new BT<Key, Value>(root.left);
		sz -= originLeft.sz;
		
		root.left = bt.getRoot();
		sz += bt.sz;
		
		return originLeft;
	}
	
	/**
	 * 将bt作为当前bt的右子树, 并返回之前的右子树
	 * @param bt
	 * @return
	 */
	public BT<Key, Value> replaceRight(BT<Key, Value> bt) {
		
		BT<Key, Value> originRight = new BT<Key, Value>(root.right);
		sz -= originRight.sz;
		
		root.right = bt.getRoot();
		sz += bt.sz;
		
		return originRight;
	}
	
	/**
	 * 将右子树替换成一个单节点
	 * @param bt
	 * @return
	 */
	public BT<Key, Value> replaceRight(Node<Key, Value> node) {
		
		// 需要检查该节点的左右子树均为空
		if(null != node.left || null != node.right) {
			return null;
		}
		
		BT<Key, Value> originRight = new BT<Key, Value>(root.right);
		sz -= originRight.sz;
		
		root.right = node;
		sz += 1;
		
		return originRight;
	}
	
	/**
	 * 将左子树替换成一个单节点
	 * @param bt
	 * @return
	 */
	public BT<Key, Value> replaceLeft(Node<Key, Value> node) {
		
		// 需要检查该节点的左右子树均为空
		if(null != node.left || null != node.right) {
			return null;
		}
		
		BT<Key, Value> originLeft = new BT<Key, Value>(root.left);
		sz -= originLeft.sz;
		
		root.left = node;
		sz += 1;
		
		return originLeft;
	}
	
}

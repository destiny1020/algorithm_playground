package misc;

import org.junit.Test;

/**
 * 解决 BT子结构的问题，提供minimum set of API，和BT类不一样，更多的API可以参考BT类
 * 
 * @author Destiny
 * 
 */
//TODO 还没有测试
public class BTSubTree<K extends Comparable<K>, V> {

	private Node<K, V> root;
	
	public BTSubTree(Node<K, V> root) {
		this.root = root;
	}

	public static <K extends Comparable<K>, V> boolean isSubTreeOf(BTSubTree<K, V> parent,
			BTSubTree<K, V> sub) {

		if (null == parent && null == sub) {
			return true;
		}

		if (null == parent || null == sub) {
			return false;
		}

		return isSubTreeOfCore(parent.root, sub.root);
	}

	private static <K extends Comparable<K>, V> boolean isSubTreeOfCore(
			Node<K, V> parent, Node<K, V> sub) {

		if (null == sub) {
			return true;
		}

		if (null == parent) {
			return false;
		}

		boolean result = false;
		if (parent.key.compareTo(sub.key) == 0) {
			result = isSubTreeOfCompare(parent.left, sub.left)
					&& isSubTreeOfCompare(parent.right, sub.right);
		}

		if (!result && null != parent.left) {
			result = isSubTreeOfCore(parent.left, sub);
		}

		if (!result && null != parent.right) {
			result = isSubTreeOfCore(parent.right, sub);
		}

		return result;
	}

	private static <K extends Comparable<K>, V> boolean isSubTreeOfCompare(
			Node<K, V> parent, Node<K, V> sub) {

		if (null == sub) {
			return true;
		}

		if (null == parent) {
			return false;
		}

		if (parent.key.compareTo(sub.key) == 0) {
			return isSubTreeOfCompare(parent.left, sub.left)
					&& isSubTreeOfCompare(parent.right, sub.right);
		}

		return false;
	}

	private static class Node<K extends Comparable<K>, V> {
		private K key;
		private V value;
		private Node<K, V> left;
		private Node<K, V> right;
		
		// 构造方法
		private Node(K key, Node<K, V> left, Node<K, V> right) {
			this.key = key;
			
			this.left = left;
			this.right = right;
		}
		
		@Override
		public String toString() {
			return "Key: " + key + " - Left: " + left.key + " - Right: " + right.key;
		}
	}
	
	public static void main(String[] args) {
		// 构造这个树
//		      1                                                   8
//         /    \                                               /    \
//         8    7                                               9    2
//      /    \
//      9    2
//          /  \
//          4  7
		
		Node<Integer, Integer> tree1_node4 = new Node<Integer, Integer>(4, null, null);
		Node<Integer, Integer> tree1_node7 = new Node<Integer, Integer>(7, null, null);
		Node<Integer, Integer> tree1_node2 = new Node<Integer, Integer>(2, tree1_node4, tree1_node7);
		Node<Integer, Integer> tree1_node9 = new Node<Integer, Integer>(9, null, null);
		Node<Integer, Integer> tree1_node8 = new Node<Integer, Integer>(8, tree1_node9, tree1_node2);
		Node<Integer, Integer> tree1_node7_2 = new Node<Integer, Integer>(7, null, null);
		Node<Integer, Integer> tree1_node1 = new Node<Integer, Integer>(1, tree1_node8, tree1_node7_2);
		
		BTSubTree<Integer, Integer> tree1 = new BTSubTree<Integer, Integer>(tree1_node1);
		
		// ----------------------------------------------
		
		Node<Integer, Integer> tree2_node2 = new Node<Integer, Integer>(2, null, null);
		Node<Integer, Integer> tree2_node9 = new Node<Integer, Integer>(9, null, null);
		Node<Integer, Integer> tree2_node8 = new Node<Integer, Integer>(8, tree2_node9, tree2_node2);
		
		BTSubTree<Integer, Integer> tree2 = new BTSubTree<Integer, Integer>(tree2_node8);
		
		System.out.println(BTSubTree.isSubTreeOf(tree1, tree2));
	}
}

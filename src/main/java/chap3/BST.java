package chap3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

// BST Implementation
public class BST<Key extends Comparable<Key>, Value>
{
	private Node<Key, Value> root;
	
	public void setRoot(Node<Key, Value> node)
	{
		this.root = node;
	}
	
	public Node<Key, Value> getRoot()
	{
		return this.root;
	}
	
	// Height with deepest paths
	// Recursive version
	// 获取最深路径
	public List<Node<Key, Value>> heightWithPaths()
	{
		List<Node<Key, Value>> pathStack = new ArrayList<Node<Key,Value>>();
		pathStack.add(this.root);
		pathStack = heightWithPath(pathStack, this.root);
		return pathStack;
	}
	
	private List<Node<Key, Value>> heightWithPath(
			List<Node<Key, Value>> pathStack, Node<Key, Value> node)
	{
		if(null == node)
		{
			return pathStack;
		}
		
		// 分别求出左子树和右子树的高度，总是找高度大的那一条分支探索
		// 感觉这里的性能有问题，因为height本身又是一个递归方法
		int heightLeft = height(node.left);
		int heightRight = height(node.right);
		
		if(heightLeft >= heightRight && heightLeft != -1)
		{
			pathStack.add(node.left);
			return heightWithPath(pathStack, node.left);
		}
		else
		{
			if(heightRight != -1)
				pathStack.add(node.right);
			return heightWithPath(pathStack, node.right);
		}
	}

	// Height method, to get the height of BST, based on zero
	// Recursive version
	public int height()
	{
		return height(this.root);
	}
	
	private int height(Node<Key, Value> node)
	{
		if(null == node)
		{
			return -1;
		}
		
		return 1 + Math.max(height(node.left), height(node.right));
	}
	
	// Size method, to get the number of nodes in BST
	public int size()
	{
		return this.size(root);
	}
	
	// Size method, to get the number of nodes rooted at node
	public int size(Node<Key, Value> node)
	{
		if(null == node)
		{
			return 0;
		}
		else
		{
			return node.N;
		}
	}
	
	// Get the value of the key
	public Node<Key, Value> get(Key key)
	{
		return this.get(key, root);
	}
	
	private Node<Key, Value> get(Key key, Node<Key, Value> node)
	{
		if(null == node)
		{
			return null;
		}
		
		int compare = key.compareTo(node.key);
		if(compare < 0)
		{
			return this.get(key, node.left);
		}
		else if(compare > 0)
		{
			return this.get(key, node.right);
		}
		else
		{
			return node;
		}
	}
	
	// Put method, replace the older value or add a brand new node
	public void put(Key key, Value value)
	{
		this.root = this.put(key, value, root, 1); 
	}

	private Node<Key, Value> put(Key key, Value value, Node<Key, Value> node, int height)
	{
		if(null == node)
		{
			return new Node<Key, Value>(key, value, 1, height);
		}
		
		int compare = key.compareTo(node.key);
		if(compare < 0)
		{
			node.left = this.put(key, value, node.left, node.height + 1);
		}
		else if(compare > 0)
		{
			node.right = this.put(key, value, node.right, node.height + 1);
		}
		else
		{
			// Update the value in current node
			node.value = value;
		}
		
		// Update the count before this recursive process exit
		node.N = size(node.left) + size(node.right) + 1;
		
		return node;
	}
	
	// Get the node which has a minimum key in BST
	public Node<Key, Value> min()
	{
		return this.min(this.root);
	}
	
	// Get the minimum key in BST
	public Key minKey()
	{
		return this.min(this.root).key;
	}
	
	// Get the value in the minimum key in BST
	public Value minVal()
	{
		return this.min(this.root).value;
	}

	// Get the value in the minimum key in tree rooted at node
	public Node<Key, Value> min(Node<Key, Value> node)
	{
		if(null != node.left)
		{
			return min(node.left);
		}
		
		return node; 
	}
	
	// Get the node which has a maximum key in BST
	public Node<Key, Value> max()
	{
		return this.max(this.root);
	}
	
	public Key maxKey()
	{
		return this.max(this.root).key;
	}
	
	public Value maxVal()
	{
		return this.max(this.root).value;
	}
	
	public Node<Key, Value> max(Node<Key, Value> node)
	{
		if(null != node.right)
		{
			return max(node.right);
		}
		
		return node;
	}
	
	// Get the node which has the max key smaller than or equal to the parameter
	public Node<Key, Value> floor(Key key)
	{
		return this.floor(key, this.root);
	}

	private Node<Key, Value> floor(Key key, Node<Key, Value> node)
	{
		if(null == node)
		{
			return null;
		}
		
		// Compare the target with the current
		int compare = key.compareTo(node.key);
		
		// If the target equals with the current
		if(compare == 0)
		{
			return node;
		}
		
		// If the target less than the current, search in left subtree recursively
		if(compare < 0)
		{
			return floor(key, node.left);
		}
		// If the target more than the current, search in right subtree recursively
		else
		{
			Node<Key, Value> ret = floor(key, node.right);
			if(null == ret)
			{
				return node;
			}
			else
			{
				return ret;
			}
		}
	}	
	
	// Get the node which has the min key larger than or equal to the parameter
	public Node<Key, Value> ceil(Key key)
	{
		return this.ceil(key, this.root);
	}

	private Node<Key, Value> ceil(Key key, Node<Key, Value> node)
	{
		if(null == node)
		{
			return null;
		}
		
		int compare = key.compareTo(node.key);
		
		if(compare == 0)
		{
			return node;
		}
		
		if(compare > 0)
		{
			return ceil(key, node.right);
		}
		else
		{
			Node<Key, Value> ret = ceil(key, node.left);
			if(null == ret)
			{
				return node;
			}
			else
			{
				return ret;
			}
		}
	}
	
	// Select operation, give an index, the index is based on zero
	public Node<Key, Value> select(int index)
	{
		if(index < 0 || index > this.root.N)
		{
			throw new IllegalArgumentException("Index is out of range");
		}
		
		return select(index, this.root);
	}

	private Node<Key, Value> select(int index, Node<Key, Value> node)
	{
		if(null == node)
		{
			return null;
		}
		
		int size_left = this.size(node.left);
		
		if(index == size_left)
		{
			return node;
		}
		else if(index < size_left)
		{
			return select(index, node.left);
		}
		else
		{
			return select(index - size_left - 1, node.right);
		}
	}
	
	// Give the rank of key in BST, the rank is based on zero as well
	public int rank(Key key)
	{
		return this.rank(key, this.root);
	}

	private int rank(Key key, Node<Key, Value> node)
	{
		if(null == node)
		{
			// No key found in BST
			return -1;
		}
		
		int compare = key.compareTo(node.key);
		
		if(0 == compare)
		{
			return size(node.left);
		}
		
		if(compare < 0)
		{
			return rank(key, node.left);
		}
		else
		{
			return 1 + size(node.left) + rank(key, node.right);
		}
	}
	
	// Delete the minimum node in BST
	public void deleteMin()
	{
		this.root = this.deleteMin(this.root);
	}

	// Delete the minimum node in subtree rooted at node
	public Node<Key, Value> deleteMin(Node<Key, Value> node)
	{
		if(null == node.left)
		{
			// Maybe null if right subtree is null
			// Decrease the height only in this case
			this.decreaseHeight(node.right);
			
			return node.right;
		}
		
		node.left = deleteMin(node.left);
		
		// Decrease the height
//		this.decreaseHeight(node.left);
		
		// Recaculate the size of node
		node.N = 1 + this.size(node.left) + this.size(node.right);
		
		return node;
	}
	
	// Delete the maximum node in BST
	public void deleteMax()
	{
		this.root = this.deleteMax(this.root);
	}
	
	// Delete the maximum node in subtree rooted at node
	public Node<Key, Value> deleteMax(Node<Key, Value> node)
	{
		if(null == node.right)
		{
			return node.left;
		}
		
		node.right = deleteMax(node.right);
		
		// Recalculate the size
		node.N = 1 + this.size(node.left) + this.size(node.right);
		
		return node;
	}

	// Delete a node specified by its key 
	public void delete(Key key)
	{
		this.root = this.delete(key, this.root);
	}

	private Node<Key, Value> delete(Key key, Node<Key, Value> node)
	{
		if(null == node)
		{
			return null;
		}
		
		int compare = key.compareTo(node.key);
		
		if(compare < 0)
		{
			node.left = this.delete(key, node.left);
		}
		else if(compare > 0)
		{
			node.right = this.delete(key, node.right);
		}
		else
		{
			if(null == node.right)
			{
				// Possible null
				return node.left;
			}
			if(null == node.left)
			{
				// Impossible null
				return node.right;
			}
			Node<Key, Value> node_t = node;
			node = this.min(node_t.right);
			node.right = this.deleteMin(node_t.right);
			node.left = node_t.left;
			
			// Update the height
			node.height = node_t.height;
		}
		
		// Recalculate the size of BST
		node.N = 1 + this.size(node.left) + this.size(node.right);
		return node;
	}
	
	// Util method for height
	private void decreaseHeight(Node<Key, Value> node)
	{
		if(node == null)
		{
			return;
		}
		if(node.left != null)
		{
			decreaseHeight(node.left);
		}
		node.height--;
		if(node.right != null)
		{
			decreaseHeight(node.right);
		}
	}
	
	// Range Search in BST
	public Iterable<Node<Key, Value>> keys()
	{
		return (Iterable<Node<Key, Value>>)this.keys(this.minKey(), this.maxKey());
	}
	
	public Iterable<Node<Key, Value>> keys(Key minKey, Key maxKey)
	{
		Queue<Node<Key, Value>> queue = new ArrayDeque<Node<Key, Value>>();
		keys(this.root, queue, minKey, maxKey);
		
		return queue;
	}

	private void keys(Node<Key, Value> node, Queue<Node<Key, Value>> queue, Key minKey,
			Key maxKey)
	{
		if(node == null)
		{
			return;
		}
		
		int lo_compare = minKey.compareTo(node.key);
		int hi_compare = maxKey.compareTo(node.key);
		
		if(lo_compare < 0)
		{
			this.keys(node.left, queue, minKey, maxKey);
		}
		if(lo_compare <= 0 && hi_compare >= 0)
		{
			queue.add(node);
		}
		if(hi_compare > 0)
		{
			this.keys(node.right, queue, minKey, maxKey);
		}
	}

	// Print the BST in order
	public void printBST(Node<Key, Value> node)
	{
		if(node == null)
		{
			return;
		}
		
//		if(node.left != null)
//		{
//			System.out.println("Entering left ...");
//		}
		this.printBST(node.left);
		
//		System.out.println("Entering itself ...");
		System.out.println("Key:\t" + node.key + "\tValue:\t" + node.value + "\tSize:\t" + node.N + "\tHeight:\t" + node.height);
		
//		if(node.right != null)
//		{
//			System.out.println("Entering right ...");
//		}
		this.printBST(node.right);
	}
	
	//--------------------------------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------------------------
	
	/**
	 * 判断一棵树是否是平衡的，不需要重复遍历子树的解法
	 * @return
	 */
	public boolean isBalanced() {
		
		// 正确的调用方式
		return isBalanced(this.root, new IntegerWrapper(0));
		
		// 错误的调用方式
//		return isBalancedFalse(this.root, 0);
		
	}

	/**
	 * 正确的实现模式
	 * @param node
	 * @param depth
	 * @return
	 */
	private boolean isBalanced(Node<Key, Value> node, IntegerWrapper depth) {
		
		// 如果node是空的，那么表示depth为0，同时返回true
		if(null == node) {
			depth.value = 0;
			return true;
		}
		
		// node不为空的情况，分别检查它的两个子树
		IntegerWrapper depth_left = new IntegerWrapper(0);
		IntegerWrapper depth_right = new IntegerWrapper(0);
		if(isBalanced(node.left, depth_left) && isBalanced(node.right, depth_right)) {
			// 在两个子树都平衡的情况下
			int diff = depth_left.value - depth_right.value;
			if(diff <= 1 && diff >= -1) {
				depth.value = 1 + ((depth_left.value > depth_right.value) ? depth_left.value : depth_right.value);
				return true;
			}
		}
		
		return false;
	}
	
	private boolean isBalancedFalse(Node<Key, Value> node, Integer depth) {
		if(null == node) {
			depth = 0;
			return true;
		}
		
		Integer left = 0;
		Integer right = 0;
		if(isBalancedFalse(node.left, left) && isBalancedFalse(node.right, right)) {
			int diff = left - right;
			if(diff >= -1 && diff <= 1) {
				depth = 1 + (left > right ? left : right);
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * 本来是使用Integer来模拟C中整型指针，但是因为Java的Integer中存在着Cache的机制
	 * @author Destiny
	 *
	 */
	public static class IntegerWrapper {
		public int value;
		public IntegerWrapper(int value) {
			this.value = value;
		}
		
	}
}

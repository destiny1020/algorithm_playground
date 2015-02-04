package chap3;

public class Node<Key, Value>
{
	public Key key;
	public Value value;
	public Node<Key, Value> left, right;
	public int N;
	public int height;
	
	public Node(Key key, Value value, int N, int height)
	{
		this.key = key;
		this.value = value;
		this.N = N;
		this.height = height;
	}
	
	@Override
	public String toString()
	{
		return "Key:\t" + this.key + "\tValue:\t" + this.value
				+ "\tCount:\t" + this.N + "\tHeight:\t" + this.height;
	}
}

package chap2.heapsort;

public class HeapNode<Key extends Comparable<Key>, Value> implements
		Comparable<Key>
{
	private Key key;
	private Value value;

	public HeapNode(Key key, Value value)
	{
		this.key = key;
		this.value = value;
	}

	@Override
	public int compareTo(Key o)
	{
		return this.key.compareTo(o);
	}

	public Key getKey()
	{
		return key;
	}

	public void setKey(Key key)
	{
		this.key = key;
	}

	public Value getValue()
	{
		return value;
	}

	public void setValue(Value value)
	{
		this.value = value;
	}

	@Override
	public String toString()
	{
		return "Key:\t" + this.key.toString() + " - Value:\t" + this.value.toString();
	}
}

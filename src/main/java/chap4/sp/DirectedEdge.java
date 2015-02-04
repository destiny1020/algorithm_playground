package chap4.sp;

public class DirectedEdge
{
	private final int v;
	private final int w;
	private final double weight;
	
	public DirectedEdge(int v, int w, double weight)
	{
		this.v = v;
		this.w = w;
		this.weight = weight;
	}

	public int from()
	{
		return v;
	}

	public int to()
	{
		return w;
	}

	public double getWeight()
	{
		return weight;
	}
	
	@Override
	public String toString()
	{
		return String.format("%d->%d (%.2f) ", v, w, weight);
	}
}

package chap2.heapsort;

public class CubeSum implements Comparable<CubeSum>
{
	public final long sum;
	public final int i;
	public final int j;
	
	public CubeSum(int i, int j)
	{
		this.i = i;
		this.j = j;
		this.sum = (long)i * i * i + (long)j * j * j;
	}
	
	@Override
	public int compareTo(CubeSum that)
	{
		if(this.sum < that.sum)
		{
			return -1;
		}
		if(this.sum > that.sum)
		{
			return 1;
		}
		
		return 0;
	}
	
	@Override
	public String toString()
	{
		return this.sum + " = " + i + "^3 " + j + "^3";
	}
}

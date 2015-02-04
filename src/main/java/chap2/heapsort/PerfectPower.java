package chap2.heapsort;

public class PerfectPower implements Comparable<PerfectPower>
{
	public final long power;
	public final int a;
	public final int b;
	
	public PerfectPower(int a, int b)
	{
		this.power = power(a, b);
		this.a = a;
		this.b = b;
	}
	
	public static long power(int a, int b)
	{
		long result = 1;
		for(int i = 0; i < b; i++)
		{
			result *= a;
		}
		
		if(result < Long.MAX_VALUE)
		{
			return result;
		}
		else
		{
			throw new RuntimeException("Exceed the Maximum value of Long");
		}
	}

	@Override
	public int compareTo(PerfectPower that)
	{
		if(this.power < that.power)
		{
			return -1;
		}
		if(this.power > that.power)
		{
			return 1;
		}
		return 0;
	}
	
	@Override
	public String toString()
	{
		return this.power + " = " + this.a + " ^ " + this.b;
	}

}

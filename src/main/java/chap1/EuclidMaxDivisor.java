package chap1;

public class EuclidMaxDivisor
{
	// Should make sure that first is greater than or equal to second
	public static int gcd(int first, int second)
	{
		System.out.println("Current Values: " + first + "\t" + second);
		
		if(0 == second)
		{
			return first;
		}
		int remainder = first % second;
		return gcd(second, remainder);
	}
}

package gadget;

public class DoubleTools
{
	public static double H(int N)
	{
		double sum = 0.0;
		for (int i = 1; i <= N; i++)
			sum += 1.0 / i;
		return sum;
	}
}

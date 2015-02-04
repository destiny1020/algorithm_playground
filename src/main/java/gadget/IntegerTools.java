package gadget;

public class IntegerTools
{
	public static boolean isPrime(int n)
	{
		if(n < 2)
			return false;
		
		for(int i = 2; i * i <= n; i++)
		{
			if(0 == n % i)
				return false;
		}
		 
		return true;
	}
}

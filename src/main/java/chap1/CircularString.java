package chap1;

public class CircularString
{
	public static boolean compare(String s1, String s2)
	{
		if (s1.length() != s2.length())
		{
			return false;
		}

		int length = s1.length();
		int count = 0;

		for (int i = 0, j = 0, k = 0; i < length; i++)
		{
			// For every comparison, s1 should start at index 0, and s2 should
			// start at index i
			k = i;
			j = 0;

			while (s1.charAt(j) == s2.charAt(k % length))
			{
				count++;
				if (count == length)
				{
					return true;
				}
				j++;
				k++;
			}
			count = 0;
//			j = 0;
		}

		return false;
	}

	public static String mystery(String s)
	{
		int N = s.length();
		if (N <= 1)
			return s;
		String a = s.substring(0, N / 2);
		String b = s.substring(N / 2, N);
		return mystery(b) + mystery(a);
	}
}

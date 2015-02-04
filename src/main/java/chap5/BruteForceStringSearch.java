package chap5;

public class BruteForceStringSearch
{
	public static int search(String pattern, String txt)
	{
		int N = txt.length();
		int M = pattern.length();

		for (int i = 0, j = 0; i < N - M; i++, j = 0)
		{
			for (; j < M; j++)
			{
				if (txt.charAt(i + j) != pattern.charAt(j))
				{
					break;
				}
			}
			if (j == M)
			{
				return i;
			}
		}

		return -1;
	}

	public static int search2(String pattern, String txt)
	{
		int N = txt.length();
		int M = pattern.length();
		int i, j;

		for (i = 0, j = 0; i < N && j < M; i++)
		{
			if (txt.charAt(i) == pattern.charAt(j))
			{
				if (++j == M)
					return i - M + 1;
			} else
			{
				i -= j;
				j = 0;
			}
		}
		return -1; // not found
	}

	public static int search3(String pat, String txt)
	{
		int j, M = pat.length();
		int i, N = txt.length();
		for (i = 0, j = 0; i < N && j < M; i++)
		{
			if (txt.charAt(i) == pat.charAt(j))
				j++;
			else
			{
				i -= j;
				j = 0;
			}
		}
		if (j == M)
			return i - M; // found
		else
			return N; // not found
	}

	public static void main(String[] args)
	{
		int result = BruteForceStringSearch.search3("ABRA", "ABACADABRAC");
		System.out.println(result);
	}
}

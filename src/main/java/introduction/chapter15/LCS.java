package introduction.chapter15;

/**
 * Longest Common Subsequence
 * 
 * @author Destiny
 * 
 */
public class LCS
{
	private String str1;
	private String str2;

	private int[][] c;

	public LCS(String str1, String str2)
	{
		this.str1 = str1;
		this.str2 = str2;

		this.c = new int[str1.length() + 1][str2.length() + 1];
	}
	
	public int process()
	{
		for(int i = 0; i < this.str1.length(); i++)
		{
			for(int j = 0; j < this.str2.length(); j++)
			{
				if(str1.charAt(i) == str2.charAt(j))
				{
					this.c[i + 1][j + 1] = this.c[i][j] + 1;
				}
				else if(this.c[i][j + 1] >= this.c[i + 1][j])
				{
					this.c[i + 1][j + 1] = this.c[i][j + 1];
				}
				else
				{
					this.c[i + 1][j + 1] = this.c[i + 1][j];
				}
			}
		}
		
		return this.c[str1.length()][str2.length()]; 
	}
	
	/**
	 * i should be the length of str1, j should be the length of str2
	 * @param i
	 * @param j
	 */
	public void printSequence(int i, int j)
	{
		if(i == 0 || j == 0)
			return;
		
		if(str1.charAt(i - 1) == str2.charAt(j - 1))
		{
			printSequence(i - 1, j - 1);
			System.out.println(this.str1.charAt(i - 1));
		}
		else if(c[i - 1][j] >= c[i][j - 1])
		{
			printSequence(i - 1, j);
		}
		else
		{
			printSequence(i, j - 1);
		}
	}

	public String getStr1()
	{
		return str1;
	}

	public String getStr2()
	{
		return str2;
	}

	public int[][] getC()
	{
		return c;
	}

	public static void main(String[] args)
	{
		String str1 = "ABCBDAB";
		String str2 = "BDCABA";
		
		LCS  lcs = new LCS(str1, str2);
		
		System.out.println(lcs.process());
		
		System.out.println("-----------------------------");
		
		lcs.printSequence(str1.length(), str2.length());
	}
}

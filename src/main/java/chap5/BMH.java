package chap5;

import java.util.Arrays;

/**
 * Boyer-Moore-Horspool Substring search algorithm
 * @author Destiny
 *
 */
public class BMH
{
	private String pattern;
	private int[] right;
	public static int R = 256;
	
	public BMH(String pattern)
	{
		this.pattern = pattern;
		this.initRight();
	}

	private void initRight()
	{
		int M = this.pattern.length();
		this.right = new int[R];
		Arrays.fill(this.right, -1);
		for(int i = 0; i < M; i++)
		{
			this.right[this.pattern.charAt(i)] = i;
		}
	}
	
	public int process(String txt)
	{
		int M = this.pattern.length();
		int N = txt.length();
		int skip, skipHorspool;
		for(int i = 0; i < N - M; i += skip)
		{
			skip = 0;
			for(int j = M - 1; j >= 0; j--)
			{
				if(txt.charAt(i + j) != this.pattern.charAt(j))
				{
					// Judge skip by bad character
					skip = j - this.right[txt.charAt(i + j)];
					// Judge skip by Horspool
					String subPat = this.pattern.substring(0, this.pattern.length() - 1);
					int indexL = subPat.lastIndexOf(this.pattern.charAt(this.pattern.length() - 1));
					skipHorspool = M - indexL;
					skip = Math.max(skipHorspool, skip);
					if(skip < 1)
						skip = 1;
					// Means mismatch, exit inner loop
					break;
				}
			}
			if(skip == 0)
				return i;
		}
		return -1;
	}
	
	public static void main(String[] args)
	{
		BMH bmh = new BMH("NEEDLE");
		int result = bmh.process("FINDINAHAYSTACKNEEDLEINA");
		System.out.println(result);
	}
}

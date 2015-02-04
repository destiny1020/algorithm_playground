package chap5;

import java.util.Arrays;

/**
 * Boyer-Moore Substring search algorithm
 * @author Destiny
 *
 */
public class BM
{
	private String pattern;
//	private int[][] right;
	private int[] right;
	public static int R = 256;
	
	public BM(String pattern)
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
//		for(int i = 0; i < M; i++)
//		{
//			int j = i;
//			while(j < M)
//			{
//				this.right[this.pattern.charAt(i)][j++] = i;
//			}
//		}
	}
	
	public int process(String txt)
	{
		int M = this.pattern.length();
		int N = txt.length();
		int skip;
		for(int i = 0; i < N - M; i += skip)
		{
			skip = 0;
			for(int j = M - 1; j >= 0; j--)
			{
				if(txt.charAt(i + j) != this.pattern.charAt(j))
				{
					skip = j - this.right[txt.charAt(i + j)];
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
		BM bm = new BM("NEEDLE");
		int result = bm.process("FINDINAHAYSTACKNEEDLEINA");
		System.out.println(result);
	}
}

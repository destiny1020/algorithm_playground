package chap5;

public class KMP
{
	private String pattern;
	private int[][] dfa;
	public static int R = 256;
	
	public KMP(String pattern)
	{
		this.pattern = pattern;
		this.initDFA();
	}

	private void initDFA()
	{
		int M = this.pattern.length();
		this.dfa = new int[R][M];
		
		// Route leads to State 1
		this.dfa[this.pattern.charAt(0)][0] = 1;
		for(int X = 0, j = 1; j < M; j++)
		{
			// Set Mismatch
			for(int c = 0; c < R; c++)
			{
				this.dfa[c][j] = this.dfa[c][X];
			}
			// Set Match
			this.dfa[this.pattern.charAt(j)][j] = j + 1;
			// Upgrade the restart state
			X = this.dfa[this.pattern.charAt(j)][X];
		}
	}
	
	public int process(String txt)
	{
		int M = this.pattern.length();
		int N = txt.length();
		
		int i, j;
		
		for(i = 0, j = 0; j < M && i < N; i++)
		{
			j = this.dfa[txt.charAt(i)][j];
		}
		if(j == M)
			return i - M;
		else
			return -1;
	}
	
	public static void main(String[] args)
	{
		KMP kmp = new KMP("ABRA");
		int result = kmp.process("ABACADABRAC");
		System.out.println(result);
	}
}

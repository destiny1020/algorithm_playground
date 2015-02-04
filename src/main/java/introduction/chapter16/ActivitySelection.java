package introduction.chapter16;

/**
 * DP Solution here
 * @author Destiny
 *
 */
public class ActivitySelection
{
	private int N;
	
	private int[] starts;
	private int[] ends;
	
	private int[][] counts;
	private int[][] partition;
	
	public ActivitySelection(int[] starts, int[] ends)
	{
		if(starts.length == ends.length)
			this.N = starts.length;
		else
			throw new IllegalArgumentException("starts and ends have different lengths");
		
		this.starts = starts;
		this.ends = ends;
		
		this.counts = new int[N][N];
		this.partition = new int[N][N];
	}
	
	public int process()
	{
		for(int length = 2; length <= N - 1; length++)
		{
			for(int i = 0; i < N - length; i++)
			{
				int j = i + length;
				for(int k = i + 1; k < j; k++)
				{
					// If the collection is not empty
					if(starts[k] >= ends[i] && ends[k] <= starts[j])
					{
						if(counts[i][j] < counts[i][k] + counts[k][j] + 1)
						{
							counts[i][j] = counts[i][k] + counts[k][j] + 1;
							partition[i][j] = k;
						}
					}
				}
			}
		}
		
		return counts[0][N - 1];
	}

	public int[][] getCounts()
	{
		return counts;
	}

	public int[][] getPartition()
	{
		return partition;
	}
}

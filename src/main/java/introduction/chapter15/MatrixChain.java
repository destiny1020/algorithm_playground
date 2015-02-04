package introduction.chapter15;

public class MatrixChain
{
	private int[] dimensions;
	private int matrixChainLength;
	private int[][] multiCounts;
	private int[][] records;
	
	public MatrixChain(int[] dimensions)
	{
		this.dimensions = dimensions;
		this.matrixChainLength = dimensions.length - 1;
		
		// Initialize the multiCounts array and records array
		// Row 0 and Column 0 are not used
		this.multiCounts = new int[matrixChainLength + 1][matrixChainLength + 1];
		this.records = new int[matrixChainLength + 1][matrixChainLength + 1];
	}
	
	public void process()
	{
		for(int i = 1; i <= this.matrixChainLength; i++)
			this.multiCounts[i][i] = 0;	//Ordinary Solution
		
		for(int length = 2; length <= this.matrixChainLength; length++)
		{
			for(int i = 1; i <= this.matrixChainLength - length + 1; i++)
			{
				// Determine j based on length and i
				int j = i + length - 1;
				// Set Infinite value first
				this.multiCounts[i][j] = Integer.MAX_VALUE;
				// For each k between i and j
				for(int k = i; k < j; k++)
				{
					int count = this.multiCounts[i][k] + this.multiCounts[k + 1][j] + this.dimensions[i - 1] * this.dimensions[k] * this.dimensions[j];
					if(count < this.multiCounts[i][j])
					{
						this.multiCounts[i][j] = count;
						this.records[i][j] = k;
					}
				}
			}
		}
	}
	
	public void printChain(int i, int j)
	{
		if(i == j)
			System.out.print("A" + i);
		else
		{
			System.out.print("( ");
			printChain(i, this.records[i][j]);
			System.out.print(" ");
			printChain(this.records[i][j] + 1, j);
			System.out.print(" )");
		}
	}
	
	public static void main(String[] args)
	{
		int[] dimensions = new int[]{30, 35, 15, 5, 10, 20, 25};
		MatrixChain chain = new MatrixChain(dimensions);
		chain.process();
		chain.printChain(1, dimensions.length - 1);
	}
}

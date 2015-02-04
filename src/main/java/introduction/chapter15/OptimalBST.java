package introduction.chapter15;


/**
 * Optimal Binary Search Tree - DP 2012-03-04
 * 
 * @author Destiny
 * 
 */
public class OptimalBST
{
	private double[] pNode;
	private double[] pLeaf;
	private int numberOfNodes;

	private double[][] expected;
	private double[][] weight;
	private int[][] root;

	/**
	 * pNode and pLeaf should have same length, i.e. they should be aligned For
	 * instance: pNode = [0, X, X] pLeaf = [X, X, X]
	 * 
	 * @param pNode
	 * @param pLeaf
	 */
	public OptimalBST(double[] pNode, double[] pLeaf)
	{
		this.pLeaf = pLeaf;
		this.pNode = pNode;
		this.numberOfNodes = pNode.length;

		this.expected = new double[this.numberOfNodes + 1][this.numberOfNodes + 1];
		this.weight = new double[this.numberOfNodes + 1][this.numberOfNodes + 1];
		this.root = new int[this.numberOfNodes][this.numberOfNodes];
	}

	public void process()
	{
		// Initialize the probability for leaf
		for (int i = 1; i <= numberOfNodes; i++)
		{
			this.expected[i][i - 1] = this.pLeaf[i - 1];
			this.weight[i][i - 1] = this.pLeaf[i - 1];
		}

		for (int length = 1; length < this.numberOfNodes; length++)
		{
			for (int i = 1; i < this.numberOfNodes - length + 1; i++)
			{
				int j = i + length - 1;
				this.expected[i][j] = Double.MAX_VALUE;
				this.weight[i][j] = this.weight[i][j - 1] + this.pLeaf[j]
						+ this.pNode[j];
				for (int r = i; r <= j; r++)
				{
					double temp = this.expected[i][r - 1]
							+ this.expected[r + 1][j] + this.weight[i][j];
					if (Double.compare(temp, this.expected[i][j]) < 0)
					{
						this.expected[i][j] = temp;
						this.root[i][j] = r;
					}
				}
			}
		}

		System.out.println("Root: " + this.root[1][this.numberOfNodes - 1]);
		System.out.println("Expected: "
				+ this.expected[1][this.numberOfNodes - 1]);
		
		// Test
		for(int i = 0; i < this.root.length; i++)
		{
			for(int j = 0; j < this.root[1].length; j++)
			{
				System.out.print(this.root[i][j] + "\t");
			}
			System.out.println();
		}
	}

	public double[][] getExpected()
	{
		return expected;
	}

	public double[][] getWeight()
	{
		return weight;
	}

	public int[][] getRoot()
	{
		return root;
	}

	public static void main(String[] args)
	{
		double[] pLeaf = { 0.05, 0.10, 0.05, 0.05, 0.05, 0.10 };
		double[] pNode = { 0.00, 0.15, 0.10, 0.05, 0.10, 0.20 };

		OptimalBST obst = new OptimalBST(pNode, pLeaf);
		obst.process();

		// obst.printTree();
	}
}

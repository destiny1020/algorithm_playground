package com.algo.chap3;

/**
 * 0-1 背包问题
 * 递归表达式：
 * m(i, j) = max{m(i+1, j), m(i+1, j-w[i]) + v[i]} when j >= w[i]
 * m(i, j) = m(i+1, j) when j < w[i]
 * 创建时间: 2011-4-11
 * @author Destiny
 *
 */
public class KnapsackZeroOne
{
	private int[] weight;
	private int[] value;
	private int num;
	private int maxWeight;
	
	/**
	 * w为方便下标运算，将matrix设置成[num+1][maxWeight+1]的矩阵
	 */
	private int[][] matrix;
	
	/**
	 * 用于保存最后的取舍结果，1代表取，0代表不取
	 */
	private int[] result;
	
	/**
	 * 保存总价值
	 */
	private int totalValue;
	
	public KnapsackZeroOne(int num, int maxWeight, int[] weight, int[] value) throws Exception
	{
		if(num != weight.length || num != value.length)
			throw new Exception("物品的个数和weight数组的元素个数或和value数组的元素个数不一致！");
		
		//进行初始化的操作
		this.weight = weight;
		this.value = value;
		this.num = num;
		this.maxWeight = maxWeight;
		
		//初始化matrix以及result
		matrix = new int[num + 1][maxWeight + 1];
		result = new int[num + 1];
		
		//对matrix进行赋值操作，根据动态规划原理
		this.initMatrix();
	}
	
	private void initMatrix()
	{
		//从最后一个元素开始初始化，注意weight和value数组的元素下标
		
		//对最后一个元素进行处理，注意matrix[i][j]代表的是，在总重量j的情况下，从物品i到num能够得到的最大价值
		for(int i = weight[this.num - 1]; i <= maxWeight; i++)
			matrix[this.num][i] = this.value[num - 1];
		
		//对第2个元素到第n - 1个元素进行处理
		for(int i = this.num - 1; i > 1; i--)
		{
			//取总重量和最后一个物品的重量的最小值
			int bound = Math.min(weight[i - 1] - 1, this.maxWeight);
			for (int j = 0; j <= bound; j++)
				this.matrix[i][j] = this.matrix[i + 1][j];
			for(int j = weight[i - 1]; j <= this.maxWeight; j++)
				this.matrix[i][j] = Math.max(this.matrix[i + 1][j], this.matrix[i + 1][j - weight[i - 1]] + value[i - 1]);
		}
		
		//假设从1-num的情况和从2-num的情况相同
		matrix[1][maxWeight] = matrix[2][maxWeight];
		if(maxWeight >= this.weight[0])
			matrix[1][maxWeight] = Math.max(matrix[2][maxWeight], matrix[2][maxWeight - weight[0]] + value[0]);
	}
	
	public void constructResults()
	{
		int maxW = maxWeight;
		//对1至n-1个物品进行判断
		for(int i = 1; i < num; i++)
		{
			if(matrix[i][maxW] == matrix[i + 1][maxW])
				//如果i和i + 1的矩阵值相等，那么有没有它也就无所谓了
				result[i] = 0;
			else
			{
				result[i] = 1;
				this.totalValue += value[i - 1];
				maxW -= weight[i - 1];
			}
		}
		//对最后一个物品进行判断
		result[num] = (matrix[num][maxW] >= 0) ? 1 : 0;
		if(result[num] == 1)
			this.totalValue += value[num - 1];
	}

	public int[] getResult()
	{
		return result;
	}

	public int getValue()
	{
		return totalValue;
	}
}

package com.algo.chap2;

import com.algo.gadgets.NumberHandler;

/**
 * 实际上就是求Bell数的问题
 * 递推公式为，
 *	B(0) = 1,
 *	B(n+1) = Sum(0,n) C(n,k)B(k). n = 1,2,...
 * 其中，Sum(0,n)表示对k从0到n求和，C(n,k) = n!/[k!(n-k)!]
 * 使用备忘录方法对运算结果进行保存
 * 
 * 关于Stirling数：
 * S(n,n) = S(n,1) = 1,
 * S(n,k) = S(n-1,k-1) + kS(n-1,k)
 * 
 * Bell数和Stirling数之间的关系：
 * B(n) = Sum(1,n) S(n,k)
 * 
 * 创建时间: 2011-4-7
 * @author Destiny
 *
 */
public class SetDivision
{
	//保存待求Bell数的n值
	private int n;
	
	//保存结果值
	private long result;
	
	//保存阶乘的结果，避免重复计算
	private long[] factorialResults;
	
	//保存组合的结果，避免重复计算，此矩阵是一个下三角矩阵，这里的空间开销是个问题啊！
	private long[][] combinationResults;
	
	public SetDivision(int n)
	{
		this.n = n;
		this.factorialResults = new long[n];
		this.combinationResults = new long[n][n];
		
		//目前只初始化了factorialResult数组
		this.init();
	}
	
	private void init()
	{
		for(int i = 0; i < n; i++)
			this.factorialResults[i] = NumberHandler.factorial(i);
	}

	/**
	 * 对保存在n中的数进行运算，运算结果保存在result中
	 * 外部调用此方法进行计算，使用getResult得到结果
	 */
	public void caculateBellNumber()
	{
		this.result = this.execute(n);
	}
	
	/**
	 * 递归的进行求解
	 * @param n 当前值
	 * @return 返回当前值的bell数
	 */
	private long execute(int n)
	{
		if(n == 0)
			return 1;
		
		long sum = 0;
		long com;
		for(int i = 0; i < n; i++)
		{
			//说明此结果之前计算过
			if(this.combinationResults[n - 1][i] > 0)
				com = this.combinationResults[n - 1][i];
			else
				com = this.caculateCombination(n - 1, i);
			
			//未避免控制语句过多，可以先计算factorialResult
			sum += com * this.execute(i);
		}
		
		return sum;
	}
	
	private long caculateCombination(int n, int k)
	{
		if(n < k)
		{
			System.out.println("Wrong!");
			return -1;
		}
		
		long numerator = this.factorialResults[n];
		long denominator = this.factorialResults[k] * this.factorialResults[n - k];
		
		long result = numerator / denominator;
		this.combinationResults[n][k] = result;
		
		return result;
	}

	//改变n的值时，对结果数组也需要进行修改
	public void setN(int n)
	{
		//如果传入的n大于目前的n值，对结果数组进行扩容
		if(n > this.n)
		{
			this.factorialResults = new long[n];
			this.combinationResults = new long[n][n];
			
//			System.arraycopy(factorialResults, 0, newFactorialResults, 0, factorialResults.length);
//			System.arraycopy(combinationResults, 0, newCombinationResults, 0, combinationResults.length);
//			
//			this.factorialResults = newFactorialResults;
//			this.combinationResults = newCombinationResults;
			
			this.n = n;
		}
	}

	/**
	 * 通过m和n计算斯特灵数
	 * @param m 集合元素个数
	 * @param n 划分集合个数
	 * @return Stirling数
	 */
	public long caculateStirling(int m, int n)
	{
		if((m < n) || m <= 0 || n <= 0)
		{
			System.out.println("Wrong!");
			return -1;
		}
		
		if((m == n) || (n == 1))
			return 1;
		
		return this.caculateStirling(m - 1, n - 1) + n * this.caculateStirling(m - 1, n);
	}
	
	public long getResult()
	{
		return result;
	}

	public long[] getFactorialResults()
	{
		return factorialResults;
	}

	public long[][] getCombinationResults()
	{
		return combinationResults;
	}
}

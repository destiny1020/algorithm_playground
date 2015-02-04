package com.algo.chap2;

/**
 * 半数集问题的实现
 * 创建时间: 2011-4-7
 * @author Destiny
 *
 */
public class HalfSet
{
	private int n;
	private int[] result;
	
	public HalfSet(int n)
	{
		this.n = n;
		this.result = new int[n + 1];
	}
	
	public int halfSet(int n)
	{
		int ans = 1;
		if(result[this.n] > 0)
			return result[this.n];
		if(n > 1)
			for(int i = 1; i <= n / 2; i++)
				ans += this.halfSet(i);
		result[n] = ans;
		return ans;
	}
}

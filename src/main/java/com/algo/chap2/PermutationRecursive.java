package com.algo.chap2;

import com.algo.gadgets.NumberHandler;

/**
 * 通过递归的方法列出给定给定上限n的一个全排列
 * 创建时间: 2011-3-30
 * @author Destiny
 *
 */
public class PermutationRecursive
{
	/**
	 * 记录去全排列的上限数字
	 */
	private int length;
	
	/**
	 * 有上限数字决定待排列的数组，传递进来的应该是一个ascending的数组
	 */
	private int[] permutation;
	
	/**
	 * 排列计数器
	 */
	private int count;
	
	public PermutationRecursive(int length, int[] permutation)
	{
		this.length = length;
		this.permutation = permutation;
	}
	
	public void getAllPermutation()
	{
		this.getPermutation(0, length - 1);
	}
	
	/**
	 * 对指定的索引闭区间进行全排列
	 * @param start 开始区间
	 * @param end 结束区间
	 * @return 排列个数
	 */
	public void getPermutation(int start, int end)
	{
		//递归结束条件
		if(start == end)
		{
			this.printPerm();
		}
		else
		{
			for(int i = start; i <= end; i++)
			{
				NumberHandler.swap(permutation, start, i);
				this.getPermutation(start + 1, end);
				NumberHandler.swap(permutation, start, i);
			}
		}
	}
	
	/**
	 * 打印这个排列
	 */
	private void printPerm()
	{
		for (int temp : permutation)
		{
			System.out.print(temp + " ");
		}
		System.out.println();
		
		//计数器+1
		this.count++;
	}
	
	/**
	 * 将计数器清零
	 */
	public void clearCount()
	{
		this.count = 0;
	}
	
	/**
	 * 获得计数器的值
	 * @return 获得计数器的值
	 */
	public int getCount()
	{
		return this.count;
	}
}

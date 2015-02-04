package com.algo.chap2;

import java.util.ArrayList;

import com.algo.sorts.QuickSort;

/**
 * 众数问题，求一个无序数组中出现最多的那个元素以及它的出现次数
 * 创建时间: 2011-4-5
 * @author Destiny
 *
 */
public class Mode
{
	/**
	 * 存储满足要求的众数
	 */
	private ArrayList<Integer> mode = new ArrayList<Integer>();
	
	/**
	 * 存储已经进行过scan的元素，防止重复扫描
	 */
	private ArrayList<Integer> record = new ArrayList<Integer>();
	
	/**
	 * 记录众数的出现次数
	 */
	private int num = 1;
	
	//以下两个成员变量在使用基于递归的解法时需要用到
	private int scanStart;
	private int scanEnd;
	
	public Mode()
	{
		
	}
	
	public Mode(int scanStart, int scanEnd)
	{
		this.scanEnd = scanEnd;
		this.scanStart = scanStart;
	}
	
	/**
	 * 求众数的递归解法，在众数的出现次数超过整个arr长度的一半时，效率较高，否则效率底下
	 * 此时使用下面的普通排序-扫描算法更好
	 * 当start取0，end取arr.length - 1时即进行全组搜索
	 * 搜索结果保存在类的成员变量中
	 * @param arr	待操作的数组，无序
	 * @param start 搜索开始的索引
	 * @param end 搜索结束的索引
	 */
	public void getMode(int[] arr, int start, int end)
	{
		//递归终止条件
		if(start == end)
		{
			//当区间闭合时，进行最后一次搜索
			int num = this.searchForOccurrence(arr, arr[start]);
			this.handleNumOfOccurrence(num, arr[start]);
			return;
		}
			
		//由于数组已经是无序的了，没有使用randomizePartition
		int pivotIndex = QuickSort.partition(arr, start, end);
		
		int numOfOccurrence;
		numOfOccurrence = this.searchForOccurrence(arr, arr[pivotIndex]);
		this.handleNumOfOccurrence(numOfOccurrence, arr[pivotIndex]);
		
		//pivot元素左边区间的元素个数
		int leftSize = pivotIndex - start;
		//pivot元素右边区间的元素个数
		int rightSize = end - pivotIndex;
		
		//只有在左边区间的元素个数大于或者等于当前众数的重数时，对左边区间进行递归求解
		if(leftSize >= this.num)
			this.getMode(arr, start, pivotIndex - 1);
		
		//只有咋右边区间的元素个数大于或者等于当前众数的重数时，对右边区间进行递归求解
		if(rightSize >= this.num)
			this.getMode(arr, pivotIndex + 1, end);
	}

	/**
	 * 在数组arr中搜索元素i出现的次数，首先判断i是否已经搜索过
	 * 如果进行过了搜索，直接返回-1，否则进行搜索并且将i加入到已搜索的list中
	 * @param arr 待搜索的数组
	 * @param i 需要搜索的值
	 * @param start 搜索开始索引
	 * @param end 搜索结束索引
	 * @return 元素i在arr中出现的次数
	 */
	private int searchForOccurrence(int[] arr, int i)
	{
		//判断集合中是否存在元素i，如果存在i，说明元素已经被扫描过了
		if (!this.record.contains(i))
		{
			//list中不存在元素i，将元素i加入到record中
			record.add(i);
			
			int numOfOccurrence = 0;
			for (int j = this.scanStart; j <= this.scanEnd; j++)
			{
				if (i == arr[j])
					numOfOccurrence++;
			}
			return numOfOccurrence;
		}
		//如果集合中已经存在了元素i，直接返回-1
		else
			return -1;
	}
	
	private void handleNumOfOccurrence(int numOfOccurrence, int value)
	{
		if(numOfOccurrence >= this.num)
		{
			if(numOfOccurrence == this.num)
				//存在众数的重数相同的情况，将此数也加入到mode集合中
				this.mode.add(value);
			else
			{
				//如果有了更大的numOfOccurrence，那么将mode集合清空，并将此众数加入到mode集合中
				this.mode.clear();
				this.mode.add(value);
				this.num = numOfOccurrence;
			}
		}
	}
	
	/**
	 * 普通的排序-扫描算法，对数组元素个数很大，但是众数的重数很小时，更有效！
	 * @param arr 待操作的数组
	 * @param start 排序和扫描的起始索引位置
	 * @param end 排序和扫描的终止索引位置
	 */
	public void sortAndScan(int[] arr, int start, int end)
	{
		//首先对指定的索引区间进行快排
		if(start != end)
			QuickSort.quickSort(arr, start, end);
		
		int tempCount = 1;
		for (int i = start; i <= end; i++)
		{
			while(arr[i] == arr[i + 1])
			{
				tempCount++;
				if(i == end - 1)
					break;
				i++;
			}
			if(tempCount >= this.num)
			{
				if(tempCount == this.num)
					this.mode.add(arr[i]);
				else
				{
					this.mode.clear();
					this.mode.add(arr[i]);
					this.num = tempCount;
				}
			}
			
			//一定记得将tempCount进行重置
			tempCount = 1;
		}
	}


	public ArrayList<Integer> getMode()
	{
		return mode;
	}


	public int getNum()
	{
		return num;
	}
}

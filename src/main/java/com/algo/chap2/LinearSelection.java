package com.algo.chap2;

import com.algo.sorts.QuickSort;

/**
 * 线性选择算法，比如在arr中选择第k小的元素，求中位数元素也可行
 * 创建时间: 2011-4-5
 * @author Destiny
 *
 */
public class LinearSelection
{
	/**
	 * 随机化选择算法，在数组arr的索引区间内挑选第select小的元素
	 * 使用QuickSort中的partition方法进行划分，然后根据左右区域进行递归
	 * @param arr 待操作的数组
	 * @param start 索引区间开始位置
	 * @param end 索引区间结束位置
	 * @param select 第select小的元素
	 * @return 区间内的第select小的元素
	 */
	public static int randomizedSelect(int[] arr, int start, int end, int select)
	{
		//递归算法的终止条件
		if(start == end)
			return arr[start];
		
		QuickSort.randomPivot(arr, start, end);	//挑选随机化的一个pivot，放到start位置
		int pivotIndex = QuickSort.partition(arr, start, end);	//获得partition操作后pivot的index
		
		//pivot左边区间的元素个数
		int num = pivotIndex - start + 1;
		
		//判断需要选择的元素所在区间
		if(select <= num)	//表示需要选择的元素在左半区间
			return randomizedSelect(arr, start, pivotIndex, select);
		else
			return randomizedSelect(arr, pivotIndex + 1, end, select - num);
	}
	
	/**
	 * 选择算法，在数组arr的索引区间内挑选第select小的元素。在数组长度大于一个阈值时，启用优化算法
	 * 将n个元素5个5个分为一组，设最后分的组数为n组，除可能有一个组不是5个元素外。将每一组的中位数
	 * 依次交换到数组的前n个位置。找到这n个元素中的中位数，即中位数的中位数。将这个数X作为pivot，对
	 * 数组进行partition操作。以上的目标是使partition后左右区间的元素个数尽量平衡。
	 * 然后使用判断需要找的元素属于左半分区还是右半分区，进行递归调用
	 * @param arr 待操作的数组
	 * @param start 索引区间开始位置
	 * @param end 索引区间结束位置
	 * @param select 第select小的元素，注意使用的是序号 - Based on 1，不是索引 - Based on 0
	 * @return 区间内的第select小的元素的 索引！！索引！！索引！！索引！！
	 */
	public static int optimizedSelect(int[] arr, int start, int end, int select)
	{
		int threshold = 75;
		//实际待查找的元素个数
		int actualLength = end - start + 1;
		
		//检查select的合法性
		if(select < 1 || select > end - start + 1)
		{
			System.out.println("不合法的select值！");
			return -1;
		}
		
		if(actualLength <= threshold)
		{
			//如果实际元素个数小于阈值，那么直接进行快排
			QuickSort.quickSort(arr, start, end);
			return start + select - 1;
		}
		
		//如果元素个数大于了阈值
		for(int i = 0; i < (end - start - 4) / 5; i++)
			handleSubArray(i, start, arr);
		
		//找到中位数的中位数的索引
		int allMedianIndex = optimizedSelect(arr, start, start + (end - start - 4) / 5, (end - start - 4) / 10 + 1);
		//将中位数的中位数放在start位置
		int temp = arr[start];
		arr[start] = arr[allMedianIndex];
		arr[allMedianIndex] = temp;
		
		//根据中位数的中位数进行partition操作
		int pivotIndex = QuickSort.partition(arr, start, end);
		
		//计算左半区间的元素个数
		int num = pivotIndex - start + 1;
		
		//进行递归求解
		if(select <= num)	
			return optimizedSelect(arr, start, pivotIndex,  select); //表示待select的元素在左半区间
		else
			return optimizedSelect(arr, pivotIndex + 1, end, select - num);
	}

	/**
	 * 对长度为5的子数组进行处理，找到中位数并进行交换
	 * @param i 子数组的序号
	 * @param start 开始继续的索引，指arr的索引
	 * @param arr 整个数组的引用
	 */
	private static void handleSubArray(int i, int start, int[] arr)
	{
		//对每一个子区间进行排序
		QuickSort.quickSort(arr, start + i * 5, start + i * 5 + 4);
		//子元素的中位数
		int median = arr[start + i * 5 + 2];
		//进行元素的swap操作，将子组中的median与数组中的前(end - start - 4) / 5个元素进行交换
		int temp = arr[start + i];
		arr[start + i] = median;
		arr[start + i * 5 + 2] = temp;
	}
}

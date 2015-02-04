package com.algo.sorts;

public class QuickSort
{
	/**
	 * 对给定的数组arr的制定索引区间进行快排
	 * @param arr 待排数组
	 * @param start 待排序开始区间
	 * @param end 待排序结束区间
	 */
	public static void quickSort(int[] arr, int start, int end)
	{
		if(start < end)
		{
			int pivotIndex = partition(arr, start, end);
			quickSort(arr, start, pivotIndex - 1);
			quickSort(arr, pivotIndex + 1, end);
		}
	}
	
	/**
	 * 对给定的数组arr的制定索引区间进行快排，不同之处在于，首先随机挑选一个pivot
	 * @param arr 待排数组
	 * @param start 待排序开始区间
	 * @param end 待排序结束区间
	 */
	public static void quickSortRandomPivot(int[] arr, int start, int end)
	{
		if(start < end)
		{
			randomPivot(arr, start, end);	//加上了随机挑选pivot的部分
			int pivotIndex = partition(arr, start, end);
			quickSort(arr, start, pivotIndex - 1);
			quickSort(arr, pivotIndex + 1, end);
		}
	}
	
	/**
	 * 对arr中从start到end索引区间内的元素随机挑选一个成为pivot
	 * 之所以设置成为public，是因为在线性时间选择算法中会会使用到
	 * @param arr 待操作的数组
	 * @param start 索引区间开始位置
	 * @param end 索引区间结束位置
	 */
	public static void randomPivot(int[] arr, int start, int end)
	{
		int randomIndex = start + (int)(Math.random() * (end - start) + 0.5);
		swap(arr, start, randomIndex);
	}
	
	/**
	 * 对arr的start - end位置上的元素根据pivot进行划分，pivot是区间上的第一个元素，即arr[start]
	 * 之所以设置成为public，是因为在线性时间选择算法中会会使用到
	 * @param arr 待操作的数组
	 * @param start 索引区间开始位置，也是pivot元素所在的索引
	 * @param end 索引区间结束位置
	 * @return 返回pivot最后在arr中的索引位置
	 */
	public static int partition(int[] arr, int start, int end)
	{
		int i = start;
		int j = end + 1;
		int pivot = arr[start];
		
//		System.out.println("Pivot Element: " + pivot);
		//将arr中比pivot小的元素移动到左边区域
		//将arr中比pivot大的元素移动到右边区域
		while(true)
		{
			//这里可以控制是descend排序或者是ascend排序
			while(arr[++i] <pivot && i < end);	//如果遇到了pivot相同的值，最终会放在pivot的右边？？
			while(arr[--j] > pivot && j > start);
			if(i >= j)
				break;	//当索引位i超过或者等于j时，终止循环
			swap(arr, i, j);
		}
		
		//将pivot和j索引为的元素交换位置
		arr[start] = arr[j];
		arr[j] = pivot;
		
		return j;	//返回pivot元素所在的index
	}

	private static void swap(int[] arr, int i, int j)
	{
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}

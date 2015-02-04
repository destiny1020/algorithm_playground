package com.algo.chap1;

/**
 * 关于字典序的另外一个问题，和全排列有关的问题
 * @author Destiny
 *
 */
public class DictionaryOrderPermutation
{
	//测试方法调用
	public int[] getOrderAndNextSequence(int[] arr)
	{
		System.out.println("当前序列的序号：" + this.findPosition(arr));
		if(this.sortSequence(arr))
			return arr;
		else
		{
			System.out.println("已经达到最后一个序列！");
			return arr;
		}
	}

	/**
	 * 求number的阶乘
	 * @param number 待求阶乘的数
	 * @return 返回number的阶乘
	 */
	private int factorial(int number)
	{
		//进行简单的验证
		if(number <= 0)
			return -1;
		
		int sum = 1;
		while(number > 0)
		{
			sum *= number;
			number--;
		}
		
		return sum;
	}
	
	private boolean sortSequence(int[] arr)
	{
		int index1, index2;
		index1 = this.findFirstSmall(arr);
		//如果没有找到，代表已经达到最后一个序列
		if(index1 == -1)
			return false;
		index2 = this.findSmallest(arr, index1 + 1);
		this.swap(arr, index1, index2);
		this.converse(arr, index1 + 1, arr.length - 1);
		
		return true;
	}
	
	/**
	 * 获取给定序列在此字典序规则之下的序号
	 * @param arr 待处理的序列
	 * @return 此序列的序号
	 */
	private int findPosition(int[] arr)
	{
		int sum = 0;
		for(int i = 0; i < arr.length; i++)
		{
			int smallerNumber = this.getCount(arr, i);
			if(smallerNumber > 0)
				sum += smallerNumber * this.factorial(arr.length - i - 1);
		}
		
		return sum + 1;
	}
	
	/**
	 * 从待操作的数组右端开始找到第一个元素，它需要满足的条件是它的值小于它右边元素的值
	 * @param arr 待操作的数组
	 * @return 该元素的索引
	 */
	private int findFirstSmall(int[] arr)
	{
		for(int i = arr.length - 1; i > 0; i--)
		{
			if(arr[i-1] < arr[i])
				return i - 1;
		}
		//如果没有找到
		return -1;
	}
	
	/**
	 * 获得arr[start]右边所有比它小的元素的个数
	 * @param arr 待操作的数组
	 * @param start 开始计数的边界
	 * @return 返回arr[start]右边所有比它小的元素的个数
	 */
	private int getCount(int[] arr, int start)
	{
		int value = arr[start];
		int count = 0;
		for(int i = start + 1; i < arr.length; i++)
			if(arr[i] < value)
				count++;
		
		return count;
	}
	
	/**
	 * 对于数组arr中的某一个元素arr[start - 1]，找出在其右侧的所有比它的数中最小的一个，并返回该数的索引
	 * @param arr 待操作的整型数组
	 * @param start 检测开始的索引
	 * @return arr[start - 1]右侧的所有比它的数中最小的一个的索引
	 */
	private int findSmallest(int[] arr, int start)
	{
		int size = arr.length;
		int value = arr[start - 1];
		int key = arr[start];
		int index = start;
		for(int i = start + 1; i < size; i++)
		{
			if(arr[i] > value && arr[i] < key)
			{
				key = arr[i];
				index = i;
			}
		}
		
		return index;
	}
	
	/**
	 * 实现整型数组中元素位置调整
	 * @param arr 整型数组
	 * @param index1 需要调整的元素索引1
	 * @param index2 需要调整的元素索引2
	 */
	private void swap(int[] arr, int index1, int index2)
	{
		int temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;
	}
	
	/**
	 * 对数组arr中的指定序列进行逆序操作
	 * @param arr 待进行逆序操作的数组
	 * @param start 开始进行逆序操作的索引
	 * @param end 结束进行逆序操作的索引
	 */
	private void converse(int[] arr, int start, int end)
	{
		//可以观察到无论是从左侧开始循环，还是从右侧开始循环，中间的一个变量都可以表示为start + end - i
		for(int i = end; i >= (end + start + 1) / 2; i--)
			swap(arr, end - i + start, i);
//		for(int i = start; i <= (end + start + 1) / 2; i++)
//			swap(arr, i, start + end - i);
	}
}

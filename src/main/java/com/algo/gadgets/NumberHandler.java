package com.algo.gadgets;

public class NumberHandler
{
	/**
	 * 
	 * @param number 即将要转换的Integer
	 * @return 返回number的每一位数字，存储在int数组中
	 */
	public static int[] convertIntToIntArray(int number)
	{	
		String ns = String.valueOf(number);
		int[] nums = new int[ns.length()];
		
		for (int i = 0; i < ns.length(); i++)
		{
			nums[i] = Integer.parseInt(ns.charAt(i) + "");
		}
		
		return nums;
	}
	
	/**
	 *交换arr数组中索引为i1以及索引为i2的两个元素
	 * @param arr 元素所在数组
	 * @param i1 第一个待交换元素的索引
	 * @param i2 第二个待交换元素的索引
	 */
	public static void swap(int[] arr, int i1, int i2)
	{
		int temp = arr[i1];
		arr[i1] = arr[i2];
		arr[i2] = temp;
	}
	
	/**
	 * 求number的阶乘
	 * @param number 待求阶乘的数
	 * @return 返回number的阶乘
	 */
	public static long factorial(int number)
	{
		//进行简单的验证
		if(number < 0)
			return -1;
		if(number == 0)
			return 1;
		
		long sum = 1;
		while(number > 0)
		{
			sum *= number;
			number--;
		}
		
		return sum;
	}
}

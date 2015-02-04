package com.algo.chap1;

import com.algo.gadgets.NumberHandler;

public class PageStatistics
{
	public static int[] pageCounter1(int numberOfPage)
	{
		int[] counts = new int[10];
		
		for (int i = 1; i <= numberOfPage; i++)
		{
			int temp = i;
			while(temp != 0)
			{
				counts[temp % 10]++;
				temp /= 10;
			}
		}
		return counts;
	}
	
	public static int[] pageCounter2(int numberOfPage)
	{
		int partial = numberOfPage;
		int length = (int)Math.log10(numberOfPage);
		int[] pow10 = new int[length + 1];
		pow10[0] = 1;
		int[] counts = new int[10];
		int[] numsArray = NumberHandler.convertIntToIntArray(numberOfPage);
		
		for (int i = 1; i < pow10.length; i++)
		{
			pow10[i] = pow10[0] * 10;
		}
		
		int index = 0;
		//这里将length在len变量中备份一次，在最后减去多余0的处理中起到作用
		int len = length;
		int head = numsArray[index++];
		partial %= pow10[length];
		while(length > 0)
		{
			//对于0的单独处理，提高效率？
			if(head == 0)
			{
				counts[0] += partial + 1;
				head = numsArray[index++];
				length--;
				partial %= pow10[length];
				continue;
			}
			//处理除首位外的数字的出现次数，根据公式f(n)=n*10的n-1次方
			for (int i = 0; i < 10; i++)
			{
				counts[i] += head * length * pow10[length - 1];
			}
			//处理首位数字的出现次数，完整的出现次数，例如输入2234，则统计的是0xxx和1xxx中0和1的出现次数
			for (int i = 0; i < head; i++)
			{
				counts[i] += pow10[length];
			}
			//为什么是partial + 1呢，因为还要包括尾数部分全0的情况
			counts[head] += partial + 1;
			length--;
			head = numsArray[index++];
			partial %= pow10[length];
		}
		
		//开始计算最后的个位数
		for (int i = 0; i <= head; i++)
		{
			counts[i]++;
		}
		
		//对多余的0进行处理，因为公式是根据00000，00001这种形式的数字进行统计的
		for (int i = 0; i < len; i++)
		{
			counts[0] -= pow10[len];
		}
		
		return counts;
	}
}

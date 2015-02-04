package com.algo.chap1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * 创建时间: 2011-3-29
 * @author Destiny
 *
 */
public class MaxDistance
{
	/**
	 * 解决最大间隙问题的第一种解法，主要是进行排序之后求解，最快nlog(n)
	 */
	public static double firstMethod(String filePath)
	{
		DecimalFormat format = new DecimalFormat("#.0000");
		int n;
		try
		{
			FileReader fr =new FileReader(filePath);
			Scanner sc = new Scanner(fr);
			//根据输入文件的特点进行读操作，首先读取元素的个数
			if(sc.hasNextInt())
				n = sc.nextInt();
			else
				return -1;
			
			//根据n的个数来新建一个double类型的数组
			double x[] = new double[n];
			//使用sc将剩下的元素读入到x[]中
			for(int i = 0; i < n; i++)
				if(sc.hasNextDouble())
					x[i] = sc.nextDouble();
			
			//使用Arrays工具类对数据进行排序
			//默认使用改良的QS，复杂度nlog(n)
			Arrays.sort(x);
			//设置max的值为第二个元素减去第一个元素的值
			double max = x[1] - x[0];
			//开始循环，若检测到更大的值，则赋给max
			for(int i = 2; i < n; i++)
				if(x[i] - x[i - 1] > max)
					max = x[i] - x[i - 1];
			
			return Double.parseDouble(format.format(max));
			
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
			return -1;
		} catch (Exception e) 
		{
			e.printStackTrace();
			return -1;
		}
	}
	
	public static double secondMethod(String filePath)
	{
		DecimalFormat format = new DecimalFormat("#.0000");
		int n;
		try
		{
			FileReader fr =new FileReader(filePath);
			Scanner sc = new Scanner(fr);
			//根据输入文件的特点进行读操作，首先读取元素的个数
			if(sc.hasNextInt())
				n = sc.nextInt();
			else
				return -1;
			
			//根据n的个数来新建一个double类型的数组
			double x[] = new double[n];
			//使用sc将剩下的元素读入到x[]中
			for(int i = 0; i < n; i++)
				if(sc.hasNextDouble())
					x[i] = sc.nextDouble();
			
			//获取数组中的最大值以及最小值 复杂度n
			double min = x[minIndex(n, x)];
			double max = x[maxIndex(n, x)];
			
			//创建几个标记数组
			//标记桶中的元素个数，实际使用1到n-1
			int[] count = new int[n];
			//记录桶的下界，实际使用1到n-1
			double[] low = new double[n];
			//记录桶的上界，实际使用1到n-1
			double[] high = new double[n];
			
			//对以上的标记数组进行初始化
			for(int i = 1; i < n; i++)
			{
				count[i] = 0;
				low[i] = max;
				high[i] = min;
			}
			
			//定义一个桶平均间距，根据此距离进行归类
			double averDis = Double.parseDouble(format.format((max - min) / (n - 1)));
			for(int i = 0; i < n; i++)
			{
				int bucket = (int)((x[i] - min) / averDis) + 1;
				if(n == bucket)
					bucket--;
				count[bucket]++;
				
				//对桶的上界和下界进行处理
				if(x[i] < low[bucket])
					low[bucket] = x[i];
				if(x[i] > high[bucket])
					high[bucket] = x[i];
			}
			
			//初始化一个最大间距
			double maxDis = 0;
			//根据上面的分类规则，桶1永远是有元素的，去high[1]为右边界
			double leftBound = high[1];
			
			for(int i = 1; i < n; i++)
			{
				//只有桶中存在元素才进行处理
				if(count[i] > 0)
				{
					if(low[i] - leftBound > maxDis)
						maxDis = low[i] - leftBound;
					//改变右边界为当前bucket的high值
					leftBound = high[i];
				}
			}
			
			return Double.parseDouble(format.format(maxDis));
			
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
			return -1;
		} catch (Exception e) 
		{
			e.printStackTrace();
			return -1;
		}
	}
	
	/**
	 * 得到数组x中最小元素的index
	 * @param n 数组的长度
	 * @param x 待查找的数组
	 * @return 最小元素的索引
	 */
	private static int minIndex(int n, double[] x)
	{
		double min = x[0];
		int index = 0;
		for(int i = 1; i < n; i++)
			if(x[i] < min)
			{
				min = x[i];
				index = i;
			}
		
		return index;
	}
	
	/**
	 * 得到数组x中最大元素的index
	 * @param n 数组的长度
	 * @param x 待查找的数组
	 * @return 最大元素的索引
	 */
	private static int maxIndex(int n, double[] x)
	{
		double max = x[0];
		int index = 0;
		for(int i = 1; i < n; i++)
			if(x[i] > max)
			{
				max = x[i];
				index = i;
			}
		
		return index;
	}
}

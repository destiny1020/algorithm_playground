package com.algo.chap1;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 求一个区间内的拥有最多约数的数，并给出约数的数量
 * @author Destiny
 *
 */
public class MaxDivisor
{
	/**
	 * 求出给定整数number的约数个数
	 * @param number 指定的整数
	 * @return 返回number的约数个数
	 */
	private int getCountOfDivisors(int number)
	{
		//每个整数至少有两个约数，即1和它本身
		int initialCount = 2;
		int upperBound = (int)Math.sqrt(number);
		for(int i = 2; i < upperBound; i++)
		{
			if(number % i == 0)
				initialCount += 2;
		}
		if(number % upperBound == 0)
			initialCount++;
		
		return initialCount;
	}
	
	/**
	 * 给定一个闭区间，求出区间内有最多约数的数，以及它约数的个数
	 * @param lowerBound 闭区间的下界
	 * @param upperBound 闭区间的上界
	 * @return 将结果以数组的形式返回，第一个元素返回拥有最多约数个数的数，第二个元素返回最多约数的个数
	 */
	public int[] getMaxDivisor(int lowerBound, int upperBound)
	{
		//将结果以数组的形式返回，第一个元素返回拥有最多约数个数的数，第二个元素返回最多约数的个数
		int[] numberAndCounts = new int[2];
		
		//每个整数至少有两个约数，即1和它本身
		numberAndCounts[1] = 2;
		numberAndCounts[0] = lowerBound;
		
		//使用第二种时需要首先构建素数表
		int[] primeLib =this.getPrimeLib(upperBound / 2);
		
		for(int i = lowerBound; i <= upperBound; i++)
		{
			//使用第一种方法获得结果
//			int divisors = this.getCountOfDivisors(i);
			
			//使用第二种方法获得结果
			int divisors = this.getCountOfDivisors2(primeLib, i);
			
			if(divisors > numberAndCounts[1])
			{
				numberAndCounts[1] = divisors;
				numberAndCounts[0] = i;
			}
		}
		
		return numberAndCounts;
	}
	
	//----------------------------------------------------------------------------
	//----------------------------------------------------------------------------
	//----------------------------------------------------------------------------
	
	/**
	 * 得到上限为n的素数数组
	 * @param n 上限，是上界的平方根
	 * @return 返回素数数组
	 */
	public int[] getPrimeLib(int n)
	{
		//这里创建了一个长度为n+1的bool数组，第一个元素，也即下标为0的元素不需要使用
		//但是为了下标和实际数字一一对应，声明了n+1个元素
		boolean[] isPrime = new boolean[n + 1];
		
		for(int i = 0; i <= n; i++)
			isPrime[i] = true;
		
		//以n为上界时，不超过n的所有数中，最多只有n/2个数是素数
		int maxPrimes = n / 2;
		for(int i = 2; i <= maxPrimes; i++)
		{
			if(isPrime[i])
			{
				for(int j = 2 * i; j <= n; j += i)
				{
					//对于i的整数倍，都不会是素数
					isPrime[j] = false;
				}
			}
		}
		
//		//统计素数的个数
//		int primeCounter = 0;
		ArrayList<Integer> primes = new ArrayList<Integer>();
		for(int i = 2; i <= n; i++)
		{
			if(isPrime[i])
				//像传入到本函数的参数数组进行赋值，并递增计变量
//				primes[primeCounter++] = i;
				primes.add(i);
		}
		//将计数变量增加1，以放映素数的个数
//		primeCounter++;
		
		//将ArrayList转换为int[]
		int[] primeLib = new int[primes.size()];
		for (int i = 0; i < primeLib.length; i++)
		{
			primeLib[i] = primes.get(i);
		}
		return primeLib;
	}
	
	/**
	 * 暂时没有用了！
	 * 
	 * 对arr上使用折半插入法插入一个整型变量，即toBeInserted
	 * @param arr 被操作的数组，已经按照升序排序
	 * @param size 数组的长度
	 * @param toBeInserted 待插入的整型变量
	 * @return 操作结束后数组的长度，如果返回n+1则代表插入成功
	 */
	private int insertByHalf(int[] arr, int size, int toBeInserted)
	{
		//首先检查被插入的值，如果小于1，直接返回
		if(toBeInserted < 1)
			return size;
		
		int low = 0, high = size - 1;
		//这里不冗余的进行一次初始化，后面的mid可能会没有初始化，编译器判断出如果没有进入下面的while循环的话
		//后面的mid是没有进行初始化的
		int mid = (low + high) / 2;
		
		//开始进行折半查找toBeInserted应该被插入的位置
		while(low <= high)
		{
			mid = (low + high) / 2;
			//如果在arr中找到了值为toBeInserted的元素，那么直接返回，不能重复插入
			if(arr[mid] == toBeInserted)
				return size;
			//如果arr[mid]比待插入的数大，那么应该在左边继续进行折半查找
			if(arr[mid] > toBeInserted)
				high = mid - 1;
			else
				low = mid + 1;
		}//到while循环退出的时候，数组索引为mid的元素应该就是toBeInserted应该被插入的地方
		
		for(int i = size - 1; i >= mid; i--)
		{
			//从右方开始移动元素，依次往右边移动一个索引位
			arr[i + 1] = arr[i];
		}
		//将数据插入到mid索引位中
		arr[mid] = toBeInserted;
		
		//成功插入，返回数组的新尺寸
		return size + 1;
	}
	
	/**
	 * 根据素数表求number的约数个数
	 * @param primes 指定上界的缩小版素数表，即经过平方根处理过的
	 * @param length 素数表的长度
	 * @param number 待求约数个数的数
	 * @return 根据素数不熬返回number的约数个数
	 */
	private int getCountOfDivisors2(int[] primes, int number)
	{
		int length = primes.length;
		int copyOfNumber = number;
//		int[] powers = new int[length];
		int result = 1;
		//初始化powers数组
//		for(int i = 0; i < powers.length; i++)
//			powers[i] = 0;
		
		//记录power的临时变量
		int temp = 0;
		
		for (int i = 0; i < length; i++, temp = 0)
		{
			while(number % primes[i] == 0)
			{
				temp++;
				number /= primes[i];
			}
			result *= (temp + 1);
			//调整循环终止的条件
			if(number == 1 || primes[i] >= copyOfNumber / 2 + 1)
				break;
		}
		
//		int result = 1;
//		for (int i : powers)
//		{
//			result *= (i + 1);
//		}
		
		if(result == 1)
			return 2;
		else
			return result;
	}
	
	/**
	 * 给定一个闭区间，求出区间内有最多约数的数，以及它约数的个数
	 * 使用公式(e1 + 1) * (e2 + 1) * ...... * (en + 1)来求约数的个数
	 * 需要用到构建的素数表
	 * @param lowerBound 闭区间的下界
	 * @param upperBound 闭区间的上界
	 * @return 将结果以数组的形式返回，第一个元素返回拥有最多约数个数的数，第二个元素返回最多约数的个数
	 */
	public int[] getMaxDivisor2(int lowerBound, int upperBound)
	{
		return null;
	}
}

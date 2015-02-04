package com.algo.chap1;

/**
 * 
 * @author Destiny
 *	关于字典序问题的实现
 */
public class DictionaryOrder
{
	/**
	 * 给定一个字符串在字典序中的序号
	 * @param str 待确定顺序的字符串
	 * @return 该字符串的顺序
	 */
	public int getStringOrder(String str)
	{
		if(!isValidString(str))
			//表示输入字符串不合法
			return -1;
		
		//输入合法，开始判断字符串的序号
		int sum = 0;
		int length = str.length();
		
		//获得字符串长度小于str.length()的所有字符串的个数
		for(int i = 1; i < length; i++)
			sum += caculateNumberByLength(i);
		
		//获得小于第一个字母的长度为str.length()的字符串的个数
		for(int i = 1; i < changeToValue(str.charAt(0)); i++)
			sum += caculateNumber(i, length);
		
		//以第一个字母作为开始的字符串组合个数 
		//以下这一部分是重点
		for(int i = 1, temp = changeToValue(str.charAt(0)); i < length; i++)
		{
			//获取当前字符的值，从第二个字符开始
			int currentValue = changeToValue(str.charAt(i));
			//获取当前的长度
			int currentLength = length - i;
			for (int j = temp + 1; j < currentValue; j++)
			{
				sum += caculateNumber(j, currentLength);
			}
			temp = currentValue;
		}
		
		return sum + 1;
	}
	
	/**
	 * 根据字典规则来确定输入字符串是否合法
	 * @param str 待验证的字符串
	 * @return 是否合法
	 */
	private boolean isValidString(String str)
	{
		int length = str.length();
		for (int i = 0; i < length - 1; i++)
		{
			//判断字符是否按照升序排列，或者是否含有其他不合法字符
			if(str.charAt(i + 1) - str.charAt(i) <= 0 || str.charAt(i) - 'a' < 0 || str.charAt(i) - 'z' > 0)
				return false;
		}
		//判断最后一个字符是不是合法的
		if(str.charAt(length - 1) - 'a' < 0 || str.charAt(length - 1) - 'z' > 0)
			return false;
		
		return true;
	}
	
	/**
	 * 字符代表的数值，例如a=1，b=2等
	 * @param ch 需要进行转换的字符
	 * @return 字符代表的数值，例如a=1，b=2等
	 */
	private int changeToValue(char ch)
	{
		return ch - 'a' + 1;
	}
	
	/**
	 * 得到以start开头，长度为length的所有字符串的个数
	 * @param start 字符串开头字符的序号
	 * @param length 字符串长度
	 * @return 得到以start开头，长度为length的所有字符串的个数
	 */
	private int caculateNumber(int start, int length)
	{
		//当字符串的长度为1时，直接返回1
		if(length == 1)
			return 1;
		
		int sum = 0;
		for (int i = start + 1; i <= 26; i++)
		{
			sum += caculateNumber(i, length - 1); 
		}
		
		return sum;
	}
	
	/**
	 * 获得长度为length的所有字符串的个数
	 * @param length 字符串的长度
	 * @return 获得长度为length的所有字符串的个数
	 */
	private int caculateNumberByLength(int length)
	{
		int sum = 0;
		for (int i = 1; i <= 26; i++)
		{
			sum += caculateNumber(i, length);
		}
		
		return sum;
	}
}

package com.algo.chap2;

public class IntegerDivision
{
	public int divisionCount(int integer)
	{
		return division(integer, integer);
	}
	
	private int division(int integer, int maxAddend)
	{
		if(integer < 1 || maxAddend < 1)
			return -1;
		if(integer == 1 || maxAddend == 1)
			return 1;
		//如果剩余的数小于最大加数
		if(integer < maxAddend)
			return division(integer, integer);
		if(integer == maxAddend)
			return 1 + division(integer, maxAddend - 1);
		
		return division(integer, maxAddend - 1) + division(integer - maxAddend, maxAddend);
	}
}

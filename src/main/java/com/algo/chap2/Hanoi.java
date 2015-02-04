package com.algo.chap2;

/**
 * 经典问题Hanoi
 * 创建时间: 2011-3-30
 * @author Destiny
 *
 */
public class Hanoi
{
	public int moveCount = 0;
	
	public void solution(int n, char tower1, char tower2, char tower3)
	{
		if(n == 0)
			return;
		else
		{
			this.solution(n - 1, tower1, tower3, tower2);
			this.move(tower1, tower2);
			this.solution(n - 1, tower3, tower2, tower1);
		}
	}
	
	private void move(char tower1, char tower2)
	{
		System.out.println(tower1 + " ---> " + tower2);
		moveCount++;
	}
}

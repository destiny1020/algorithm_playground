package com.algo.others;

public class SnakePrint
{
	private int row;
	private int column;
	private int[][] array;
	//初始情况，方向是向右的
	private Direction currentDirection = Direction.RIGHT;
	
	public SnakePrint(int row, int column)
	{
		this.row = row;
		this.column = column;
		array = new int[row][column];
	}
	
	//注意枚举类型的声明方式!
	private enum Direction
	{
		LEFT, RIGHT, UP, DOWN;
	}
	
	/**
	 * 根据当前的位置和当前的方向来判断下一个方向
	 * @param ri
	 * @param ci
	 * @return
	 */
	private Direction getNextDirection(int ri, int ci)
	{
		Direction direction = this.currentDirection;
		switch (direction)
		{
		case RIGHT:
			if(ci == this.column - 1 || array[ri][ci + 1] != 0)
				direction = Direction.DOWN;   // 或者使用 direction = direction.DOWN 也是可以的
			break;
		case DOWN:
			if(ri == this.row - 1 || array[ri + 1][ci] != 0)
				direction = Direction.LEFT;
			break;
		case LEFT:
			if(ci == 0 || array[ri][ci - 1] != 0)
				direction = Direction.UP;
			break;
		case UP:
			if(ri == 0 || array[ri - 1][ci] != 0)
				direction = Direction.RIGHT;
			break;
		default:
			System.out.println("Something is wrong");
			break;
		}
		
		return direction;
	}
	
	/**
	 * 初始化数组
	 */
	private void initArray()
	{
		int ri, ci;
		ri = ci = 0;
		
		for(int i = 0; i < this.row * this.column; i++)
		{
			array[ri][ci] = i + 1;
			currentDirection = this.getNextDirection(ri, ci);
			switch (currentDirection)
			{
			case RIGHT:
				ci++;
				break;
			case LEFT:
				ci--;
				break;
			case DOWN:
				ri++;
				break;
			case UP:
				ri--;
				break;
			default:
				System.out.println("Something is wrong!");
				break;
			}
		}
	}
	
	/**
	 * 将矩阵打印出来
	 */
	public void printArray()
	{
		this.initArray();
		
		for(int i = 0; i < row; i++)
		{
			for (int j = 0; j < column; j++)
			{
				System.out.print(this.array[i][j] + "\t");
			}
			System.out.println();
		}
	}
}

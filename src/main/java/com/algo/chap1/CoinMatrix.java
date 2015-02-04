package com.algo.chap1;

/**
 * 金币阵列问题实现
 * 创建时间: 2011-3-28
 * @author Destiny
 *
 */
public class CoinMatrix
{
	private int rows;
	private int columns;
	private int count = 0;
	private int best = -1;
	private int[][] start;
	private int[][] end;
	private int[][] copyOfEnd;
	
	/**
	 */
	public CoinMatrix(int rows, int columns, int[][] start, int[][] end)
	{
		this.rows = rows;
		this.columns = columns;
		this.start = start;
		this.end = end;
		
		this.best = rows + columns + 1;
		
		this.copyOfEnd = new int[rows][columns];
	}
	
	public int getCount()
	{
		return count;
	}
	
	/**
	 * 将矩阵toBeCopied拷贝到copy数组中
	 * @param copy 目标数组
	 * @param toBeCopied 需要被拷贝的数组
	 */
	private void createCopyOfEnd(int[][] copy, int[][] toBeCopied)
	{
		for(int i = 0; i < rows; i++)
			for(int j = 0; j < columns; j++)
				copy[i][j] = toBeCopied[i][j];
	}
	
	/**
	 * 将矩阵arr中的第row行中的元素全部翻转，例如：将0翻转成1，将1翻转成0
	 * @param arr 待处理的矩阵
	 * @param row 待处理的行
	 */
	private void transRows(int[][] arr, int row)
	{
		if(row >= this.rows)
		{
			System.out.println("Row Error!");
			return;
		}
		
		for(int i = 0; i < columns; i++)
			//对行中的每一个元素进行抑或处理
			arr[row][i] ^= 1;
		
		this.count++;
	}
	
	/**
	 * 将矩阵的col1和col2对换
	 * @param arr 待操作的矩阵
	 * @param col1 待交换的一列
	 * @param col2 待交换的另一列
	 */
	private void transColumns(int[][] arr, int col1, int col2)
	{
		if(col1 >= this.columns || col2 >= this.columns)
		{
			System.out.println("Column Error!");
			return;
		}
		
		//如果col1和col2相同，则什么也不做
		if(col1 == col2)
			return;
		
		int temp;
		for(int i = 0; i < this.rows; i++)
		{
			temp = arr[i][col1];
			arr[i][col1] = arr[i][col2];
			arr[i][col2] = temp;
		}
		
		this.count++;
	}
	
	/**
	 * 判断矩阵arr1的col1列和矩阵arr2的col2列是否完全一致
	 * @param arr1 待判定的矩阵1
	 * @param arr2 待判定的矩阵2
	 * @param col1 待判定的矩阵1中的第col1列
	 * @param col2 待判定的矩阵2中的第col2列
	 * @return true如果一致，false不一致
	 */
	private boolean isColumnSame(int[][] arr1, int[][] arr2, int col1, int col2)
	{
		boolean flag = true;
		for(int i = 0; i < this.rows; i++)
		{
			if(arr1[i][col1] != arr2[i][col2])
				flag = false;
		}
		
		return flag;
	}
	
	/**
	 * 根据枚举法找到count数目最短的解法
	 * @return 操作步骤，即count的值
	 */
	public int judgeFeasibility()
	{
		if(null == start || null == end)
			//返回-2表示程序错误
			return -2;
		
		boolean isFound;
		
		//将end状态矩阵保存起来
		this.createCopyOfEnd(copyOfEnd, end);
		
		//开始进行循环枚举
		for(int i = 1; i < this.columns; i++)
		{
			//首先对end矩阵进行恢复操作
			this.createCopyOfEnd(end, copyOfEnd);
			//将操作步骤置0
			this.count = 0;
			
			//将第i列与第一列交换
			this.transColumns(end, 0, i);
			
			//进行循环比较第一列中每个元素与start第一列中每个元素是否相等
			//若不相等则进行行变换
			for(int j = 0; j < rows; j++)
			{
				if(start[j][0] != end[j][0])
					this.transRows(end, j);
			}
			
			for(int j = 1; j < columns; j++)
			{
				//初始设置为没有找到
				isFound = false;
				for(int k = j; k < columns; k++)
				{
					//如果start中的第j列和end中的第k列相同
					if(this.isColumnSame(start, end, j, k))
					{
						this.transColumns(end, j, k);
						isFound = true;
						//跳出里层for循环
						break;
					}
				}
				//如果里层循环都没有找到相同的列，那么终止外层循环
				if(!isFound)
				{
					this.count = -1;
					break;
				}
			}
			
			//将操作数字和best相比，并处理
			if(this.count < this.best && count != -1)
				this.best = this.count;
		}
		
		return this.best;
	}
}

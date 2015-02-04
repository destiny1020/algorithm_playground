package com.algo.chap2;

/**
 * 问题应该就是在X和Y坐标上分别求出中位数
 * 使用线性时间选择算法
 * 创建时间: 2011-4-5
 * @author Destiny
 *
 */
public class PostOfficeLocation
{
	/**
	 * 保存结果坐标
	 */
	private int[] location = new int[2];
	
	/**
	 * 保存|x1 - x2| + |y1 - y2|的总和
	 * 即距离的总和
	 */
	private int distance = 0;
	
	/**
	 * 首先计算最佳的XY坐标，最后的distance和坐标保存在此类的成员变量中
	 * @param x X坐标序列
	 * @param y Y坐标序列
	 */
	public void CaculateDistance(int[] x, int[] y)
	{
		//利用线性时间选择中优化过的选择算法分别得到最佳X和Y的坐标
		int indexX = LinearSelection.optimizedSelect(x, 0, x.length - 1, x.length / 2 + 1);
		int indexY = LinearSelection.optimizedSelect(y, 0, y.length - 1, y.length / 2 + 1);
		location[0] = x[indexX];
		location[1] = y[indexY];
		
		//分局最佳XY坐标算出距离的和
		distance += this.caculateDistance(x, location[0]);
		distance += this.caculateDistance(y, location[1]);
	}
	
	/**
	 * 计算一个数组中的每一个元素到median的距离的总和
	 * @param arr 待操作的数组
	 * @param median 数组的中位数
	 * @return 距离的总和
	 */
	private int caculateDistance(int[] arr, int median)
	{
		int tempDistance = 0;
		
		for(int i = 0; i < arr.length; i++)
		{
			tempDistance += Math.abs(arr[i] - median);
		}
		
		return tempDistance;
	}

	public int[] getLocation()
	{
		return location;
	}

	public int getDistance()
	{
		return distance;
	}
}

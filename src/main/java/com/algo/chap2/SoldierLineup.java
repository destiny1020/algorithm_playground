package com.algo.chap2;

import com.algo.sorts.QuickSort;

/**
 * 士兵站队问题，转换为求XY两个方向上的median，其中X需要进行变换
 * 创建时间: 2011-4-7
 * @author Destiny
 *
 */
public class SoldierLineup
{
	private int[] bestLocation = new int[2];
	private int distance = 0;
	
	public void caculateLocationAndDistance(int[] locationsX, int[] locationsY)
	{
		//对X坐标进行处理
		//这里需要注意啊！
		QuickSort.quickSort(locationsX, 0, locationsX.length - 1);
		this.preprocessX(locationsX);
		
		int indexBestX = LinearSelection.optimizedSelect(locationsX, 0, locationsX.length - 1, locationsX.length / 2 + 1);
		int indexBestY = LinearSelection.optimizedSelect(locationsY, 0, locationsY.length - 1, locationsY.length / 2 + 1);
		bestLocation[0] = locationsX[indexBestX];
		bestLocation[1] = locationsY[indexBestY];
		
		distance += this.caculateDistance(locationsX, bestLocation[0]);
		distance += this.caculateDistance(locationsY, bestLocation[1]);
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
	
	private void preprocessX(int[] locationsX)
	{
		for(int i = 0; i < locationsX.length; i++)
			locationsX[i] -= i;
	}
	
	public int[] getBestLocation()
	{
		return bestLocation;
	}
	
	public int getDistance()
	{
		return distance;
	}
}

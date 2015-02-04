package com.algo.chap3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.algo.gadgets.Point;

/**
 * 使用书中介绍的基于跳跃点的算法来求解背包问题
 * 可以支持weight和value是double类型数值的情况
 * 创建时间: 2011-4-11
 * @author Destiny
 *
 */
public class KnapsackExtended
{
	private double[] weight;
	private double[] value;
	private int num;
	private double maxWeight;
	
	/**
	 * 用于保存最后的取舍结果，1代表取，0代表不取
	 */
	private int[] result;
	
	private List<Point> weightAndValue;
	
	/**
	 * 保存跳跃点的信息
	 * 相当于形式化表示中的P[i]
	 */
	private List<Point> keyPoints;
	
	/**
	 * 保存临时点的信息
	 * 相当于形式化表示中的Q[i]
	 */
	private List<Point> tempPoints;
	
	/**
	 * 保存总价值
	 */
	private double totalValue;
	
	public KnapsackExtended(int num, double maxWeight, double[] weight, double[] value) throws Exception
	{
		if(num != weight.length || num != value.length)
			throw new Exception("物品的个数和weight数组的元素个数或和value数组的元素个数不一致！");
		
		//进行初始化的操作
		this.weight = weight;
		this.value = value;
		this.num = num;
		this.maxWeight = maxWeight;
		
		//初始化matrix以及result
		result = new int[num + 1];
		
		//初始化跳跃点的List
		this.keyPoints = new ArrayList<Point>();
		keyPoints.add(new Point(0, 0));
		
		//将Weight以及Value信息构建Point信息，并放如List中
		this.initWeightAndValueList();
	}
	
	public void run()
	{
		this.createKeyPoints();
		
		//TEST
		for (int i = 0; i < keyPoints.size(); i++)
		{
			System.out.println("X = " + keyPoints.get(i).getX() + "; Y = " + keyPoints.get(i).getY());
		}
	}
	
	private void createKeyPoints()
	{
		for(int i = this.num - 1; i >= 0; i--)
		{
			tempPoints = this.addListByPoint(keyPoints, this.weightAndValue.get(i));	//还应该有一个Point的过滤功能，x大于maxWeight的不能保留
			this.getUnionList(keyPoints, tempPoints);	
			this.deleteControlledPoint(keyPoints);
		}
	}

	/**
	 * 使用weight数组以及value数组来初始化ArrayList
	 */
	private void initWeightAndValueList()
	{
		this.weightAndValue = new ArrayList<Point>();
		
		for(int i = 0; i < num; i++)
		{
			weightAndValue.add(new Point(this.weight[i], this.value[i]));
		}
	}

	/**
	 * 对参数中的两个List求并集
	 * @param list1
	 * @param list2
	 */
	private void getUnionList(List<Point> list1, List<Point> list2)
	{
		list1.removeAll(list2);
		list1.addAll(list2);
	}
	
	/**
	 * 删除一个未排序的ArrayList中的受控点
	 * @param points
	 */
	private void deleteControlledPoint(List<Point> pointsList)
	{
		if (pointsList.size() > 1)
		{
			Collections.sort(pointsList);
		}
		
		//从index等于0的地方开始判断，会有问题！
		//可能最后的返回结果
		/**
		 * X = 0.0; Y = 0.0
			X = 2.0; Y = 3.0
			X = 2.0; Y = 6.0
			X = 4.0; Y = 9.0
			X = 6.0; Y = 12.0
			X = 8.0; Y = 15.0
			而其中的X = 2.0; Y = 3.0明显不合要求，属于受控点
		 */
		for (int i = 0; i < pointsList.size() - 1;)
		{
			if((pointsList.get(i).getX() < pointsList.get(i + 1).getX() || 
					Math.abs(pointsList.get(i).getX() - pointsList.get(i + 1).getX()) <= 0.000001) && 
					(pointsList.get(i).getY() > pointsList.get(i + 1).getY() ||
					Math.abs(pointsList.get(i).getY() - pointsList.get(i + 1).getY()) <= 0.000001))
			{
				pointsList.remove(i + 1);
				continue;
			}
			//需要删除前面注释中不合要求的点，判断前后点X相同，但是前者的Y小，实际上也是受控点
			else if((Math.abs(pointsList.get(i).getX() - pointsList.get(i + 1).getX()) <= 0.000001) && 
					(pointsList.get(i).getY() < pointsList.get(i + 1).getY() ||
					Math.abs(pointsList.get(i).getY() - pointsList.get(i + 1).getY()) <= 0.000001))
			{
				pointsList.remove(i);
				continue;
			}
			i++;
		}
		
//		for(int i = pointsList.size() - 1; i >= 1; i--)
//		{
//			if((pointsList.get(i).getX() > pointsList.get(i - 1).getX() || 
//					Math.abs(pointsList.get(i).getX() - pointsList.get(i - 1).getX()) <= 0.000001) && 
//					(pointsList.get(i).getY() < pointsList.get(i - 1).getY() ||
//					Math.abs(pointsList.get(i).getY() - pointsList.get(i - 1).getY()) <= 0.000001))
//			{
//				pointsList.remove(i);
//			}
//		}
	}
	
	/**
	 * 
	 * @param points
	 * @param p
	 * @return
	 */
	private List<Point> addListByPoint(List<Point> points, Point p)
	{
		//需要对points进行深clone，得到一个新的List后，对新的进行操作并返回！
		
		List<Point> temp = new ArrayList<Point>();
		
		for (int i = 0; i < points.size(); i++)
		{
			Point candidate = new Point(points.get(i)).add(p);
			if(candidate.getX() <= 10)
				temp.add(candidate);
		}
		
		return temp;
	}
}


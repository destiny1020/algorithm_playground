package com.algo.gadgets;

import java.util.Comparator;

/**
 * 目前主要根据x的坐标来判断
 * 创建时间: 2011-4-11
 * @author Destiny
 *
 */
public class PointComparator implements Comparator<Point>
{
	@Override
	public int compare(Point o1, Point o2)
	{
		if(Math.abs(o1.getX() - o2.getX()) <= 0.00001)
		{
			if(o1.getY() - o2.getY() <= 0.00001)
				return 0;
			if(o1.getY() > o2.getY())
				return 1;
			
			return -1;
		}
		else if(o1.getX() < o2.getX())
			return -1;
		
		return 1;
	}

}

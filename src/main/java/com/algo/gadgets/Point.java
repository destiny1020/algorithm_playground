package com.algo.gadgets;

import java.util.Comparator;

/**
 * 实现了一些点的常用操作，实现为double类型的
 * 顺便复习C&C接口的使用方法
 * 创建时间: 2011-4-11
 * @author Destiny
 *	实际的类型，比如Integer，Double等
 * @param <T>
 */
public class Point implements Comparable<Point>
{
	private double x;
	private double y;
	
	private static Comparator<Point> comparator;
	
	public Point(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Point(Point p)
	{
		this.x = p.getX();
		this.y = p.getY();
	}
	
	public Point add(double x, double y)
	{
		this.x += x;
		this.y += y;
		
		return this;
	}
	
	public Point add(Point p)
	{
		this.x += p.getX();
		this.y += p.getY();
		
		return this;
	}

	@Override
	public int compareTo(Point o)
	{
//		System.out.println(this);
//		System.out.println(o);
		
		return Point.comparator.compare(this, o);
	}

	@Override
	public String toString()
	{
		return "X = " + this.x + "; Y = " + this.y;
	}
	
	public double getX()
	{
		return x;
	}

	public void setX(double x)
	{
		this.x = x;
	}

	public double getY()
	{
		return y;
	}

	public void setY(double y)
	{
		this.y = y;
	}

	public static void setComparator(Comparator<Point> comparator)
	{
		Point.comparator = comparator;
	}
	
}

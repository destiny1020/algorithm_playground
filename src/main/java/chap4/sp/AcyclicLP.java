package chap4.sp;

import java.util.Arrays;

import chap1.LinkedStack;
import chap4.digraph.DirectedDepthFirstOrder;

/**
 * Acyclic Longest Path Problem. The only differences between Acyclic SP and
 * Acyclic LP are this method and init of distTo
 * 
 * @author Destiny
 * 
 */
public class AcyclicLP implements IPaths
{
	private double[] distTo;
	private DirectedEdge[] edgeTo;

	public AcyclicLP(WeightedDigraph wdi, int source)
	{
		distTo = new double[wdi.getV()];
		Arrays.fill(distTo, Double.NEGATIVE_INFINITY);
		edgeTo = new DirectedEdge[wdi.getV()];

		// Init source
		distTo[source] = 0.0;

		DirectedDepthFirstOrder ddfo = new DirectedDepthFirstOrder(wdi);

		for (int w : ddfo.getReversePost())
		{
			relax(wdi, w);
		}
	}

	/**
	 * The only differences between Acyclic SP and Acyclic LP are this method
	 * and init of distTo
	 * 
	 * @param wdi
	 * @param v
	 */
	private void relax(WeightedDigraph wdi, int v)
	{
		for (DirectedEdge edge : wdi.adj(v))
		{
			int w = edge.to();
			if (distTo[w] < distTo[v] + edge.getWeight())
			{
				distTo[w] = distTo[v] + edge.getWeight();
				edgeTo[w] = edge;
			}
		}
	}

	@Override
	public double distTo(int dest)
	{
		return distTo[dest];
	}

	@Override
	public boolean hasPathsTo(int dest)
	{
		return (null != edgeTo[dest]);
	}

	@Override
	public Iterable<DirectedEdge> pathsTo(int dest)
	{
		if (!hasPathsTo(dest))
			return null;

		LinkedStack<DirectedEdge> stack = new LinkedStack<DirectedEdge>();
		for (DirectedEdge edge = edgeTo[dest]; null != edge; edge = edgeTo[edge
				.from()])
		{
			stack.push(edge);
		}

		return stack;
	}
}

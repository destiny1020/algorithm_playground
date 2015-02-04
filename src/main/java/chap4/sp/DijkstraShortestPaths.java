package chap4.sp;

import java.util.Arrays;

import chap1.LinkedStack;
import chap2.heapsort.IndexMinPQ;

public class DijkstraShortestPaths implements IPaths
{
	private double[] distTo;
	private DirectedEdge[] edgeTo;
	private IndexMinPQ<Double> pq;
	
	public DijkstraShortestPaths(WeightedDigraph wdi, int source)
	{
		distTo = new double[wdi.getV()];
		Arrays.fill(distTo, Double.POSITIVE_INFINITY);
		
		edgeTo = new DirectedEdge[wdi.getV()];
		
		pq = new IndexMinPQ<Double>(wdi.getV());
		
		distTo[source] = 0.0;
		edgeTo[source] = null;
		
		pq.insert(source, 0.0);
		while(!pq.isEmpty())
			relax(wdi, pq.delMin());
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
		if(!hasPathsTo(dest))
			return null;
		
		LinkedStack<DirectedEdge> stack = new LinkedStack<DirectedEdge>();
		for(DirectedEdge edge = edgeTo[dest]; null != edge; edge = edgeTo[edge.from()])
		{
			stack.push(edge);
		}
		
		return stack;
	}
	
	private void relax(WeightedDigraph wdi, int v)
	{
		for(DirectedEdge edge : wdi.adj(v))
		{
			int w = edge.to();
			if(distTo[w] > distTo[v] + edge.getWeight())
			{
				distTo[w] = distTo[v] + edge.getWeight();
				edgeTo[w]  = edge;
				if(pq.contains(w))
					pq.change(w, distTo[w]);
				else
					pq.insert(w, distTo[w]);
			}
//			else
//				System.out.println("Oh yeah!");
		}
	}
}

package chap4.digraph;

import chap1.LinkedStack;
import chap4.sp.DirectedEdge;
import chap4.sp.WeightedDigraph;

public class DirectedDepthFirstCycleDetection
{
	private boolean[] visited;
	private boolean[] onStack;
	private int[] edgeTo;
	private LinkedStack<Integer> cycle;

	public DirectedDepthFirstCycleDetection(Digraph di)
	{
		this.visited = new boolean[di.getV()];
		this.onStack = new boolean[di.getV()];
		this.edgeTo = new int[di.getV()];

		for (int i = 0; i < di.getV(); i++)
		{
			if (!visited[i])
				dfs(di, i);
		}
	}

	/**
	 * Used for Acyclic SP problem
	 * 
	 * @param wdi
	 */
	public DirectedDepthFirstCycleDetection(WeightedDigraph wdi)
	{
		this.visited = new boolean[wdi.getV()];
		this.onStack = new boolean[wdi.getV()];
		this.edgeTo = new int[wdi.getV()];

		for (int i = 0; i < wdi.getV(); i++)
		{
			if (!visited[i])
				dfs(wdi, i);
		}
	}

	private void dfs(Digraph di, int current)
	{
		visited[current] = true;
		onStack[current] = true;

		for (int adj : di.adj(current))
		{
			if (hasCycle())
				return;
			if (!visited[adj])
			{
				edgeTo[adj] = current;
				dfs(di, adj);
			} else if (onStack[adj])
			{
				cycle = new LinkedStack<Integer>();
				cycle.push(adj);
				for (int start = current; start != adj; start = edgeTo[start])
				{
					cycle.push(start);
				}
				cycle.push(adj);
			}
		}

		onStack[current] = false;
	}

	/**
	 * Used for Acyclic SP problem
	 * 
	 * @param wdi
	 * @param current
	 */
	private void dfs(WeightedDigraph wdi, int current)
	{
		visited[current] = true;
		onStack[current] = true;

		for (DirectedEdge adj : wdi.adj(current))
		{
			int w = adj.to();
			if (hasCycle())
				return;
			if (!visited[w])
			{
				edgeTo[w] = current;
				dfs(wdi, w);
			} else if (onStack[w])
			{
				cycle = new LinkedStack<Integer>();
				cycle.push(w);
				for (int start = current; start != w; start = edgeTo[start])
				{
					cycle.push(start);
				}
				cycle.push(w);
			}
		}

		onStack[current] = false;
	}

	public boolean hasCycle()
	{
		return cycle != null;
	}

	public Iterable<Integer> getCycle()
	{
		return cycle;
	}
}

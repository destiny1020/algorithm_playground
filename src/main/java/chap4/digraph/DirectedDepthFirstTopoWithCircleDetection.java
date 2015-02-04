package chap4.digraph;

import chap1.LinkedStack;

/**
 * Topological Sorting
 * 
 * @author Destiny
 * 
 */
public class DirectedDepthFirstTopoWithCircleDetection
{
	private boolean[] visited;
	private boolean[] onStack;
	private int[] edgeTo;
	private LinkedStack<Integer> reversePost;
	private LinkedStack<Integer> cycle;

	/**
	 * Topological Sorting Constructor
	 */
	public DirectedDepthFirstTopoWithCircleDetection(Digraph di)
	{
		this.visited = new boolean[di.getV()];
		this.onStack = new boolean[di.getV()];
		this.edgeTo = new int[di.getV()];

		this.reversePost = new LinkedStack<Integer>();

		for (int i = 0; i < di.getV(); i++)
		{
			if (!visited[i])
			{
				dfs(di, i);
			}
		}
	}


	private void dfs(Digraph di, int v)
	{
		visited[v] = true;
		onStack[v] = true;

		for (int w : di.adj(v))
		{
			if(hasCycle())
			{
				return;
			}
			if (!visited[w])
			{
				edgeTo[w] = v;
				dfs(di, w);
			}
			else if(onStack[w])
			{
				cycle = new LinkedStack<Integer>();
				cycle.push(w);
				for(int start = v; start != w; start = edgeTo[start])
				{
					cycle.push(v);
				}
				cycle.push(w);
			}
		}

		reversePost.push(v);
		onStack[v] = false;
	}

	private boolean hasCycle() 
	{
		return (null != cycle);
	}
	
	public Iterable<Integer> getReversePost()
	{
		if(!hasCycle())
		{
			return reversePost;
		}
		else 
		{
			throw new IllegalArgumentException("Has Cycle: " + getCycle());
		}
	}
	
	public Iterable<Integer> getCycle() 
	{
		return cycle;
	}
}

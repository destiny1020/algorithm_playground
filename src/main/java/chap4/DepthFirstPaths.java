package chap4;

import chap1.LinkedStack;

public class DepthFirstPaths
{
	private boolean[] visited;
	private int[] edgeTo;
	private final int source;
	
	public DepthFirstPaths(Graph g, int source)
	{
		this.source = source;
		
		this.visited = new boolean[g.getV()];
		this.edgeTo = new int[g.getV()];
		
		dfs(g, source);
	}

	private void dfs(Graph g, int v)
	{
		this.visited[v] = true;
		
		for(int w : g.adj(v))
		{
			if(!this.visited[w])
			{
				edgeTo[w] = v;
				this.dfs(g, w);
			}
		}
	}
	
	public boolean connected(int dest)
	{
		return this.visited[dest];
	}
	
	public Iterable<Integer> pathTo(int dest)
	{
		if(!this.visited[dest])
			return null;
		
		LinkedStack<Integer> stack = new LinkedStack<Integer>();
		for(int v = dest; v != this.source; v = this.edgeTo[v])
		{
			stack.push(v);
		}
		stack.push(this.source);
		
		return stack;
	}
}

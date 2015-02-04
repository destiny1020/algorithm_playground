package chap4.digraph;

import java.util.Arrays;

import chap1.LinkedStack;

public class DirectedDepthFirstPaths
{
	private boolean[] visited;
	private int[] edgeTo;
	private final int source;
	
	public DirectedDepthFirstPaths(Digraph di, int source)
	{
		this.source = source;
		
		this.visited = new boolean[di.getV()];
		this.edgeTo = new int[di.getV()];
		
		// Init edgeTo with -1
		Arrays.fill(edgeTo, -1);
		
		dfs(di, source);
	}
	
	private void dfs(Digraph di, int v)
	{
		visited[v] = true;
		
		for(int w : di.adj(v))
		{
			if(!visited[w])
			{
				edgeTo[w] = v;
				dfs(di, w);
			}
		}
	}
	
	public boolean connected(int dest)
	{
		return visited[dest];
	}
	
	public Iterable<Integer> pathTo(int w)
	{
		if(!visited[w])
			return null;
		
		LinkedStack<Integer> stack = new LinkedStack<Integer>();
		for(int v = w; v != source; v = edgeTo[v])
		{
			stack.push(v);
		}
		stack.push(source);
		
		return stack;
	}
}

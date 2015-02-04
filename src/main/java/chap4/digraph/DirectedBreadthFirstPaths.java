package chap4.digraph;

import java.util.Arrays;

import chap1.LinkedQueue;
import chap1.LinkedStack;

public class DirectedBreadthFirstPaths
{
	private boolean[] visited;
	private int[] edgeTo;
	private final int source;
	private LinkedQueue<Integer> queue;
	
	public DirectedBreadthFirstPaths(Digraph di, int source)
	{
		this.source = source;
		
		this.visited = new boolean[di.getV()];
		this.edgeTo = new int[di.getV()];
		
		// Fill with -1
		Arrays.fill(edgeTo, -1);
		
		this.queue = new LinkedQueue<Integer>();
		
		start(di);
	}

	private void start(Digraph di)
	{
		visited[source] = true;
		edgeTo[source] = source;
		queue.enqueue(source);
		
		while(!queue.isEmpty())
		{
			int v = queue.dequeue();
			for(int w : di.adj(v))
			{
				if(!visited[w])
				{
					visited[w] = true;
					edgeTo[w] = v;
					queue.enqueue(w);
				}
			}
		}
	}
	
	public boolean connected(int v)
	{
		return visited[v];
	}
	
	public Iterable<Integer> pathTo(int v)
	{
		if(!connected(v))
			return null;
		
		LinkedStack<Integer> stack = new LinkedStack<Integer>();
		for(int w = v; w != source; w = edgeTo[w])
		{
			stack.push(w);
		}
		stack.push(source);
		
		return stack;
	}
}

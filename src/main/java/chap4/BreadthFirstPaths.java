package chap4;

import chap1.LinkedQueue;
import chap1.LinkedStack;

public class BreadthFirstPaths
{
	private boolean[] visited;
	private int[] edgeTo;
	private final int source;
	private LinkedQueue<Integer> queue;
	
	public BreadthFirstPaths(Graph g, int source)
	{
		this.source = source;
		
		this.visited = new boolean[g.getV()];
		this.edgeTo = new int[g.getV()];
		
		this.queue = new LinkedQueue<Integer>();
		
		start(g);
	}

	private void start(Graph g)
	{
		this.visited[source] = true;
		queue.enqueue(source);
		
		while(!queue.isEmpty())
		{
			int v = queue.dequeue();
			for(int w : g.adj(v))
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

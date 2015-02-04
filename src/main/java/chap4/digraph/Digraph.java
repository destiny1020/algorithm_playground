package chap4.digraph;

import java.io.IOException;
import java.util.Scanner;

import chap1.LinkedBag;

public class Digraph
{
	private final int V;
	private int E;
	private LinkedBag<Integer>[] adj;
	
	@SuppressWarnings("unchecked")
	public Digraph(int V)
	{
		this.V = V;
		adj = (LinkedBag<Integer>[]) new LinkedBag[V];
		for(int i = 0; i < V; i++)
			adj[i] = new LinkedBag<Integer>();
	}
	
	public Digraph(Scanner scanner) throws IOException
	{
		this(scanner.nextInt());
		
		int edges = 0;
		if(scanner.hasNext())
			edges = scanner.nextInt();
		
		if(0 == edges)
			throw new IllegalArgumentException("Input file has error, no edge number defined");
		
		for(int i = 0; i < edges; i++)
		{
			int v = scanner.nextInt();
			int w = scanner.nextInt();
			this.addEdge(v, w);
		}
	}

	public void addEdge(int v, int w)
	{
		adj[v].add(w);
		E++;
	}
	
	public Digraph reverse()
	{
		Digraph reversed = new Digraph(V);
		for(int v = 0; v < V; v++)
		{
			// for each v -> w
			for(int w : adj[v])
			{
				reversed.addEdge(w, v);
			}
		}
		
		return reversed;
	}
	
	public Iterable<Integer> adj(int v)
	{
		return adj[v];
	}

	public int getV()
	{
		return V;
	}

	public int getE()
	{
		return E;
	}

	public LinkedBag<Integer>[] getAdj()
	{
		return adj;
	}
}

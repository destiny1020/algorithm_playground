package chap4;

import java.io.IOException;
import java.util.Scanner;

import chap1.LinkedBag;

public class Graph
{
	private final int V;
	private int E;
	private LinkedBag<Integer>[] adj;

	@SuppressWarnings("unchecked")
	public Graph(int V)
	{
		this.V = V;
		adj = (LinkedBag<Integer>[]) new LinkedBag[V];
		for (int i = 0; i < V; i++)
			adj[i] = new LinkedBag<Integer>();
	}

	public Graph(Scanner scanner) throws IOException
	{
		this(scanner.nextInt());
		
		int E = 0;
		if (scanner.hasNext())
			E = scanner.nextInt();
		else
			E = 0;

		if(0 == E)
			return;
		else
		{
			for (int i = 0; i < E; i++)
			{
				int v = scanner.nextInt();
				int w = scanner.nextInt();
				this.addEdge(v, w);
			}
		}
	}

	public void addEdge(int v, int w)
	{
		this.adj[v].add(w);
		this.adj[w].add(v);
		E++;
	}

	public Iterable<Integer> adj(int v)
	{
		return this.adj[v];
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

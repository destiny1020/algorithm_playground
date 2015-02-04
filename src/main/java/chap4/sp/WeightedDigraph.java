package chap4.sp;

import java.io.IOException;
import java.util.Scanner;

import chap1.LinkedBag;

public class WeightedDigraph
{
	private final int V;
	private int E;
	private LinkedBag<DirectedEdge>[] adj;
	
	@SuppressWarnings("unchecked")
	public WeightedDigraph(int v)
	{
		this.V = v;
		adj = (LinkedBag<DirectedEdge>[])new LinkedBag[V];
		for(int i = 0; i < V; i++)
			adj[i] = new LinkedBag<DirectedEdge>();
	}
	
	public WeightedDigraph(Scanner scanner) throws IOException
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
			double weight = scanner.nextDouble();
			addEdge(v, w, weight);
		}
	}

	public void addEdge(int v, int w, double weight)
	{
		adj[v].add(new DirectedEdge(v, w, weight));
		E++;
	}
	
	public Iterable<DirectedEdge> adj(int v)
	{
		return adj[v];
	}
	
	public Iterable<DirectedEdge> edges()
	{
		LinkedBag<DirectedEdge> bag = new LinkedBag<DirectedEdge>();
		for(int i = 0; i < V; i++)
		{
			for(DirectedEdge edge : adj[i])
				bag.add(edge);
		}
		
		return bag;
	}

	public int getV()
	{
		return V;
	}

	public int getE()
	{
		return E;
	}
}

package chap4.digraph;

import java.util.ArrayList;
import java.util.List;

import chap1.LinkedList;
import chap1.LinkedQueue;

public class KahnTopological
{
	private List<Integer> result;
	private LinkedQueue<Integer> setOfZeroIndegree;
	private int[] indegrees;
	private int edges;
	private Digraph di;
	
	public KahnTopological(Digraph di)
	{
		this.di = di;
		this.edges = di.getE();
		this.indegrees = new int[di.getV()];
		this.result = new ArrayList<Integer>();
		this.setOfZeroIndegree = new LinkedQueue<Integer>();
		
		// Init the set
		Iterable<Integer>[] adjs = di.getAdj();
		for(int i = 0; i < adjs.length; i++)
		{
			// for every v -> w
			for(int w : adjs[i])
			{
				indegrees[w]++;
			}
		}
		
		for(int i = 0; i < indegrees.length; i++)
		{
			if(0 == indegrees[i])
			{
				setOfZeroIndegree.enqueue(i);
			}
		}
		
		process();
	}
	
	private void process()
	{
		while(!setOfZeroIndegree.isEmpty())
		{
			int v = setOfZeroIndegree.dequeue();
			
			// add current vertex to result list
			result.add(v);
			
			// iterate the paths originated from v
			for(int w : di.adj(v))
			{
				// remove an edge from the digraph
				edges--;
				if(0 == --indegrees[w])
				{
					setOfZeroIndegree.enqueue(w);
				}
			}
		}
		
		if(0 != edges)
		{
			throw new IllegalArgumentException("Has Cycle !");
		}
	}
	
	public Iterable<Integer> getResult()
	{
		return result;
	}
	
	public Integer[] getResultAsArray()
	{
		return result.toArray(new Integer[]{});
	}
}

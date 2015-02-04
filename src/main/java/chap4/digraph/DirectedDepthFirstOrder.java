package chap4.digraph;

import chap1.LinkedQueue;
import chap1.LinkedStack;
import chap4.DepthFirstCycleDetection;
import chap4.sp.DirectedEdge;
import chap4.sp.WeightedDigraph;

/**
 * Topological Sorting
 * 
 * @author Destiny
 * 
 */
public class DirectedDepthFirstOrder
{
	private boolean[] visited;
	private LinkedQueue<Integer> pre;
	private LinkedQueue<Integer> post;
	private LinkedStack<Integer> reversePost;

	/**
	 * Topological Sorting Constructor
	 * 
	 * @param di
	 * @param detectCycle
	 *            determine whether to detect cycles, in kosaraju's scc
	 *            algorithm, no detection is needed
	 */
	public DirectedDepthFirstOrder(Digraph di, boolean detectCycle)
	{
		DirectedDepthFirstCycleDetection detect = new DirectedDepthFirstCycleDetection(
				di);

		if (detectCycle && detect.hasCycle())
			throw new IllegalArgumentException("Has cycle");

		this.visited = new boolean[di.getV()];

		this.pre = new LinkedQueue<Integer>();
		this.post = new LinkedQueue<Integer>();
		this.reversePost = new LinkedStack<Integer>();

		for (int i = 0; i < di.getV(); i++)
		{
			if (!visited[i])
			{
				dfs(di, i);
			}
		}
	}

	/**
	 * Used for Acyclic SP problem, in this scenario, cycle detection is a must
	 * procedure
	 * 
	 * @param wdi
	 */
	public DirectedDepthFirstOrder(WeightedDigraph wdi)
	{
		DirectedDepthFirstCycleDetection cycleDetection = new DirectedDepthFirstCycleDetection(
				wdi);

		if (cycleDetection.hasCycle())
		{
			System.out.println(cycleDetection.getCycle());
			throw new IllegalArgumentException("Has cycle");
		}

		this.visited = new boolean[wdi.getV()];

		this.pre = new LinkedQueue<Integer>();
		this.post = new LinkedQueue<Integer>();
		this.reversePost = new LinkedStack<Integer>();

		for (int i = 0; i < wdi.getV(); i++)
		{
			if (!visited[i])
			{
				dfs(wdi, i);
			}
		}
	}

	private void dfs(Digraph di, int v)
	{
		visited[v] = true;
		pre.enqueue(v);

		for (int w : di.adj(v))
		{
			if (!visited[w])
			{
				dfs(di, w);
			}
		}

		post.enqueue(v);
		reversePost.push(v);
	}

	/**
	 * Used for Acyclic SP problem
	 * 
	 * @param wdi
	 * @param v
	 */
	private void dfs(WeightedDigraph wdi, int v)
	{
		visited[v] = true;
		pre.enqueue(v);

		for (DirectedEdge w : wdi.adj(v))
		{
			if (!visited[w.to()])
			{
				dfs(wdi, w.to());
			}
		}

		post.enqueue(v);
		reversePost.push(v);
	}

	public Iterable<Integer> getPre()
	{
		return pre;
	}

	public Iterable<Integer> getPost()
	{
		return post;
	}

	public Iterable<Integer> getReversePost()
	{
		return reversePost;
	}
}

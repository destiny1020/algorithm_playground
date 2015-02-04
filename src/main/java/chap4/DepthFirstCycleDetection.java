package chap4;

/**
 * Judge whether there is a cycle in the graph
 * @author Destiny
 *
 */
public class DepthFirstCycleDetection
{
	private boolean hasCycle;
	private boolean[] visited;
	private int cycleCount;	// Test Aim

	public DepthFirstCycleDetection(Graph g)
	{
		visited = new boolean[g.getV()];

		for (int i = 0; i < g.getV(); i++)
			if (!visited[i])
				dfs(g, i, i);
	}

	private void dfs(Graph g, int v, int u)
	{
		visited[v] = true;

		for (int w : g.adj(v))
		{
			if (!visited[w])
				dfs(g, w, v);
			else if (w != u)
			{
				hasCycle = true;
				cycleCount++;
			}
		}
	}

	public boolean hasCycle()
	{
		return hasCycle;
	}

	public int getCycleCount()
	{
		return cycleCount;
	}
}

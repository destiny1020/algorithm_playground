package chap4;

public class ConnectedComponent
{
	private int numberOfComponents;
	private int[] id;
	private boolean[] visited;
	
	public ConnectedComponent(Graph g)
	{
		id = new int[g.getV()];
		visited = new boolean[g.getV()];
		
		for(int v = 0; v < g.getV(); v++)
			id[v] = -1;
		
		for(int v = 0; v < g.getV(); v++)
		{
			if(!visited[v])
			{
				dfs(g, v);
				numberOfComponents++;
			}
		}
	}
	
	private void dfs(Graph g, int v)
	{
		visited[v] = true;
		id[v] = numberOfComponents;
		
		for(int w : g.adj(v))
		{
			if(!visited[w])
			{
				dfs(g, w);
			}
		}
	}
	
	public int count()
	{
		return numberOfComponents;
	}
	
	public boolean connected(int v, int w)
	{
		return id[v] == id[w];
	}
	
	public int id(int v)
	{
		return id[v];
	}
}

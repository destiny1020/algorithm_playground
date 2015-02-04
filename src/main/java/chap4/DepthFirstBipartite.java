package chap4;

public class DepthFirstBipartite
{
	private boolean isTwoColor = true;
	private boolean[] visited;
	private boolean[] color;
	
	public DepthFirstBipartite(Graph g)
	{
		visited = new boolean[g.getV()];
		color = new boolean[g.getV()];
		
		for(int i = 0; i < g.getV(); i++)
		{
			if(!visited[i])
				dfs(g, i);
		}
	}

	private void dfs(Graph g, int i)
	{
		visited[i] = true;
		for(int w : g.adj(i))
		{
			if(!visited[w])
			{
				color[w] = !color[i];
				dfs(g, w);
			}
			else if(color[w] == color[i])
				isTwoColor = false;
		}
	}
	
	public boolean isTwoColor()
	{
		return isTwoColor;
	}
}

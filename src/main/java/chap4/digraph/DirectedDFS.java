package chap4.digraph;

public class DirectedDFS
{
	private boolean[] marked;
	
	public DirectedDFS(Digraph di, int s)
	{
		this.marked = new boolean[di.getV()];
		
		dfs(di, s);
	}
	
	public DirectedDFS(Digraph di, Iterable<Integer> sources)
	{
		this.marked = new boolean[di.getV()];
		
		for(int s : sources)
		{
			if(!marked[s])
				dfs(di, s);
		}
	}
	
	private void dfs(Digraph di, int s)
	{
		marked[s] = true;
		
		for(int w : di.adj(s))
		{
			if(!marked[w])
				dfs(di, w);
		}
	}

	public boolean marked(int v)
	{
		return marked[v];
	}

	public boolean[] getMarked()
	{
		return marked;
	}
}

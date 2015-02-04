package chap4.digraph;

/**
 * Hamilton Path Detection for DAG
 */
public class DAGHamiltonPath
{
	private boolean hamiltonPathPresent;
	private Digraph di;
	private KahnTopological kts;
	
	public DAGHamiltonPath(Digraph di, KahnTopological kts)
	{
		this.di = di;
		this.kts = kts;
		
		process();
	}
	
	private void process()
	{
		Integer[] topoResult = kts.getResultAsArray();
		
		for(int i = 0; i < topoResult.length - 1; i++)
		{
			if(!hasPath(topoResult[i], topoResult[i + 1]))
			{
				hamiltonPathPresent = false;
				return;
			}
		}
		hamiltonPathPresent = true;
	}
	
	private boolean hasPath(int start, int end)
	{
		for(int w : di.adj(start))
		{
			if(w == end)
			{
				return true;
			}
		}
		return false;
	}

	public boolean hasHamiltonPath()
	{
		return hamiltonPathPresent;
	}
}

package chap1.uf;

/**
 * Quadratic process, because the union method's time complexity is O(N)
 * Suppose there are M pairs and N Components at init time, then this process is O(MN), a quadratic process 
 * @author Destiny
 *
 */
public class UF
{
	private int[] id;
	private int count;
	
	public UF(int N)
	{
		this.id = new int[N];
		this.count = N;
		
		for(int i = 0; i < N; i++)
		{
			id[i] = i;
		}
	}
	
	public int count()
	{
		return this.count;
	}
	
	public boolean connected(int p, int q)
	{
		return (id[p] == id[q]);
	}
	
	public int find(int p)
	{
		return id[p];
	}
	
	public void union(int p, int q)
	{
		int pComponent = this.find(p);
		int qComponent = this.find(q);
		
		if(pComponent == qComponent)
		{
			return;
		}
		
		for(int i = 0; i < this.id.length; i++)
		{
			if(id[i] == pComponent)
			{
				id[i] = qComponent;
			}
		}
		
		this.count--;
	}
}

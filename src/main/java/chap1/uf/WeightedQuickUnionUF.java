package chap1.uf;

/**
 * An improvement for QuickUnionUF by taking tree size into consideration
 * @author Destiny
 *
 */
public class WeightedQuickUnionUF
{
	private int count;
	private int[] id;
	private int[] size;
	
	public WeightedQuickUnionUF(int N)
	{
		this.count = N;
		this.id = new int[N];
		this.size = new int[N];
		
		for(int i = 0; i < this.count; i++)
		{
			id[i] = i;
			size[i] = 1;
		}
	}
	
	public boolean connected(int p, int q)
	{
		return find(p) == find(q);
	}

	private int find(int p)
	{
		while(p != id[p])
			p = id[p];
		
		return p;
	}
	
	public void union(int p, int q)
	{
		int pCom = this.find(p);
		int qCom = this.find(q);
		
		if(size[pCom] > size[qCom])
		{
			// Make qCom a child of pCom
			id[qCom] = pCom;
			size[pCom] += size[qCom];
		}
		else
		{
			// Make pCom a child of qCom
			id[pCom] = qCom;
			size[qCom] += size[pCom];
		}
		
		count--;
	}
	
	public int count()
	{
		return this.count;
	}
}

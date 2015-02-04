package chap1.uf;

/**
 * Quick Union UF
 * @author Destiny
 *
 */
public class QuickUnionUF
{
	private int[] id;
	private int count;
	
	public QuickUnionUF(int N)
	{
		this.count = N;
		this.id = new int[N];
		
		for(int i = 0; i < id.length; i++)
			this.id[i] = i;
	}
	
	public boolean connected(int p, int q)
	{
		return find(p) == find(q);
	}

	private int find(int p)
	{
		// id[p] plays as a parent-link 
		while(p != id[p])
			p = id[p];
		
		// Return the root
		return p;
	}
	
	public void union(int p, int q)
	{
		int pCom = this.find(p);
		int qCom = this.find(q);
		
		//  Make pCom a child of qCom
		id[pCom] = qCom;
		count--;
	}
	
	public int count()
	{
		return this.count;
	}
}

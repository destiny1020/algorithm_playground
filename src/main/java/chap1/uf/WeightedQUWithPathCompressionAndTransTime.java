package chap1.uf;

/**
 * Weighted Quick Union with Path Compression
 * 
 * ADD FUNCTION: FROM HDU-3635 (The transporting times of ith component)
 * 
 * @author Destiny
 * 
 */
public class WeightedQUWithPathCompressionAndTransTime
{
	private int count;
	private int[] id;
	private int[] size;

	// ADD FUNCTION:
	private int[] transTime;

	public WeightedQUWithPathCompressionAndTransTime(int N)
	{
		this.count = N;
		this.id = new int[N];
		this.size = new int[N];

		this.transTime = new int[N];

		for (int i = 0; i < this.count; i++)
		{
			id[i] = i;
			size[i] = 1;
			transTime[i] = 0;
		}
	}

	public boolean connected(int p, int q)
	{
		return find(p) == find(q);
	}

	public int find(int p)
	{
		while (p != id[p])
		{
			if (id[p] != id[id[p]])
			{
				transTime[p] += transTime[id[p]];
				id[p] = id[id[p]];
			}
			p = id[p];
		}
		return p;
	}

	public void union(int p, int q)
	{
		int pCom = this.find(p);
		int qCom = this.find(q);

		if (pCom == qCom)
		{
			return;
		}

		// ADD FUNCTION: UPDATE TRANS TIME
		transTime[pCom]++;

		// NOTICE: NO SIZE JUDGEMENT
		id[pCom] = qCom;
		size[qCom] += size[pCom];

		count--;
	}

	// if the id is not the root node, find the root first
	public int componentSize(int id)
	{
		return size[find(id)];
	}

	public int count()
	{
		return this.count;
	}

	public int getTransTime(int p)
	{
		int totalTrans = transTime[p];
		while (p != id[p])
		{
			totalTrans += transTime[id[p]];
			p = id[p];
		}

		return totalTrans;
	}
}

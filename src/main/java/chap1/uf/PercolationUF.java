package chap1.uf;

import gadget.CustomRandom;

import java.util.Arrays;

import chap1.Stopwatch;

/**
 * Weighted Quick Union with Path Compression - Percolation
 * 
 * @author Destiny
 * 
 */
public class PercolationUF
{
	private int count;
	private int[] id;
	private int[] size;
	private int dimension;
	
	private int vaccant;

	public PercolationUF(int N)
	{
		this.count = N;
		this.id = new int[N];
		this.size = new int[N];
		this.dimension = (int) Math.sqrt(N);

		Arrays.fill(id, -1);
		Arrays.fill(size, 1);

		int last = N - dimension;
		for (int i = 0; i < dimension; i++)
		{
			id[i] = i;
			id[last] = last;
			last++;
		}
		
		last = N - dimension;
		for(int i = 0; i < dimension - 1; i++)
		{
			this.union(i, i + 1);
			this.union(last, last + 1);
			last++;
		}
		
		this.vaccant = dimension * 2;
	}
	
	public void process()
	{
		Stopwatch watch = new Stopwatch();
		while(!this.connect(0, this.id.length - 1))
		{
			this.random();
		}
		double elapsedTime = watch.elapsedTimeInSeconds();
		
		System.out.println("Elapsed Time: " + elapsedTime);
		
		System.out.println("Vaccant: " + this.vaccant);
		System.out.println("Total: " + this.id.length);
		System.out.println("P: " + ((double)this.vaccant / this.id.length));
	}
	
	private void random()
	{
		int index = CustomRandom.uniform(dimension, this.id.length - dimension);
		id[index] = index;
		vaccant++;
		
		if(index % dimension == 0)
		{
			union(index, index + 1);
			union(index, index + dimension);
			union(index, index - dimension);
			
			// 8 directions
			union(index, index - dimension + 1);
			union(index, index + dimension + 1);
		}
		else if((index + 1) % dimension == 0)
		{
			union(index, index - 1);
			union(index, index + dimension);
			union(index, index - dimension);
			
			// 8 directions
			union(index, index - dimension - 1);
			union(index, index + dimension - 1);
		}
		else
		{
			union(index, index + 1);
			union(index, index - 1);
			union(index, index + dimension);
			union(index, index - dimension);
			
			// 8 directions
			union(index, index + dimension);
			union(index, index + dimension + 1);
			union(index, index + dimension - 1);
			union(index, index - dimension);
			union(index, index - dimension + 1);
			union(index, index - dimension - 1);
		}
	}
	
	private boolean connect(int p, int q)
	{
		int pCom = find(p);
		int qCom = find(q);
		
		if(-1 == pCom || -1 == qCom)
			return false;
		
		return pCom == qCom;
	}

	private int find(int p)
	{
		if (-1 == id[p])
			return -1;

		while (p != id[p])
		{
			// Change p's parent to its grandparent
			// One line to implement path compression !
			id[p] = id[id[p]];

			p = id[p];
		}

		return p;
	}

	public void union(int p, int q)
	{
		int pCom = this.find(p);
		int qCom = this.find(q);

		if (-1 == pCom || -1 == qCom)
			return;

		if (size[pCom] > size[qCom])
		{
			// Make qCom a child of pCom
			id[qCom] = pCom;
			size[pCom] += size[qCom];
		} else
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

	public int getCount()
	{
		return count;
	}

	public int[] getId()
	{
		return id;
	}

	public int[] getSize()
	{
		return size;
	}

	public int getDimension()
	{
		return dimension;
	}
}

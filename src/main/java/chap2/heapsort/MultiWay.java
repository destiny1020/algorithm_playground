package chap2.heapsort;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class MultiWay
{
	// One Scanner per data file
	private Scanner[] data;
	private PriorityQueue<PQNode> pq;
	
	private class PQNode implements Comparable<PQNode>
	{
		private int index;
		private String content;
		
		public PQNode(int index, String content)
		{
			this.index = index;
			this.content = content;
		}
		
		public int getIndex()
		{
			return this.index;
		}
		
		public String getContent()
		{
			return this.content;
		}

		@Override
		public int compareTo(PQNode that)
		{
			return this.content.compareTo(that.content);
		}
	}
	
	/**
	 * Constructor
	 * @param dataPaths Paths of data file
	 * @throws FileNotFoundException
	 */
	public MultiWay(String[] dataPaths) throws FileNotFoundException
	{
		this.data = new Scanner[dataPaths.length];
		for(int i = 0; i < dataPaths.length; i++)
		{
			data[i] = new Scanner(new File(dataPaths[i]));
		}
		this.pq = new PriorityQueue<PQNode>();
	}
	
	public void process()
	{
		// add one data item from every scanner, these items are organized by priority heap
		for(int i = 0; i < data.length; i++)
		{
			if(data[i].hasNext())
			{
				pq.add(new PQNode(i, data[i].next()));
			}
		}
		
		while(!this.isExhausted())
		{
			PQNode node = pq.poll();
			if(null != node)
			{
				int index = node.getIndex();
				System.out.print(node.getContent() + " ");
				if(this.data[index].hasNext())
				{
					pq.add(new PQNode(index, this.data[index].next()));
				}
			}
		}
		
		while(!this.pq.isEmpty())
		{
			System.out.print(this.pq.poll().getContent());
		}
	}
	
	private boolean isExhausted()
	{
		for(Scanner scanner : this.data)
		{
			if(scanner.hasNext())
			{
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) throws FileNotFoundException
	{
		String[] paths = new String[]{"data/1.txt", "data/2.txt", "data/3.txt"};
		MultiWay mw = new MultiWay(paths);
		mw.process();
	}
}

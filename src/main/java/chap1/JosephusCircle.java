package chap1;

public class JosephusCircle
{
	private int N;
	private int skip;
	
	private LinkedQueue<Integer> queue;
	
	public JosephusCircle(int N, int skip)
	{
		this.N = N;
		this.skip = skip;
		
		this.init();
	}

	private void init()
	{
		this.queue = new LinkedQueue<Integer>();
		
		for(int i = 0; i < this.N; i++)
		{
			this.queue.enqueue(i + 1);
		}
	}
	
	public void process()
	{
		while(!this.queue.isEmpty())
		{
			for(int i = 1; i < this.skip; i++)
			{
				this.queue.enqueue(this.queue.dequeue());
			}
			System.out.print(this.queue.dequeue() + " ");
		}
	}

	
}

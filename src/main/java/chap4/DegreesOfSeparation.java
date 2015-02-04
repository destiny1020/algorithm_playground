package chap4;

public class DegreesOfSeparation
{
	private SymbolGraph sg;
	private BreadthFirstPaths bfp;
	
	public DegreesOfSeparation(SymbolGraph sg)
	{
		this.sg = sg;
		bfp = new BreadthFirstPaths(sg.g(), sg.index("Bacon, Kevin"));
	}
	
	public void process(String target)
	{
		int indexTarget = sg.index(target);
		if(-1 == indexTarget)
		{
			System.out.println("No such star ...");
			return;
		}
		
		if(bfp.connected(indexTarget))
		{
			for(int w : bfp.pathTo(indexTarget))
			{
				System.out.println(sg.name(w));
			}
		}
	}
}

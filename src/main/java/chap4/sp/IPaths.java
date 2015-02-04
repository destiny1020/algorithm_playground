package chap4.sp;

public interface IPaths
{
	double distTo(int dest);

	boolean hasPathsTo(int dest);

	Iterable<DirectedEdge> pathsTo(int dest);
}

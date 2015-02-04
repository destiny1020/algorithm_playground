package chap4;

public interface Searchable
{
	void setSource(int source);

	boolean marked(int dest);

	int count();
}

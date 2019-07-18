package eg.edu.alexu.csd.filestructure.graphs;

public class Edge implements IEdge{
private int source;
private int destination;
private int weight;
public Edge(int source, int destination, int weight) {
	this.source = source;
	this.destination = destination;
	this.weight = weight;
}
	@Override
	public int getSourceVertix() {
		// TODO Auto-generated method stub
		return source;
	}

	@Override
	public int getDestinationVertix() {
		// TODO Auto-generated method stub
		return destination;
	}

	@Override
	public int getWeight() {
		// TODO Auto-generated method stub
		return weight;
	}

}

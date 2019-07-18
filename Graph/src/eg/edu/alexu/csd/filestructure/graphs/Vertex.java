package eg.edu.alexu.csd.filestructure.graphs;

import java.util.ArrayList;

public class Vertex implements Comparable <Vertex>{
	private int index;
	private int distance;
	private ArrayList<IEdge> adjList;
	public Vertex(int index) {
		this.index = index;
		adjList = new ArrayList<IEdge>();
	}


	public int getIndex() {
		// TODO Auto-generated method stub
		return index;
	}

	public int getDistanceFromSrc() {
		// TODO Auto-generated method stub
		return distance;
	}

	public ArrayList<IEdge> getAdjList() {
		// TODO Auto-generated method stub
		return adjList;
	}


	public void setDistanceFromSrc(int d) {
		distance = d;
	}

	public void addNeighbor(IEdge e) {
		// TODO Auto-generated method stub
		adjList.add(e);
	}


	@Override
	public int compareTo(Vertex o) {
		// TODO Auto-generated method stub
		return distance < o.getDistanceFromSrc() ? -1
		         : distance > o.getDistanceFromSrc() ? 1
		                 : 0;
	}

}

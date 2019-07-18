package eg.edu.alexu.csd.filestructure.graphs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;


public class Graph implements IGraph{
	
private int verticesSize;
private int edgesSize;
private Vertex[] graph;
private ArrayList<Integer> orderedProcessedVertices;
	@Override
	public void readGraph(File file) {
		// TODO Auto-generated method stub
		BufferedReader reader = null;
		int i = 1;
		
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e1) {
		}
		String line1 = null;
		try {
			line1 = reader.readLine();
		} catch (IOException e1) {
		}
		String[] splited1 = line1.split("\\s+");
		verticesSize = Integer.parseInt(splited1[0]);
		edgesSize = Integer.parseInt(splited1[1]);			
		graph = new Vertex[verticesSize];
		for (int k = 0; k < verticesSize; k++) {
			if (graph[k] == null) {
				graph[k] = new Vertex(k);
			}
		}
		try {
			String line = reader.readLine();
			while (i <= edgesSize) {
				String[] splited = line.split("\\s+");
				IEdge e = new Edge(Integer.parseInt(splited[0]), Integer.parseInt(splited[1]), Integer.parseInt(splited[2]));
				graph[Integer.parseInt(splited[0])].addNeighbor(e);	
				i++;
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
		}
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return edgesSize;
	}

	@Override
	public ArrayList<Integer> getVertices() {
		// TODO Auto-generated method stub
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for (int i = 0; i < verticesSize; i++) {
			arr.add(i);
		}
		return arr;
	}

	@Override
	public ArrayList<Integer> getNeighbors(int v) {
		// TODO Auto-generated method stub
		ArrayList<IEdge> arr = graph[v].getAdjList();
		ArrayList<Integer> arrR = new ArrayList<Integer>();
		for (int i = 0; i < arr.size(); i++) {
			arrR.add(arr.get(i).getDestinationVertix());
		}
		return arrR;
	}

	@Override
	public void runDijkstra(int src, int[] distances) {
		// TODO Auto-generated method stub
		orderedProcessedVertices = new ArrayList<Integer>(verticesSize);
		PriorityQueue<Vertex> pQueue = new PriorityQueue<>();
		//distances = new int[verticesSize];
			initial(src);
			for(int i = 0; i < verticesSize; i++) {
				distances[i] = graph[i].getDistanceFromSrc();
				pQueue.add(graph[i]);
			}
			while(!pQueue.isEmpty()) {
				Vertex u = pQueue.poll();
				orderedProcessedVertices.add(u.getIndex());
				for(int i = 0; i < u.getAdjList().size(); i++) {
					Vertex v = graph[u.getAdjList().get(i).getDestinationVertix()];
					if((u.getDistanceFromSrc() + u.getAdjList().get(i).getWeight()) < v.getDistanceFromSrc()) {
						pQueue.remove(v);
						int d = u.getDistanceFromSrc() + u.getAdjList().get(i).getWeight();
						v.setDistanceFromSrc(d);
						distances[v.getIndex()] = d; 
						pQueue.add(v);
					}
				
			}
		}
			
	}

	@Override
	public ArrayList<Integer> getDijkstraProcessedOrder() {
		// TODO Auto-generated method stub
		return orderedProcessedVertices;
	}
	@Override
	public boolean runBellmanFord(int src, int[] distances) {
		// TODO Auto-generated method stub
		initial(src);
		for (int i = 0; i < verticesSize; i++) {
			for (int j = 0; j < graph.length; j++) {
				ArrayList<IEdge> adj = graph[j].getAdjList();
				for (int k = 0; k < adj.size();k++) {
					relax(adj.get(k).getSourceVertix(), adj.get(k).getDestinationVertix(), adj.get(k).getWeight());
				}
			}
		}
		for (int i = 0; i < verticesSize; i++) {
			distances[i] = graph[i].getDistanceFromSrc();
		}
		for (int j = 0; j < graph.length; j++) {
			ArrayList<IEdge> adj = graph[j].getAdjList();
			for (int k = 0; k < adj.size();k++) {
				if (adj.get(k).getDestinationVertix()> (adj.get(k).getSourceVertix() + adj.get(k).getWeight())) {
					return false;
				}
			}
		}
		return true;
	}
	
	private void initial(int src) {
		for (int i = 0; i < verticesSize; i++) {
			if (graph[i] == null) {
				graph[i] = new Vertex(i);
			}
			graph[i].setDistanceFromSrc(Integer.MAX_VALUE / 2);
		}
		graph[src].setDistanceFromSrc(0);
	}
	
	private void relax(int u, int v, int w) {
		if (graph[v].getDistanceFromSrc() > (graph[u].getDistanceFromSrc() + w)) {
			graph[v].setDistanceFromSrc(graph[u].getDistanceFromSrc() + w);
		}
	}
}

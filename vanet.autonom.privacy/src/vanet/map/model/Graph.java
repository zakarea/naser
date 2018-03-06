package vanet.map.model;

import java.util.ArrayList;
import java.util.List;

/**
* Graph (directed/undirected graphs)
* @author Zakarea AL SHARA
*
*/

public class Graph {
	private final List<Vertex> vertexes;
	private final List<Edge> edges;

	public Graph(List<Vertex> vertexes, List<Edge> edges) {
		this.vertexes = vertexes;
		this.edges = edges;
		findFanOut();
	}

	public List<Vertex> getVertexes() {
		return vertexes;
	}

	public List<Edge> getEdges() {
		return edges;
	}
	
	//find edges that connect a vertix with its neighbors
	private void findFanOut() {
		for(Vertex vertex : vertexes)
		{
			List<Edge> fanOut = new ArrayList<Edge>();
			for (Edge edge : edges) {
				if (edge.getSource().equals(vertex)) {
					fanOut.add(edge);
				}
			}
			vertex.setFanOut(fanOut);
		}
		
	}
	
}

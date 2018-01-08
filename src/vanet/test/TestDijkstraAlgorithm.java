package vanet.test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import vanet.map.model.Edge;
import vanet.map.model.Graph;
import vanet.map.model.Map;
import vanet.map.model.Path;
import vanet.map.model.Vertex;
import vanet.rout.algorithms.DijkstraAlgorithm;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
* Test_1
* @author Zakarea AL SHARA
*
*/

public class TestDijkstraAlgorithm {

	private List<Vertex> nodes;
	private List<Edge> edges;

	@Test
	public void testExcute() {
	
		nodes = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();
		for (int i = 0; i < 11; i++) {
			Vertex location = new Vertex("Node_" + i, "Node_" + i);
			nodes.add(location);
		}

		//go
		addLane("Edge_0.1", 0, 1, 85);
		addLane("Edge_1.1", 0, 2, 217);
		addLane("Edge_2.1", 0, 4, 173);
		addLane("Edge_3.1", 2, 6, 186);
		addLane("Edge_4.1", 2, 7, 103);
		addLane("Edge_5.1", 3, 7, 183);
		addLane("Edge_6.1", 5, 8, 250);
		addLane("Edge_7.1", 8, 9, 84);
		addLane("Edge_8.1", 7, 9, 167);
		addLane("Edge_9.1", 4, 9, 502);
		addLane("Edge_10.1", 9, 10, 40);
		addLane("Edge_11.1", 1, 10, 443);
		
		//back
		addLane("Edge_0.2", 1, 0, 85);
		addLane("Edge_1.2", 2, 0, 217);
		addLane("Edge_2.2", 4, 0, 173);
		addLane("Edge_3.2", 6, 2, 186);
		addLane("Edge_4.2", 7, 2, 103);
		addLane("Edge_5.2", 7, 3, 183);
		addLane("Edge_6.2", 8, 5, 250);
		addLane("Edge_7.2", 9, 8, 84);
		addLane("Edge_8.2", 9, 7, 167);
		addLane("Edge_9.2", 9, 4, 502);
		addLane("Edge_10.2", 10, 9, 40);
		addLane("Edge_11.2", 10, 1, 443);
		

		// Lets check from location Loc_1 to Loc_10
		Graph graph = new Graph(nodes, edges);
		DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
		dijkstra.execute(nodes.get(0));
		Path path = dijkstra.getPath(nodes.get(10));
		
		assertNotNull(path);
		assertTrue(path.getAll().size() > 0);
		
		for (Vertex vertex : path.getAll()) {
			System.out.println(vertex);
		}
		
	}

	private void addLane(String laneId, int sourceLocNo, int destLocNo,
			int duration) {
		Edge lane = new Edge(laneId,nodes.get(sourceLocNo), nodes.get(destLocNo), duration );
		edges.add(lane);
	}
}

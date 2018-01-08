package vanet.test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import vanet.map.model.Cross;
import vanet.map.model.Map;
import vanet.map.model.Path;
import vanet.map.model.Road;
import vanet.map.model.Route;
import vanet.map.model.Vertex;
import vanet.rout.algorithms.DijkstraAlgorithm;
import vanet.rout.algorithms.ShortestPath;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
* Test_1
* @author Zakarea AL SHARA
*
*/

public class TestDijkstraAlgorithm2 {

	private List<Cross> crosses;
	private List<Road> roads;

	@Test
	public void testExcute() {
	
		crosses = new ArrayList<Cross>();
		roads = new ArrayList<Road>();
		for (int i = 0; i < 11; i++) {
			Cross location = new Cross("Node_" + i, "Node_" + i);
			crosses.add(location);
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
		Map map = new Map(crosses, roads);
		ShortestPath shortest = new ShortestPath(map);
		shortest.execute(crosses.get(0));
		Route route = shortest.getRoute(crosses.get(10));
		
		assertNotNull(route);
		assertTrue(route.getAllRoute().size() > 0);
		System.out.println("Warning: Change path to rout");
		for (Vertex vertex : route.getAll()) {
			System.out.println(vertex);
		}
		
	}

	private void addLane(String laneId, int sourceLocNo, int destLocNo,
			int duration) {
		Road lane = new Road(laneId,crosses.get(sourceLocNo), crosses.get(destLocNo), duration );
		roads.add(lane);
	}
}

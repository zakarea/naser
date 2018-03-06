package vanet.rout.algorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import vanet.map.model.Cross;
import vanet.map.model.Graph;
import vanet.map.model.Map;
import vanet.map.model.Path;
import vanet.map.model.Route;
import vanet.map.model.Vertex;

public class ShortestPath extends DijkstraAlgorithm{

	public ShortestPath(Map map) {
		super(map);
	}
	

	public Route getRoute(Cross target) {
		Path shortest = super.getPath(target);
		if(shortest == null){
			try {
				throw new Exception("There is no route found from current position to " + target.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		List<Cross> routeList = (List<Cross>) (List<?>) shortest.getAll();
		Route route = new Route((ArrayList<Cross>) routeList);
		
//		Route route = new Route();
//		for(Vertex vertix : super.getPath(target).getAll()){
//			Cross cross = (Cross) vertix;
//			route.add(cross);
//		}
		
		return route;
	}

}

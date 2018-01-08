package vanet.vehicle.model;

import java.util.LinkedList;

import vanet.map.model.Cross;
import vanet.map.model.Map;
import vanet.map.model.Route;


/**
* Mobile: un abstract class for all mobile entity
* @author Zakarea AL SHARA
*
*/

public abstract class Mobile {
	
	static int next_id = 0;
	static int next_fake_id = 0;
	int id;
	int fake_id;
//	Map map;
	Cross source;
	Cross target;
	Route routeHistory = new Route();
	
	public abstract boolean move(Map map);
	
	public void setSource(Cross source) {
		this.source = source;
	}
	
	public Cross getSource() {
		return source;
	}
	
	public void setTarget(Cross target) {
		this.target = target;
	}
	
	public Cross getTarget() {
		return target;
	}
	
	public int getId() {
		return id;
	}
	
	public int generateFakeId() {
		return fake_id++;
	}
	
	public int getFakeId() {
		return fake_id;
	}
	
	public Route getRouteHistory() {
		return routeHistory;
	}

}

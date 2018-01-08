package vanet.map.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Route extends Path{
	private List<Cross> route;

	public Route() {
		route = new LinkedList<Cross>();
	}
	
	public Route(ArrayList<Cross> route) {
		super((ArrayList<Vertex>) (ArrayList<?>) route);
		this.route = route;
	}
	
	
	public void add(Cross cross) {
		super.add(cross);
	}
	
	public List<Cross> getAllRoute(){
		return route;
	}
	
	@Override
	public String toString() {
		
		if(route.size() == 0){
			return "Route= {}";
		}
		
		String ret = new String();
		ret += "Route= {";
		for(int i = 0; i < route.size(); i++){
			ret += route.get(i).id;
			if(i == route.size()-1){
				ret += "}";
			}else{
				ret += ",";
			}
		}
		return ret;
	}

}

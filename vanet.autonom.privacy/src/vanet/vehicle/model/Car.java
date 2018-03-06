package vanet.vehicle.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import vanet.map.model.Component;
import vanet.map.model.Cross;
import vanet.map.model.Map;
import vanet.map.model.Route;
import vanet.rout.algorithms.ShortestPath;

/**
* Car
* @author Zakarea AL SHARA
*
*/

public class Car extends Mobile{

	Stack<Cross> destinations = new Stack<Cross>();
	List<Integer> fake_id = new ArrayList<Integer>();
	Component currentPosition;
	Stack<Cross> currentRoute = new Stack<Cross>();
	
//	long time = 0;
	
	public Car(LinkedList<Cross> destinations) {
		if(destinations.size() < 2){
			try {
				throw new IOException();
			} catch (IOException e) {
				System.err.println("You should provide at least tow destinations");
				e.printStackTrace();
			}
		}
		id = next_id++;
		fake_id.add(generateFakeId());
		Collections.reverse(destinations);
		this.destinations.addAll(destinations);
		currentPosition = this.destinations.pop();
		setSource((Cross) currentPosition);
		setTarget(this.destinations.pop());
		this.destinations.trimToSize();
		
	}
	
	//todo see how to get edges between two vertixes
	//return true if the care reach a xxxxxx
	public boolean move(Map map){
//		Cross target = getTarget();
		if(currentPosition.equals(getTarget())){
			if(destinations.isEmpty()){//finish: pass all destinations
//				System.out.println(this.getClass().getName() + "[" + id + "] hase reach final destination.");
				return true;
			}
			else{//get next target
				setSource((Cross) currentPosition);
				setTarget(destinations.pop());
				destinations.trimToSize();
			}	
		}		
		
		
		//get route
		if(currentPosition.equals(getSource())){
			ShortestPath shortest = new ShortestPath(map);
			shortest.execute(getSource());
			Route shortRoute = shortest.getRoute(getTarget());
			Collections.reverse(shortRoute.getAllRoute());
			currentRoute.addAll(shortRoute.getAllRoute());
			currentRoute.pop();//remove current position
			if(routeHistory.getAll().size() == 0){//Add the first source
				routeHistory.add((Cross) currentPosition);
			}
		}
		
		
		//next move
		currentPosition = currentRoute.pop();
		routeHistory.add((Cross) currentPosition);
		//check mix zone
		if(((Cross)currentPosition).isMixZone()){
			fake_id.add(generateFakeId());
		}
		
		 
		return false;
	}
	
	
	@Override
	public String toString() {
		String ret = new String();
		ret += "Car[ID=" + id + "] -> (F_ID" + fake_id.toString() + ")";
		ret += "\n";
		ret += "Source= " + source;
		ret += "Target= " + target;
		ret += getRouteHistory();
//		ret += "\n";
		return ret;
	}
	
}

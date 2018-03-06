package vanet.map.model;

/**
* Road
* @author Zakarea AL SHARA
*
*/

public class Road extends Edge{
	
	private double distance;
	private double maxSpeed;

	//Take into account the max speed of the road
	public Road(String id, Cross source, Cross destination, double distance, double maxSpeed) {
		super(id, source, destination, distance / maxSpeed);
		
		this.distance = distance;
		this.maxSpeed = maxSpeed;
	}
	
	//Without take into account the max speed of the road
	public Road(String id, Vertex source, Vertex destination, double distance) {
		super(id, source, destination, distance);
		
		this.distance = distance;
	}

	
	public double getMaxSpeed() {
		return maxSpeed;
	}
	
	public double getDistance() {
		return distance;
	}
	
	@Override
	public String toString() {
		String ret = new String();
		ret += "Road[" + id + "](" + distance + ", " + maxSpeed + ")";
		ret += "{" + source.id + "->" + destination.getId() + "}";
		ret += "\n";
		return ret;
	}
	
}

package vanet.map.model;

/**
 * Edge
 * @author Zakarea AL SHARA
 *
 */

public class Edge implements Component{
	final String id; 
	final Vertex source;
	final Vertex destination;
	final double weight; 
	
	public Edge(String id, Vertex source, Vertex destination, double weight) {
		this.id = id;
		this.source = source;
		this.destination = destination;
		this.weight = weight;
	}
	
	public String getId() {
		return id;
	}
	public Vertex getDestination() {
		return destination;
	}

	public Vertex getSource() {
		return source;
	}
	public double getWeight() {
		return weight;
	}
	
	@Override
	public String toString() {
		return source + " " + destination;
	}
	
	
}

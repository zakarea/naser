package vanet.map.model;

import java.util.ArrayList;
import java.util.List;

/**
* Vertex
* @author Zakarea AL SHARA
*
*/

public class Vertex implements Component{
	final String id;
	final String name;
	private List<Edge> fanOut;
	
	
	public Vertex(String id, String name) {
		this.id = id;
		this.name = name;
	}
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public void setFanOut(List<Edge> fanOut) {
		this.fanOut = fanOut;
	}
	
	public List<Edge> getFanOut() {
		return fanOut;
	}
	
	//returns neighbors vertixes
	public List<Vertex> getNeighbors(){
		List<Vertex> neighbors = new ArrayList<Vertex>();
		for(Edge fanOut : this.fanOut){
			neighbors.add(fanOut.getDestination());
		}
		return neighbors;
	}

	@Override
	public String toString() {
		return name;
	}
	
}

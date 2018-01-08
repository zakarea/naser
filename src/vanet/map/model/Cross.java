package vanet.map.model;

/**
* Cross
* @author Zakarea AL SHARA
*
*/

public class Cross extends Vertex{
	
	private boolean mixZone = false;
	private double pause = 0.0; //pause time

	public Cross(String id, String name) {
		super(id, name);
	}
	
	public Cross(String id, String name, boolean mixZone) {
		super(id, name);
		this.mixZone = mixZone;
	}

	public boolean isMixZone() {
		return mixZone;
	}
	
	@Override
	public String toString() {
		String ret = new String();
		ret += "Cross[" + id + "](" + name + ", " + mixZone + ")";
		ret += "\n";
		return ret;
	}
}

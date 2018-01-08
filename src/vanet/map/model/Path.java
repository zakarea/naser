package vanet.map.model;

import java.util.ArrayList;
import java.util.List;

/**
* Path
* @author Zakarea AL SHARA
*
*/

public class Path {
	
	private List<Vertex> path;
	
	public Path() {
		path = new ArrayList<Vertex>();
	}
	
	public Path(ArrayList<Vertex> path) {
		this.path = path;
	}
	
	public void add(Vertex vertex){
		path.add(vertex);
	}
	
	public List<Vertex> getAll(){
		return path;
	}
	
	@Override
	public String toString() {
		String ret = new String();
		ret += "Path= {";
		for(int i = 0; i < path.size(); i++){
			ret += path.get(i).id;
			if(i == path.size()-1){
				ret += "}";
			}else{
				ret += ",";
			}
		}

		return ret;
	}
}

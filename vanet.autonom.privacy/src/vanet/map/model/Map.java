package vanet.map.model;

import java.util.List;

public class Map extends Graph{
	
	private final List<Cross> cross;
	private final List<Road> road;

	@SuppressWarnings("unchecked")
	public Map(List<Cross> cross, List<Road> road) {
		super((List<Vertex>) (List<?>) cross, (List<Edge>) (List<?>) road);
		this.cross = cross;
		this.road = road;
	} 

}

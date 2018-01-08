package vanet.rout.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import vanet.map.model.Cross;
import vanet.map.model.Map;
import vanet.map.model.Route;
import vanet.vehicle.model.Mobile;

public class Tracker {
	private List<Mobile> suspiciousMobiles; //list of mobiles that was occurred in the same  mix zone at the same time
	private List<Integer> preFakeId; //fake ids before entering mix zone
	private List<Integer> postFakeId; //fake ids after entering mix zone
	List<Integer> preMixZonesIdx; //list of previous mix zones index in $mobile.getRouteHistory()
	private Cross mixZone;
	private Long time; //time stamp
	private Map map;
	
	/*
	 * probabilityMatrix[preFakeIds][postFakeIds]
	 * -1 means not match
	 *  0 means unknown
	 * +1 means match
	 */
	byte[][] probabilityMatrix;
	
	private static final byte NOT_MATCH = -1;
	private static final byte UNKNOWN = 0;
	private static final byte MATCH = 1;
	
	
	public Tracker(Map map, List<Mobile> suspiciousMobiles, List<Integer> preFakeId, List<Integer> postFakeId, Cross mixZone, Long time) {
		this.map = map;
		this.suspiciousMobiles = suspiciousMobiles;
		this.preFakeId = preFakeId;
		this.postFakeId = postFakeId;
		this.mixZone = mixZone;
		this.time = time;
		probabilityMatrix = new byte[postFakeId.size()][postFakeId.size()];
	}
	
	public void shortTirm(){
		//First: define the previous mix zones (or the init source if mix zone not found)
		if(preMixZonesIdx == null){
			preMixZonesIdx = new ArrayList<Integer>();
			for(int i = 0; i < suspiciousMobiles.size(); i++){
				Mobile mobile = suspiciousMobiles.get(i);
				for(int idx = mobile.getRouteHistory().getAllRoute().size()-1; idx >= 0; idx--){
					Cross cross = mobile.getRouteHistory().getAllRoute().get(idx);
					if(cross.isMixZone() || i == 0){
						preMixZonesIdx.add(idx);
						break;
					}
				}
			}
		}
		
		//Second: compare the shortest path with actual move
		for(int i = 0; i < suspiciousMobiles.size(); i++){
			Mobile first = suspiciousMobiles.get(i);
			for(int j = i; j < suspiciousMobiles.size(); j++){
				//if it is match from previous check (at check(t-k); t = current time, k < t)
				if(probabilityMatrix[i][j] == MATCH){
					break;
				}
				//if it is not match from previous check (at check(t-k); t = current time, k < t)
				else if(probabilityMatrix[i][j] == NOT_MATCH){
					continue;
				}
				//if it is still unknown from previous check (at check(t-k); t = current time, k < t)
				Mobile second = suspiciousMobiles.get(j);
				//check matching
				byte match = check(first, second, preMixZonesIdx.get(i));
				probabilityMatrix[i][j] = match;
				
			}
		}
		
		//Third: normalize matrix to reduce possibilities
		normalization();
	}
	
	/*
	 * -1 means not match
	 *  0 means unknown
	 * +1 means match
	 */
	private byte check(Mobile first, Mobile second, int preMixZonesIdx){
		ShortestPath shortest = new ShortestPath(map);
		shortest.execute(first.getRouteHistory().getAllRoute().get(preMixZonesIdx));
		Route shortestPath = shortest.getRoute(second.getRouteHistory().getAllRoute().get(second.getRouteHistory().getAllRoute().size()));
		
		Route actualPath = second.getRouteHistory();
		
		for(int idx = actualPath.getAll().size() - 1; idx >= preMixZonesIdx; idx--){
			if(shortestPath.getAllRoute().get(idx).getId() != actualPath.getAllRoute().get(idx).getId()){
				return NOT_MATCH;
			}
		}
		return UNKNOWN;
	}
	
	private void normalization(){
		boolean isChanged = false;
		//normalize matrix
		for(int row = 0; row < probabilityMatrix.length; row++){ 
			for(int col = 0; col < probabilityMatrix[0].length; col++){
				//if MATCH found then reflect intersect  row|col to UNMATCH
				if(probabilityMatrix[row][col] == MATCH){ // error i have to make sue that is the only one in row | col that have value 1
					for(int i = 0; i < probabilityMatrix.length; i++){
						probabilityMatrix[i][col] = NOT_MATCH;
						probabilityMatrix[row][i] = NOT_MATCH;
						normalization(); //check when shoud call
						break;
					}
				}
			}
		}
		
		//if just on zero in row
		for(int row = 0; row < probabilityMatrix.length; row++){
			int sum = 0;
			for(int col = 0; col < probabilityMatrix[0].length; col++){
				sum += probabilityMatrix[row][col];
			}
			if(sum == (probabilityMatrix.length - 1)*(-1)){
				for(int col = 0; col < probabilityMatrix[0].length; col++){
					if(probabilityMatrix[row][col] == UNKNOWN){
						probabilityMatrix[row][col] = MATCH;
						normalization();
						break;
					}
				}
			}
		}
		
		//supose method
		
	}

}
















package vanet.sim;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import vanet.map.model.Map;
import vanet.sim.conf.Configuration;
import vanet.vehicle.model.Mobile;

/**
 * Simulator
 * @author Zakarea AL SHARA
 *
 */

public class Simulator {

//	long tick = 0;
	long simTime = 0;
	Map map;
	List<Mobile> mobiles;
	Configuration configuration = new Configuration();
	
	public Simulator(Long simTime) {
		this.simTime = simTime;
	}
	
	public void generateMap(String xmlPath){
		try {
			map = configuration.parserMap(xmlPath);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void generateMap(Map map){
		this.map = map;
	}
	
	public void generateMobiles(String xmlPath){
		try {
			mobiles = configuration.parserVehicle(xmlPath);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void generateMobiles(List<Mobile> mobiles){
		this.mobiles = mobiles;
	}
	
	public void execute(){
		//simulation loop
		for(int tick = 1; tick <= simTime; tick++)
		{
			//vehicles at mix zones
			List<Mobile> vehiclesAtMixZone = new ArrayList<Mobile>();
			for(Mobile vehicle : mobiles){
				vehicle.move(map);
				//check if this vehicle at mix zone
//				if(vehicle.getc)
					//if it is just alone :)
			}
		}
		
	}
	
	private void printRouteHistory(){
		for(Mobile vehicle : mobiles){
			System.out.println(vehicle.getRouteHistory().toString());
			
		}
	}
	
	public static void main(String[] args) {
		
		Simulator sim = new Simulator(1000L);
		sim.generateMap("/Zakarea/CoMe4ACloud/workspace_come4acloud/xaas_local/vanet.autonom.privacy/src/vanet/sim/conf/map.xml");
		sim.generateMobiles("/Zakarea/CoMe4ACloud/workspace_come4acloud/xaas_local/vanet.autonom.privacy/src/vanet/sim/conf/car.xml");
		sim.execute();
		
		sim.printRouteHistory();
		
	}
	
}

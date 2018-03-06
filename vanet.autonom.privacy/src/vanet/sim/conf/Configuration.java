package vanet.sim.conf;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import vanet.map.model.Cross;
import vanet.map.model.Map;
import vanet.map.model.Road;
import vanet.vehicle.model.Car;
import vanet.vehicle.model.Mobile;

/**
 * Configuration
 * Parse XML files
 * @author Zakarea AL SHARA
 *
 */

public class Configuration {
	
	HashMap<String, Cross> crosses;
	
	//pars XML configuration files
	public Map parserMap(String pathXML)throws ParserConfigurationException, IOException, SAXException {
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    factory.setValidating(true);
	    factory.setIgnoringElementContentWhitespace(true);
	    DocumentBuilder builder = factory.newDocumentBuilder();
	    File file = new File(pathXML);
	    Document doc = builder.parse(file);
	   
	    //Parse Nodes
//	    List<Cross> crosses = new ArrayList<Cross>();
	    crosses = new HashMap<String, Cross>();
	    NodeList crossNodes = doc.getElementsByTagName("node");
	    for(int i = 0; i < crossNodes.getLength(); i++){
	    	String id = crossNodes.item(i).getAttributes().getNamedItem("id").getNodeValue();
	    	String name = crossNodes.item(i).getAttributes().getNamedItem("name").getNodeValue();
	    	String mixZone = crossNodes.item(i).getAttributes().getNamedItem("mixZone").getNodeValue();
	    	Cross cross = new Cross(id, name, Boolean.parseBoolean(mixZone));
	    	crosses.put(id, cross);
//	    	System.out.println("crosses[" + id + ", " + name + ", " + mixZone + "]");
	    }
	    
	  //Parse Edges
	    List<Road> roades = new ArrayList<Road>();
	    NodeList roadNodes = doc.getElementsByTagName("edge");
	    for(int i = 0; i < roadNodes.getLength(); i++){
	    	String id = roadNodes.item(i).getAttributes().getNamedItem("id").getNodeValue();
	    	String source = roadNodes.item(i).getAttributes().getNamedItem("source").getNodeValue();
	    	String target = roadNodes.item(i).getAttributes().getNamedItem("target").getNodeValue();
	    	String distance = roadNodes.item(i).getAttributes().getNamedItem("distance").getNodeValue();
	    	String maxSpeed = roadNodes.item(i).getAttributes().getNamedItem("maxSpeed").getNodeValue();
	    	Road road = new Road(id, crosses.get(source), crosses.get(target), Double.parseDouble(distance), Double.parseDouble(maxSpeed));
	    	roades.add(road);
//	    	System.out.println("Roades[" + id + ", " + source + ", " + target + ", " + distance + ", " + maxSpeed + "]");
	    }
	    
	    //create map
	    Map map = new Map(new ArrayList<Cross>(crosses.values()), roades);
		return map;
	}
	
	
	//pars XML configuration files
		public List<Mobile> parserVehicle(String pathXML)throws ParserConfigurationException, IOException, SAXException {
		    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		    factory.setValidating(true);
		    factory.setIgnoringElementContentWhitespace(true);
		    DocumentBuilder builder = factory.newDocumentBuilder();
		    File file = new File(pathXML);
		    Document doc = builder.parse(file);
		   
		    //Parse Nodes
//		    List<Cross> crosses = new ArrayList<Cross>();
		    List<Mobile> vehicles = new ArrayList<Mobile>();
		    NodeList vehicleNodes = doc.getElementsByTagName("mobile");
		    for(int i = 0; i < vehicleNodes.getLength(); i++){
		    	String path = vehicleNodes.item(i).getAttributes().getNamedItem("path").getNodeValue();
		    	LinkedList<Cross> destinations = getDestinations(path);
		    	Car car = new Car(destinations);
		    	vehicles.add(car);
		    }
		    
		    return vehicles;
		}
		
		private LinkedList<Cross> getDestinations(String path){
			LinkedList<Cross> destinations = new LinkedList<Cross>();
			path = path.replaceAll("\\s","");//remove spaces
			String crossesId[] = path.split(",");
			for(String id : crossesId){
				if(crosses.get(id) == null){
					try {
						throw new Exception("Node[" + id + "] does not exist!");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				destinations.add(crosses.get(id));
			}
			return destinations;
		}

}

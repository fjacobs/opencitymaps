package com.dynacore.testing123.controller;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.dynacore.opencity.entity.jsonrepresentations.ParkeerLocatieTop;
import com.dynacore.opencity.entity.jsonrepresentations.ParkeerLocaties;
import com.dynacore.testing123.entity.PR;
import com.dynacore.testing123.entity.Parking;

/*
 * type:
 * Fietspunt
 * P+R
 * Parkeergarage
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@Controller
public class ParkingLocationController {
		
	List<Parking> parkingLocationList;
	
	
	
	/*
	 * 	Laad eenmalig alle parkeerplaats lijsten
	 */	
	@RequestMapping(value = "/parkinglocationsinit")
	@ResponseBody
	public List<Parking> ParkingLocationsInit() {
		RestTemplate restTemplate = new RestTemplate();
		System.out.println("printdebug 1");
		ParkeerLocaties topArray;
		
		parkingLocationList = new ArrayList<Parking>(); 
		
		try {			
			//XXX: refactoren naar Service object..
			
			topArray = restTemplate.getForObject("http://www.amsterdamopendata.nl/files/ivv/parkeren/locaties.json", ParkeerLocaties.class);
			List<ParkeerLocatieTop> x = topArray.getParkeerLocaties();
			
			 for (int i = 0; i < x.size(); i++) {
			     String title = x.get(i).getParkeerlocatie().getTitle();
			     String gps = x.get(i).getParkeerlocatie().getLocatie();
			    
			     //Parse and Reverse gps lat lon.
			     String tempLat = gps.substring(gps.indexOf("[") + 1);
			     String lat = tempLat.substring(0,tempLat.indexOf(","));
			     String lon = tempLat.substring(tempLat.indexOf(",") + 1);

			     lon = lon.substring(0, lon.indexOf("]"));
			     gps = lon + ", " + lat;
     
			     System.out.println("name:" + title);
			     System.out.println("gps:" + gps);	
			     
			     if( x.get(i).getParkeerlocatie().getType().equals("P+R") ) {
				     parkingLocationList.add(new PR(title,gps));
			     }			     
			     //List<Coordinates> coordinates = x.get(i).getParkeerlocatie().getLocatie().getCoordinates();
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return parkingLocationList;
	}
}
	

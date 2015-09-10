package com.dynacore.livemap.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.dynacore.livemap.entity.hibernate.ParkingLogData;
import com.dynacore.livemap.entity.jsonrepresentations.parking.FeatureCollection;
import com.dynacore.livemap.service.ParkingPlaceService;
import com.dynacore.opencity.entity.jsonrepresentations.ParkeerLocatieTop;
import com.dynacore.opencity.entity.jsonrepresentations.ParkeerLocaties;
import com.dynacore.testing123.entity.PR;
import com.dynacore.testing123.entity.Parking;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * type:
 * Fietspunt
 * P+R
 * Parkeergarage
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@Controller
public class ParkingLocationController {
	
	@Autowired
	private ParkingPlaceService parkingPlaceService;
		
	@RequestMapping(value = "/parkinglocationsinit")
	@ResponseBody
	public List<Parking> ParkingLocationsInit() {
		List<Parking> parkingLocationList;

		RestTemplate restTemplate = new RestTemplate();
		ParkeerLocaties topArray;

		parkingLocationList = new ArrayList<Parking>(); 
		try {			
	
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

			     //Selecteer alleen de P+R parkings.
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
	
	
	@RequestMapping(value = "/x6")
	@ResponseBody
	public FeatureCollection getParkingGeojson() {
		RestTemplate restTemplate = new RestTemplate();
		System.out.println("printdebug 1");
		FeatureCollection top = null;
		
		try {
			
			MappingJackson2HttpMessageConverter jacksonMessageConverter = new MappingJackson2HttpMessageConverter();			
			List<MediaType> supportedMediaTypes = new ArrayList<MediaType>(); 
			supportedMediaTypes.add(MediaType.ALL);			
			jacksonMessageConverter.setSupportedMediaTypes(supportedMediaTypes);			
			List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>(); 
			messageConverters.add(jacksonMessageConverter);		
			restTemplate.getMessageConverters().add(jacksonMessageConverter);
			top = restTemplate.getForObject("http://www.trafficlink-online.nl/trafficlinkdata/wegdata/IDPA_ParkingLocation.GeoJSON", FeatureCollection.class);
			
			for(int i=0; i < top.getFeatures().size(); i++){
//				top.getFeatures().get(i).getProperties().;
			}
			
			System.out.println("Top: type" + top.getType());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return top;
	}
	
	public void updateParkingPlaces(ParkingLogData logData) {		
		parkingPlaceService.save(logData);
	}
	

}
	




//@Component is equivalent to
//
//<bean>
//@Service, @Controller , @Repository = {@Component + some more special functionality}
//
//That mean Service,Controller and Repository are functionally the same.
//
//The three annotations are used to separate "Layers" in your application,
//
//Controllers just do stuff like dispatching, forwarding, calling service methods etc.
//Service Hold business Logic, Calculations etc.
//Repository are the DAOs(Data Access Objects), they access the database directly.
//Now you may ask why separate them:(I assume you know AOP-Aspect Oriented Programming)
//
//Lets say you want to Monitors the Activity of the DAO Layer only. You will write an Aspect(A class) class that does some logging before and after every method of your DAO is invoked, you are able to do that using AOP as you have three distinct Layers and are not mixed.
//
//So you can do logging of DAO "around", "before" or "after" the DAO methods. You could do that because you had a DAO in the first place. What you just achieved is Separation of concerns or tasks.
//
//Imagine if there were only one annotation @Controller, then this component will have dispatching, business logic and accessing database all mixed, so dirty code!
//
//Above mentioned is one very common scenario, there are many more use cases of why to use three annotations.
//








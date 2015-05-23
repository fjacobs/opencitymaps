package com.dynacore.mymvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.dynacore.livemap.entity.hibernate.ParkingLogData;
import com.dynacore.livemap.entity.jsonrepresentations.FeatureCollection;
import com.dynacore.livemap.service.ParkingPlaceService;

@Controller
public class ParkingPlaceController {

	@Autowired 
	ParkingPlaceService parkingPlaceService;
	
	@RequestMapping (value = "/x1")
	public String sayHello(Model model) {
		model.addAttribute("greeting", "Hello World");
		return "hello";
	}
	
	@RequestMapping(value = "/x2")
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
			
			System.out.println("Top: type: " + top.getType());
			parseFeatureCollection(top);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return top;
	}

	public void parseFeatureCollection(FeatureCollection fc){
		for(int i=0; i < fc.getFeatures().size(); i++){
			System.out.println(fc.getFeatures().get(i).getProperties().getName());
			System.out.println(fc.getFeatures().get(i).getProperties().getPubDate()); //omzetten naar unix ts
			System.out.println(fc.getFeatures().get(i).getProperties().getType()); 
			System.out.println(fc.getFeatures().get(i).getProperties().getState());
			System.out.println(fc.getFeatures().get(i).getProperties().getFreeSpaceLong());
			System.out.println(fc.getFeatures().get(i).getProperties().getFreeSpaceShort());
			System.out.println(fc.getFeatures().get(i).getProperties().getLongCapacity());
			System.out.println(fc.getFeatures().get(i).getProperties().getShortCapacity());
			
			ParkingLogData property = new ParkingLogData(
					fc.getFeatures().get(i).getProperties().getName(),
					fc.getFeatures().get(i).getProperties().getPubDate(), 
					fc.getFeatures().get(i).getProperties().getType(), 
					fc.getFeatures().get(i).getProperties().getState(), 
					fc.getFeatures().get(i).getProperties().getFreeSpaceShort(), 
					fc.getFeatures().get(i).getProperties().getFreeSpaceLong(), 
					fc.getFeatures().get(i).getProperties().getShortCapacity(), 
					fc.getFeatures().get(i).getProperties().getLongCapacity()
			);
			
			parkingPlaceService.save(property);				
		}
	}
	
}

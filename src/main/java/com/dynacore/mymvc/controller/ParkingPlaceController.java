package com.dynacore.mymvc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.dynacore.livemap.entity.jsonrepresentations.parking.FeatureCollection;
import com.dynacore.livemap.service.ParkingPlaceService;

@Controller
public class ParkingPlaceController {

	@Autowired 
	ParkingPlaceService parkingPlaceService;
	FeatureCollection top;
	
	public ParkingPlaceController() { }
		
	@RequestMapping(value = "getCustomParkingJson")
	@ResponseBody
	public FeatureCollection getCustomParkingJson() {
		return parkingPlaceService.getProcessedJson();
	}
 
}
